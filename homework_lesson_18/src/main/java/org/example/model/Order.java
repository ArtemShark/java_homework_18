package org.example.model;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Order {
    private int orderId;
    private int clientId;
    private int staffId;

    private LocalDate orderDate;
    private BigDecimal totalPrice;

    public Order() {}

    public Order(int orderId, int clientId, int staffId, LocalDate orderDate, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.staffId = staffId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}
