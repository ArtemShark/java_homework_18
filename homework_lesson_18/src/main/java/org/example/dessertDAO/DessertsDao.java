package org.example.dessertDAO;

import org.example.model.Dessert;

import java.util.List;
import java.util.Optional;

public interface DessertsDao {
    List<Dessert> findAll();
    Optional<Dessert> findById(int id);
    void save(Dessert dessert);
    void update(Dessert dessert);
    void delete(int id);
}
