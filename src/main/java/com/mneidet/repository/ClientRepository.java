package com.mneidet.repository;

import com.mneidet.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Long countByName(String name);

    List<Client> findByName(String name);

}