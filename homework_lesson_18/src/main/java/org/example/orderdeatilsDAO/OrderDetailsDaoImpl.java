package org.example.orderdeatilsDAO;

import org.example.model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    private final Connection connection;

    public OrderDetailsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OrderDetail> findAll() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ItemType"),
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public Optional<OrderDetail> findById(int id) {
        String sql = "SELECT * FROM OrderDetails WHERE OrderDetailID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ItemType"),
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity"));
                return Optional.of(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetails (OrderID, ItemType, ItemID, Quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setString(2, orderDetail.getItemType());
            pstmt.setInt(3, orderDetail.getItemId());
            pstmt.setInt(4, orderDetail.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(OrderDetail orderDetail) {
        String sql = "UPDATE OrderDetails SET OrderID = ?, ItemType = ?, ItemID = ?, Quantity = ? WHERE OrderDetailID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setString(2, orderDetail.getItemType());
            pstmt.setInt(3, orderDetail.getItemId());
            pstmt.setInt(4, orderDetail.getQuantity());
            pstmt.setInt(5, orderDetail.getOrderDetailId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM OrderDetails WHERE OrderDetailID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
