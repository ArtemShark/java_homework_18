package org.example.orderdeatilsDAO;

import org.example.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsDao {
    List<OrderDetail> findAll();
    Optional<OrderDetail> findById(int id);
    void save(OrderDetail orderDetail);
    void update(OrderDetail orderDetail);
    void delete(int id);
}
