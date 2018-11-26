package com.mneidet.gui;

import com.mneidet.model.Client;
import com.mneidet.model.QuestionFoto;
import com.mneidet.repository.ClientRepository;
import com.mneidet.repository.QuestionFotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("/clientAdd")
    public List<Client> getClientList2() {
        List<Client> clients = new ArrayList<>();
        Client client = new Client();
        client.setDescription("Vadim from interface2");
        client.setId_vk("131233BBBBB2");
        client.setName("Vaim2");
        clientRepository.save(client);
        clientRepository.findAll().forEach(clients::add);

        return clients;
    }

    @GetMapping("/clientUpdate")
    public List<Client> updateClient() {
        List<Client> clients = new ArrayList<>();
        Client client = new Client();
        client.setId(1111);
        client.setDescription("Vadim from interface2");
        client.setId_vk("131233BBBBB2");
        client.setName("Vaim3Updated");

        clientRepository.save(client);
        clientRepository.findAll().forEach(clients::add);

        return clients;
    }

    @GetMapping("/clientbyname")
    public List<Client> getClientByName(@RequestParam(name = "name") String name) {
        return clientRepository.findByName(name);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
        long longId = id;
        Client client = clientRepository.findById(longId).get();
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }
}