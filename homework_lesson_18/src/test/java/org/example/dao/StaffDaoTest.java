package org.example.dao;

import org.example.staffDAO.StaffDaoImpl;
import org.example.model.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StaffDaoTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    private StaffDaoImpl staffDaoImpl;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        staffDaoImpl = new StaffDaoImpl(connection);
    }

    @Test
    public void testFindAll() throws Exception {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("StaffID")).thenReturn(1, 2);
        when(resultSet.getString("FullName")).thenReturn("Иван Максименко", "Никита Гаращенко");
        when(resultSet.getString("Phone")).thenReturn("0679875544", "0987658899");
        when(resultSet.getString("Email")).thenReturn("ivan@mail.com", "nikita@mail.com");
        when(resultSet.getString("Position")).thenReturn("Бариста", "Официант");

        List<Staff> staffList = staffDaoImpl.findAll();

        assertEquals(2, staffList.size());
        assertEquals("Иван Максименко", staffList.get(0).getFullName());
        assertEquals("Официант", staffList.get(1).getPosition());
    }

    @Test
    public void testFindByIdFound() throws Exception {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("StaffID")).thenReturn(1);
        when(resultSet.getString("FullName")).thenReturn("Иван Максименко");
        when(resultSet.getString("Phone")).thenReturn("0679875544");
        when(resultSet.getString("Email")).thenReturn("ivan@mail.com");
        when(resultSet.getString("Position")).thenReturn("Бариста");

        Optional<Staff> staff = staffDaoImpl.findById(1);

        assertTrue(staff.isPresent());
        assertEquals("Иван Максименко", staff.get().getFullName());
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        when(resultSet.next()).thenReturn(false);

        Optional<Staff> staff = staffDaoImpl.findById(99);

        assertFalse(staff.isPresent());
    }

    @Test
    public void testSave() throws Exception {
        Staff staff = new Staff(0, "Камилла Чуб", "0987654433", "kamilla@mail.com", "Кондитер");

        staffDaoImpl.save(staff);

        verify(preparedStatement, times(1)).setString(1, "Камилла Чуб");
        verify(preparedStatement, times(1)).setString(2, "0987654433");
        verify(preparedStatement, times(1)).setString(3, "kamilla@mail.com");
        verify(preparedStatement, times(1)).setString(4, "Кондитер");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws Exception {
        Staff staff = new Staff(1, "Юра Марченко", "0679089900", "yura@mail.com", "Бариста");

        staffDaoImpl.update(staff);

        verify(preparedStatement, times(1)).setString(1, "Юра Марченко");
        verify(preparedStatement, times(1)).setString(2, "0679089900");
        verify(preparedStatement, times(1)).setString(3, "yura@mail.com");
        verify(preparedStatement, times(1)).setString(4, "Бариста");
        verify(preparedStatement, times(1)).setInt(5, staff.getStaffId());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws Exception {
        staffDaoImpl.delete(1);

        verify(preparedStatement, times(1)).setInt(1, 1);
        verify(preparedStatement, times(1)).executeUpdate();
    }

}

