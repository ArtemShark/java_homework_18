package org.example.staffDAO;

import org.example.model.Order;
import org.example.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffDao {
    List<Staff> findAll();
    Optional<Staff> findById(int id);
    void save(Staff staff);
    void update(Staff staff);
    void delete(int id);

}
