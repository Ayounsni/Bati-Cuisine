package services.implementations;

import models.entities.Client;
import repository.implementations.ClientRepository;
import repository.interfaces.IClientRepository;
import services.interfaces.IClientService;

import java.util.UUID;

public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }



    @Override
    public Client addClient(Client client) {
        return clientRepository.addClient(client);
    }

    @Override
    public Client findClientByName(String nom) {
        return clientRepository.findClientByName(nom);
    }

    @Override
    public Client findClientById(UUID clientId) {
        return clientRepository.findClientById(clientId);
    }

    @Override
    public boolean updateClient(Client client) {
        return clientRepository.updateClient(client);
    }

    @Override
    public boolean deleteClient(UUID clientId) {
        return clientRepository.deleteClient(clientId);
    }
}
