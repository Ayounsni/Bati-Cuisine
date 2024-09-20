package ui;

import models.entities.Client;
import services.implementations.ClientService;
import services.interfaces.IClientService;

import java.util.Scanner;

public class ClientUi {

    private final Scanner scanner = new Scanner(System.in);
    private final IClientService clientService = new ClientService();

    public void manageClient() {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                searchExistingClient();
                break;
            case 2:
                addNewClient();
                break;
            default:
                System.out.println("Choix invalide.");
                manageClient();
        }
    }

    private void searchExistingClient() {
        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();

        Client client = clientService.findClientByName(clientName);
        if (client != null) {
            System.out.println("Client trouvé !");
            System.out.println("Nom : " + client.getNom());
            System.out.println("Adresse : " + client.getAdresse());
            System.out.println("Numéro de téléphone : " + client.getTelephone());

            System.out.print("Souhaitez-vous continuer avec ce client ? (oui/non) : ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("oui")) {
                // Logique pour continuer avec le client
                System.out.println("Vous continuez avec le client.");
            } else {
                System.out.println("Retour au menu principal.");
            }
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    private void addNewClient() {
        System.out.println("--- Ajouter un nouveau client ---");

        System.out.print("Entrez le nom : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez l'adresse : ");
        String adresse = scanner.nextLine();

        System.out.print("Entrez le numéro de téléphone : ");
        String telephone = scanner.nextLine();

        System.out.print("Est-ce un client professionnel ? (true/false) : ");
        boolean estProfessionnel = scanner.nextBoolean();
        scanner.nextLine();

        Client client = new Client(nom, adresse, telephone, estProfessionnel);

        Client addedClient = clientService.addClient(client);

        if (addedClient != null) {
            System.out.println("Client ajouté avec succès !");
            System.out.println("Nom : " + addedClient.getNom());
            System.out.println("Adresse : " + addedClient.getAdresse());
            System.out.println("Numéro de téléphone : " + addedClient.getTelephone());
        } else {
            System.out.println("Erreur lors de l'ajout du client.");
        }
    }
}

