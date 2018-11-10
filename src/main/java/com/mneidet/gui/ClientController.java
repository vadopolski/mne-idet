package com.mneidet.gui;

import com.mneidet.model.Client;
import com.mneidet.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    private ClientRepository clientRepository;

    @Autowired
    public ClientController(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public List<Client> getClientList() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);

        return clients;
    }

    @GetMapping("/client/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientRepository.findById(id).get();
    }

    @GetMapping("/clientbyname")
    public List<Client> getClientByName(@RequestParam(name = "name") String name) {
        return clientRepository.findByName(name);
    }
}