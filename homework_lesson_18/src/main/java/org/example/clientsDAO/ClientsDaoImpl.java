package org.example.clientsDAO;

import org.example.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientsDaoImpl implements ClientsDao {
    private final Connection connection;

    public ClientsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Clients";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("ClientID"),
                        rs.getString("FullName"),
                        rs.getDate("BirthDate").toLocalDate(),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getInt("Discount"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Optional<Client> findById(int id) {
        String sql = "SELECT * FROM Clients WHERE ClientID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                        rs.getInt("ClientID"),
                        rs.getString("FullName"),
                        rs.getDate("BirthDate").toLocalDate(),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getInt("Discount"));
                return Optional.of(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Client client) {
        String sql = "INSERT INTO Clients (FullName, BirthDate, Phone, Email, Discount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, client.getFullName());
            pstmt.setDate(2, Date.valueOf(client.getBirthDate()));
            pstmt.setString(3, client.getPhone());
            pstmt.setString(4, client.getEmail());
            pstmt.setInt(5, client.getDiscount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE Clients SET FullName = ?, BirthDate = ?, Phone = ?, Email = ?, Discount = ? WHERE ClientID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, client.getFullName());
            pstmt.setDate(2, Date.valueOf(client.getBirthDate()));
            pstmt.setString(3, client.getPhone());
            pstmt.setString(4, client.getEmail());
            pstmt.setInt(5, client.getDiscount());
            pstmt.setInt(6, client.getClientId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Clients WHERE ClientID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
