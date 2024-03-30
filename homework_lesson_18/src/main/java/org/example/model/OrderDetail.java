package org.example.model;

public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private String itemType;
    private int itemId;
    private int quantity;


    public OrderDetail() {}

    public OrderDetail(int orderDetailId, int orderId, String itemType, int itemId, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.itemType = itemType;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(int orderDetailId) { this.orderDetailId = orderDetailId; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
