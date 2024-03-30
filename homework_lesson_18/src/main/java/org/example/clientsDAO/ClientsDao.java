package org.example.clientsDAO;

import org.example.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientsDao {
    List<Client> findAll();
    Optional<Client> findById(int id);
    void save(Client client);
    void update(Client client);
    void delete(int id);
}
