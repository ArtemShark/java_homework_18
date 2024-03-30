package org.example.dao;
import org.example.clientsDAO.ClientsDaoImpl;
import org.example.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientDaoTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    private ClientsDaoImpl clientsDaoImpl;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        clientsDaoImpl = new ClientsDaoImpl(connection);
    }
    @Test
    public void testFindAll() throws Exception {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("ClientID")).thenReturn(1, 2);
        when(resultSet.getString("FullName")).thenReturn("Ира Наливайко", "Алиса Обдар");
        when(resultSet.getDate("BirthDate")).thenReturn(Date.valueOf(LocalDate.of(1990, 1, 1)), Date.valueOf(LocalDate.of(1992, 2, 2)));
        when(resultSet.getString("Phone")).thenReturn("0985468877", "0674532111");
        when(resultSet.getString("Email")).thenReturn("ira@mail.com", "alice@mail.com");
        when(resultSet.getInt("Discount")).thenReturn(10, 15);

        List<Client> clients = clientsDaoImpl.findAll();

        assertEquals(2, clients.size());
        assertEquals("Ира Наливайко", clients.get(0).getFullName());
        assertEquals(LocalDate.of(1992, 2, 2), clients.get(1).getBirthDate());
    }

    @Test
    public void testFindByIdFound() throws Exception {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("ClientID")).thenReturn(1);
        when(resultSet.getString("FullName")).thenReturn("Ира Наливайко");
        when(resultSet.getDate("BirthDate")).thenReturn(Date.valueOf(LocalDate.of(1990, 1, 1)));
        when(resultSet.getString("Phone")).thenReturn("0985468877");
        when(resultSet.getString("Email")).thenReturn("ira@mail.com");
        when(resultSet.getInt("Discount")).thenReturn(10);

        Optional<Client> client = clientsDaoImpl.findById(1);

        assertTrue(client.isPresent());
        assertEquals("Ира Наливайко", client.get().getFullName());
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        when(resultSet.next()).thenReturn(false);

        Optional<Client> client = clientsDaoImpl.findById(99);

        assertFalse(client.isPresent());
    }

    @Test
    public void testSave() throws Exception {
        Client client = new Client(0, "Тимур Иовенко", LocalDate.of(2000, 1, 1), "0980004566", "timur@mail.com", 5);

        clientsDaoImpl.save(client);

        verify(preparedStatement, times(1)).setString(1, "Тимур Иовенко");
        verify(preparedStatement, times(1)).setDate(2, Date.valueOf(LocalDate.of(2000, 1, 1)));
        verify(preparedStatement, times(1)).setString(3, "0980004566");
        verify(preparedStatement, times(1)).setString(4, "timur@mail.com");
        verify(preparedStatement, times(1)).setInt(5, 5);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws Exception {
        Client client = new Client(1, "Лера Волкова", LocalDate.of(2001, 2, 2), "0987654321", "lera@mail.com", 10);

        clientsDaoImpl.update(client);

        verify(preparedStatement, times(1)).setString(1, "Лера Волкова");
        verify(preparedStatement, times(1)).setDate(2, Date.valueOf(LocalDate.of(2001, 2, 2)));
        verify(preparedStatement, times(1)).setString(3, "0987654321");
        verify(preparedStatement, times(1)).setString(4, "lera@mail.com");
        verify(preparedStatement, times(1)).setInt(5, 10);
        verify(preparedStatement, times(1)).setInt(6, client.getClientId());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws Exception {
        clientsDaoImpl.delete(1);

        verify(preparedStatement, times(1)).setInt(1, 1);
        verify(preparedStatement, times(1)).executeUpdate();
    }

}
