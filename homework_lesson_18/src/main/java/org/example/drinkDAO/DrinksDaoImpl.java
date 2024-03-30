package org.example.drinkDAO;

import org.example.model.Drink;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrinksDaoImpl implements DrinksDao {
    private final Connection connection;

    public DrinksDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Drink> findAll() {
        List<Drink> drinks = new ArrayList<>();
        String sql = "SELECT * FROM Drinks";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Drink drink = new Drink(
                        rs.getInt("DrinkID"),
                        rs.getString("Name_EN"),
                        rs.getString("Name_OtherLanguage"),
                        rs.getBigDecimal("Price"));
                drinks.add(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinks;
    }

    @Override
    public Optional<Drink> findById(int id) {
        String sql = "SELECT * FROM Drinks WHERE DrinkID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Drink drink = new Drink(
                        rs.getInt("DrinkID"),
                        rs.getString("Name_EN"),
                        rs.getString("Name_OtherLanguage"),
                        rs.getBigDecimal("Price"));
                return Optional.of(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Drink drink) {
        String sql = "INSERT INTO Drinks (Name_EN, Name_OtherLanguage, Price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, drink.getNameEN());
            pstmt.setString(2, drink.getNameOtherLanguage());
            pstmt.setBigDecimal(3, drink.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Drink drink) {
        String sql = "UPDATE Drinks SET Name_EN = ?, Name_OtherLanguage = ?, Price = ? WHERE DrinkID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, drink.getNameEN());
            pstmt.setString(2, drink.getNameOtherLanguage());
            pstmt.setBigDecimal(3, drink.getPrice());
            pstmt.setInt(4, drink.getDrinkId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Drinks WHERE DrinkID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCoffee(Drink coffee) {
        String sql = "INSERT INTO Drinks (Name_EN, Name_OtherLanguage, Price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, coffee.getNameEN());
            pstmt.setString(2, coffee.getNameOtherLanguage());
            pstmt.setBigDecimal(3, coffee.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCoffeeName(int id, String newNameEN, String newNameOtherLanguage) {
        String sql = "UPDATE Drinks SET Name_EN = ?, Name_OtherLanguage = ? WHERE DrinkID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newNameEN);
            pstmt.setString(2, newNameOtherLanguage);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

