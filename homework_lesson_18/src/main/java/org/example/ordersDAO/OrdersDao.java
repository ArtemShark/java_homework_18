package org.example.ordersDAO;

import org.example.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrdersDao {
    List<Order> findAll();
    Optional<Order> findById(int id);
    void save(Order order);
    void update(Order order);
    void delete(int id);

    void saveDessertOrder(Order order);

    void saveCoffeeOrder(Order order);

    void deleteOrdersByDessertId(int dessertId);

    List<Order> findByDessertId(int dessertId);

    List<Order> findAllOrdersOfStaff(int staffId);

    List<Order> findAllOrdersOfClient(int clientId);
}
