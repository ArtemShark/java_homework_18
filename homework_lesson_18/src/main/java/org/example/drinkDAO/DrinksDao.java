package org.example.drinkDAO;

import org.example.model.Drink;

import java.util.List;
import java.util.Optional;

public interface DrinksDao {
    List<Drink> findAll();
    Optional<Drink> findById(int id);
    void save(Drink drink);
    void update(Drink drink);
    void delete(int id);

    void saveCoffee(Drink coffee);

    void updateCoffeeName(int id, String newNameEN, String newNameOtherLanguage);
}
