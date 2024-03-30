package org.example.ordersDAO;

import org.example.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersDaoImpl implements OrdersDao {
    private final Connection connection;

    public OrdersDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("ClientID"),
                        rs.getInt("StaffID"),
                        rs.getDate("OrderDate").toLocalDate(),
                        rs.getBigDecimal("TotalPrice"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Optional<Order> findById(int id) {
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("ClientID"),
                        rs.getInt("StaffID"),
                        rs.getDate("OrderDate").toLocalDate(),
                        rs.getBigDecimal("TotalPrice"));
                return Optional.of(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Order order) {
        String sql = "INSERT INTO Orders (ClientID, StaffID, OrderDate, TotalPrice) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, order.getClientId());
            pstmt.setInt(2, order.getStaffId());
            pstmt.setDate(3, Date.valueOf(order.getOrderDate()));
            pstmt.setBigDecimal(4, order.getTotalPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE Orders SET ClientID = ?, StaffID = ?, OrderDate = ?, TotalPrice = ? WHERE OrderID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, order.getClientId());
            pstmt.setInt(2, order.getStaffId());
            pstmt.setDate(3, Date.valueOf(order.getOrderDate()));
            pstmt.setBigDecimal(4, order.getTotalPrice());
            pstmt.setInt(5, order.getOrderId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveCoffeeOrder(Order order) {
        String sql = "INSERT INTO Orders (ClientID, StaffID, OrderDate, TotalPrice, OrderType) VALUES (?, ?, ?, ?, 'Coffee')";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, order.getClientId());
            pstmt.setInt(2, order.getStaffId());
            pstmt.setDate(3, Date.valueOf(order.getOrderDate()));
            pstmt.setBigDecimal(4, order.getTotalPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDessertOrder(Order order) {
        String sql = "INSERT INTO Orders (ClientID, StaffID, OrderDate, TotalPrice, OrderType) VALUES (?, ?, ?, ?, 'Dessert')";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, order.getClientId());
            pstmt.setInt(2, order.getStaffId());
            pstmt.setDate(3, Date.valueOf(order.getOrderDate()));
            pstmt.setBigDecimal(4, order.getTotalPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrdersByDessertId(int dessertId) {
        String sql = "DELETE FROM Orders WHERE DessertID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dessertId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findByDessertId(int dessertId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.* FROM Orders o INNER JOIN OrderDetails od ON o.OrderID = o.OrderID WHERE o.DessertID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dessertId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("ClientID"),
                        rs.getInt("StaffID"),
                        rs.getDate("OrderDate").toLocalDate(),
                        rs.getBigDecimal("TotalPrice"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findAllOrdersOfStaff(int staffId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE StaffID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, staffId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("ClientID"),
                        rs.getInt("StaffID"),
                        rs.getDate("OrderDate").toLocalDate(),
                        rs.getBigDecimal("TotalPrice"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findAllOrdersOfClient(int clientId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE ClientID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("ClientID"),
                        rs.getInt("StaffID"),
                        rs.getDate("OrderDate").toLocalDate(),
                        rs.getBigDecimal("TotalPrice"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
