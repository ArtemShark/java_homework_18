package org.example.dao;


import org.example.drinkDAO.DrinksDaoImpl;
import org.example.model.Drink;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DrinkDaoTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    private DrinksDaoImpl drinksDaoImpl;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        drinksDaoImpl = new DrinksDaoImpl(connection);
    }

    @Test
    public void testFindAll() throws Exception {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("DrinkID")).thenReturn(1, 2);
        when(resultSet.getString("Name_EN")).thenReturn("Cappucino", "Tea");
        when(resultSet.getString("Name_OtherLanguage")).thenReturn("Капучино", "Чай");
        when(resultSet.getBigDecimal("Price")).thenReturn(BigDecimal.valueOf(3.50), BigDecimal.valueOf(4.00));

        List<Drink> drinks = drinksDaoImpl.findAll();

        assertEquals(2, drinks.size());
        assertEquals("Cappucino", drinks.get(0).getNameEN());
        assertEquals(BigDecimal.valueOf(4.00), drinks.get(1).getPrice());
    }


    @Test
    public void testFindByIdFound() throws Exception {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("DrinkID")).thenReturn(1);
        when(resultSet.getString("Name_EN")).thenReturn("Cappucino");
        when(resultSet.getString("Name_OtherLanguage")).thenReturn("Капучино");
        when(resultSet.getBigDecimal("Price")).thenReturn(BigDecimal.valueOf(3.50));

        Optional<Drink> drink = drinksDaoImpl.findById(1);

        assertTrue(drink.isPresent());
        assertEquals("Cappucino", drink.get().getNameEN());
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        when(resultSet.next()).thenReturn(false);

        Optional<Drink> drink = drinksDaoImpl.findById(99);

        assertFalse(drink.isPresent());
    }

    @Test
    public void testSave() throws Exception {
        Drink drink = new Drink(0, "Coca-Cola", "Кока-кола", BigDecimal.valueOf(2.50));

        drinksDaoImpl.save(drink);

        verify(preparedStatement, times(1)).setString(1, "Coca-Cola");
        verify(preparedStatement, times(1)).setString(2, "Кока-кола");
        verify(preparedStatement, times(1)).setBigDecimal(3, BigDecimal.valueOf(2.50));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws Exception {
        Drink drink = new Drink(1, "Lipton", "Липтон", BigDecimal.valueOf(5.00));

        drinksDaoImpl.update(drink);

        verify(preparedStatement, times(1)).setString(1, "Lipton");
        verify(preparedStatement, times(1)).setString(2, "Липтон");
        verify(preparedStatement, times(1)).setBigDecimal(3, BigDecimal.valueOf(5.00));
        verify(preparedStatement, times(1)).setInt(4, 1);
        verify(preparedStatement, times(1)).executeUpdate();
    }


    @Test
    public void testDelete() throws Exception {
        drinksDaoImpl.delete(1);

        verify(preparedStatement, times(1)).setInt(1, 1);
        verify(preparedStatement, times(1)).executeUpdate();
    }

}
