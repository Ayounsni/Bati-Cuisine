package services.interfaces;

import models.entities.Client;

import java.util.UUID;

public interface IClientService {
    Client addClient(Client client);
    Client findClientByName(String nom);
    Client findClientById(UUID clientId);
    boolean updateClient(Client client);
    boolean deleteClient(UUID clientId);
}
