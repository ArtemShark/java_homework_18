package org.example.dao;

import org.example.dessertDAO.DessertsDaoImpl;
import org.example.model.Dessert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DessertDaoTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    private DessertsDaoImpl dessertsDaoImpl;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        dessertsDaoImpl = new DessertsDaoImpl(connection);
    }
    @Test
    public void testFindAll() throws Exception {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("DessertID")).thenReturn(1, 2);
        when(resultSet.getString("Name_EN")).thenReturn("Napoleon", "Tiramisu");
        when(resultSet.getString("Name_OtherLanguage")).thenReturn("Наполеон", "Тирамису");
        when(resultSet.getBigDecimal("Price")).thenReturn(BigDecimal.valueOf(5.50), BigDecimal.valueOf(6.00));

        List<Dessert> desserts = dessertsDaoImpl.findAll();

        assertEquals(2, desserts.size());
        assertEquals("Napoleon", desserts.get(0).getNameEN());
        assertEquals(BigDecimal.valueOf(6.00), desserts.get(1).getPrice());
    }

    @Test
    public void testFindByIdFound() throws Exception {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("DessertID")).thenReturn(1);
        when(resultSet.getString("Name_EN")).thenReturn("Napoleon");
        when(resultSet.getString("Name_OtherLanguage")).thenReturn("Наполеон");
        when(resultSet.getBigDecimal("Price")).thenReturn(BigDecimal.valueOf(5.50));

        Optional<Dessert> dessert = dessertsDaoImpl.findById(1);

        assertTrue(dessert.isPresent());
        assertEquals("Napoleon", dessert.get().getNameEN());
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        when(resultSet.next()).thenReturn(false);

        Optional<Dessert> dessert = dessertsDaoImpl.findById(99);

        assertFalse(dessert.isPresent());
    }

    @Test
    public void testSave() throws Exception {
        Dessert dessert = new Dessert(0, "Praga", "Прага", BigDecimal.valueOf(3.00));

        dessertsDaoImpl.save(dessert);

        verify(preparedStatement, times(1)).setString(1, "Praga");
        verify(preparedStatement, times(1)).setString(2, "Прага");
        verify(preparedStatement, times(1)).setBigDecimal(3, BigDecimal.valueOf(3.00));
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws Exception {
        Dessert dessert = new Dessert(1, "Zaher", "Захер", BigDecimal.valueOf(7.00));

        dessertsDaoImpl.update(dessert);

        verify(preparedStatement, times(1)).setString(1, "Zaher");
        verify(preparedStatement, times(1)).setString(2, "Захер");
        verify(preparedStatement, times(1)).setBigDecimal(3, BigDecimal.valueOf(7.00));
        verify(preparedStatement, times(1)).setInt(4, dessert.getDessertId());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws Exception {
        dessertsDaoImpl.delete(1);

        verify(preparedStatement, times(1)).setInt(1, 1);
        verify(preparedStatement, times(1)).executeUpdate();
    }

}
