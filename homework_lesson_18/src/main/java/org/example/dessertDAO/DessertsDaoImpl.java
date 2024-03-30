package org.example.dessertDAO;

import org.example.model.Dessert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DessertsDaoImpl implements DessertsDao {
    private final Connection connection;

    public DessertsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Dessert> findAll() {
        List<Dessert> desserts = new ArrayList<>();
        String sql = "SELECT * FROM Desserts";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Dessert dessert = new Dessert(
                        rs.getInt("DessertID"),
                        rs.getString("Name_EN"),
                        rs.getString("Name_OtherLanguage"),
                        rs.getBigDecimal("Price"));
                desserts.add(dessert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return desserts;
    }

    @Override
    public Optional<Dessert> findById(int id) {
        String sql = "SELECT * FROM Desserts WHERE DessertID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Dessert dessert = new Dessert(
                        rs.getInt("DessertID"),
                        rs.getString("Name_EN"),
                        rs.getString("Name_OtherLanguage"),
                        rs.getBigDecimal("Price"));
                return Optional.of(dessert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Dessert dessert) {
        String sql = "INSERT INTO Desserts (Name_EN, Name_OtherLanguage, Price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, dessert.getNameEN());
            pstmt.setString(2, dessert.getNameOtherLanguage());
            pstmt.setBigDecimal(3, dessert.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Dessert dessert) {
        String sql = "UPDATE Desserts SET Name_EN = ?, Name_OtherLanguage = ?, Price = ? WHERE DessertID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, dessert.getNameEN());
            pstmt.setString(2, dessert.getNameOtherLanguage());
            pstmt.setBigDecimal(3, dessert.getPrice());
            pstmt.setInt(4, dessert.getDessertId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Desserts WHERE DessertID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

