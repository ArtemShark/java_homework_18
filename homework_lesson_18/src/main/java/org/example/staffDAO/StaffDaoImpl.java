package org.example.staffDAO;

import org.example.model.Order;
import org.example.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffDaoImpl implements StaffDao {
    private final Connection connection;

    public StaffDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Staff> findAll() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getInt("StaffID"),
                        rs.getString("FullName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Position"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    @Override
    public Optional<Staff> findById(int id) {
        String sql = "SELECT * FROM Staff WHERE StaffID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff(
                        rs.getInt("StaffID"),
                        rs.getString("FullName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Position"));
                return Optional.of(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Staff staff) {
        String sql = "INSERT INTO Staff (FullName, Phone, Email, Position) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, staff.getFullName());
            pstmt.setString(2, staff.getPhone());
            pstmt.setString(3, staff.getEmail());
            pstmt.setString(4, staff.getPosition());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Staff staff) {
        String sql = "UPDATE Staff SET FullName = ?, Phone = ?, Email = ?, Position = ? WHERE StaffID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, staff.getFullName());
            pstmt.setString(2, staff.getPhone());
            pstmt.setString(3, staff.getEmail());
            pstmt.setString(4, staff.getPosition());
            pstmt.setInt(5, staff.getStaffId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Staff WHERE StaffID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
