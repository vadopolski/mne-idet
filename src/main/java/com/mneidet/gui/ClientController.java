package com.mneidet.gui;

import com.mneidet.model.Client;
import com.mneidet.repository.ClientRepository;
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

    // TODO: 25.11.18  Dont working
    @GetMapping("/clients2")
    public List<Client> getClientList2() {
        List<Client> clients = new ArrayList<>();
        Client client = new Client();
        client.setId(7777);
        client.setId_vk("131233BBBBB");
        client.setName("Vaim");
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

    // TODO: 25.11.18 Dont working
    @PostMapping("client")
    public ResponseEntity<Void> addArticle(@RequestBody Client client, UriComponentsBuilder builder) {
        boolean flag;

        List<Client> list = clientRepository.findByName(client.getName());
        if (list.size() > 0) {
            flag = false;
        } else {
            clientRepository.save(client);
            flag = true;
        }

        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/client/{id}").buildAndExpand(client.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}