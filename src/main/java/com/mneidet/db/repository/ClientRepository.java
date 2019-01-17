package com.mneidet.db.repository;

import com.mneidet.db.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Long countByName(String name);

    List<Client> findByName(String name);

    Client findById(Integer id);

}