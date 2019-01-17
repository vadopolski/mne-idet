package com.mneidet.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mneidet.db.entity.Client;
import com.mneidet.db.repository.ClientRepository;
import com.mneidet.model.ClientProfile;
import com.mneidet.vk.model.Profile;
import com.mneidet.vk.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
public class ClientController {
    private ClientRepository clientRepository;
    private static final String fotoResourceUrl = "https://api.vk.com/method/photos.get?owner_id=146133434&v=5.52&access_token=ec661a47ec661a47ec3c800455ec3c73b4eec66ec661a47b4ae594ad6e9d1e29d1b7da4&album_id=profile";


    @Autowired
    public ClientController(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @GetMapping("/clients")
    public List<ClientProfile> getClientList() throws IOException {

        List<Client> clients = new ArrayList<>();
        List<ClientProfile> clientProfiles = new ArrayList<>();

        clientRepository.findAll()
                .forEach(e -> {
                    ClientProfile clientProfile = new ClientProfile();

                    try {
                        Profile profileById = Util.getProfileById(e.getId_vk());
                        clientProfile.setId(e.getId());
                        clientProfile.setId_vk(e.getId_vk());
                        clientProfile.setDescription(e.getDescription());
                        clientProfile.setName(profileById.getFirstName() + " " + profileById.getLastName());
                        clientProfile.setSmallPhotoLink(profileById.getLastSmallProfilePhotoLink());
                        clientProfile.setBigPhotoLink(profileById.getLastBigProfilePhotoLink());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
//                    clients.add(e);
                    clientProfiles.add(clientProfile);
                });

        return clientProfiles;
    }

    @GetMapping("/clientAdd")
    public List<Client> setClient() {
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
    public List<Client> updateClientName(long id) {
        Client cl = new Client();
        Optional<Client> optionalClient = Optional.of(cl);

        Optional<Client> byId = clientRepository.findById(id);
        byId.get().setName("New Name");

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