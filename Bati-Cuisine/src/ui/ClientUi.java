package ui;

import models.entities.Client;
import services.implementations.ClientService;
import services.interfaces.IClientService;
import utils.InputValidator;

import java.util.Optional;
import java.util.Scanner;

public class ClientUi {

    private final Scanner scanner = new Scanner(System.in);
    private final IClientService clientService = new ClientService();
    private ProjetUi projetUi = new ProjetUi();

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
        String clientName = scanner.nextLine().toLowerCase();

        Client client = clientService.findClientByName(clientName);
        if (client != null) {
            System.out.println("Client trouvé !");
            System.out.println("Nom : " + client.getNom());
            System.out.println("Adresse : " + client.getAdresse());
            System.out.println("Numéro de téléphone : " + client.getTelephone());

            System.out.print("Souhaitez-vous continuer avec ce client ? (oui/non) : ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("oui")) {
                projetUi.addProjet(client);
            } else {
                System.out.println("Retour au menu principal.");
            }
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    private void addNewClient() {
        System.out.println("--- Ajouter un nouveau client ---");
        String nom;
        Optional<Client> clients;
        do {
            System.out.print("Entrez le nom : ");
             nom = scanner.nextLine().toLowerCase();

             clients=Optional.ofNullable(clientService.findClientByName(nom));
            if (clients.isPresent()) {
                System.out.println("Ce nom existe déjà. Veuillez entrer un autre nom.");
            }

        }while (clients.isPresent());

        System.out.print("Entrez l'adresse : ");
        String adresse = scanner.nextLine();

        String telephone;
        do {
            System.out.print("Entrez le numéro de téléphone : ");
            telephone = scanner.nextLine();
            if(!InputValidator.validatePhoneNumber(telephone)) {
                System.out.println("Numéro de téléphone invalide. Il doit contenir 10 chiffres et commencer par 0.");
            }

        }while (!InputValidator.validatePhoneNumber(telephone));

        System.out.print("Est-ce un client professionnel ? (true/false) : ");
        boolean estProfessionnel = scanner.nextBoolean();
        scanner.nextLine();
        if (estProfessionnel) {
            float remise;
            do {
                System.out.print("Entrez le montant de remise en (%) : ");

                while (!scanner.hasNextFloat()) {
                    System.out.println("Entrée invalide. Veuillez entrer un pourcentage valide.");
                    scanner.next();
                }

                remise = scanner.nextFloat();

                if (!InputValidator.validateRemise(remise)) {
                    System.out.println("Le montant de remise doit être compris entre 0 et 100.");
                }

            } while (!InputValidator.validateRemise(remise));

            Client client = new Client(nom, adresse, telephone, true, remise);
            Client addedClient = clientService.addClient(client);
            if (addedClient != null) {
                System.out.println("Client ajouté avec succès !");
                System.out.println("Nom : " + addedClient.getNom());
                System.out.println("Adresse : " + addedClient.getAdresse());
                System.out.println("Numéro de téléphone : " + addedClient.getTelephone());
            } else {
                System.out.println("Erreur lors de l'ajout du client.");
            }
        }else {
            Client client = new Client(nom, adresse, telephone, false,0);
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
}

