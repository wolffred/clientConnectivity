package com.client.clientConnectivity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }


    public void addNewClient(Client client) {
        Optional<Client> clientOptional = clientRepository.findClientByEmail(client.getEmail());
        if(clientOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        clientRepository.save(client);
        System.out.println(client);
    }

    public void deleteClient(Long clientId) {
        boolean exist = clientRepository.existsById(clientId);
        if(!exist){
            throw new IllegalStateException("client with id: " + clientId + " already exist");
        }
        clientRepository.deleteById(clientId);
    }

    @Transactional
    public void updateClient(Long clientId, String name, String email){
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("Client with id: " + clientId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(client.getName(), name)){
            client.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(client.getEmail(), email)){
            Optional<Client> clientOptional = clientRepository.findClientByEmail(email);
            if(clientOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            client.setName(email);
        }
    }
}
