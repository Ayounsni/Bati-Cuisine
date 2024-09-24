package ui;

import models.entities.Devis;
import models.entities.Projet;
import services.implementations.DevisService;
import services.implementations.ProjetService;
import services.interfaces.IDevisService;
import services.interfaces.IProjetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DevisUi {
    private final Scanner scanner = new Scanner(System.in);
    private final IDevisService devisService = new DevisService();
    private final IProjetService projetService = new ProjetService();


    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Enregistrer un devis");
            System.out.println("2. Afficher les devis");
            System.out.println("3. Refuser un devis autant que client");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDevis();
                    break;
                case 2:
                    findAllDevis();
                    break;
                case 3:

                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    public void addDevis(){
        System.out.print("Entrez l'ID du projet que vous voulez enregistrer son devis : ");
        UUID projetId = UUID.fromString(scanner.nextLine());
        Projet projet = projetService.findProjetById(projetId);
        Devis devis = devisService.getDevisByProjetId(projetId);
        if(projet.getCoutTotal() != null){
        if(devis == null) {
            enregistreDevis(projet);
        }else{
            System.out.println("Ce projet a déja un devis");
        }
        }else {
            System.out.println("Le cout total de ce projet n'est pas encore calculer");
        }
    }

    public void enregistreDevis(Projet projet){
        System.out.println("--Enregistrer un devis --");
        System.out.print("Entrez la dure de validation du devis (jours) : ");
        int dure = scanner.nextInt();
        scanner.nextLine();
        Devis newDevis = new Devis(projet.getCoutTotal(), LocalDate.now(),LocalDate.now().plusDays(dure),projet);
        devisService.addDevis(newDevis);
        System.out.println("Devis enregistrée avec succés");
        System.out.println("Devis du projet: "+newDevis.getProjet().getNom());
        System.out.println("Cout d estimation: "+newDevis.getMontantEstime());
        System.out.println("Date d'emission: "+newDevis.getDateEmission());
        System.out.println("Date de validité: "+newDevis.getDateValidite());
        if(newDevis.isAccepte()){
            System.out.println("Status : confirmer");
        }else{
            System.out.println("Status : refuser");
        }
    }
    public void findAllDevis(){
        System.out.println("--- Les devis disponibles ---");
        List<Devis> allDevis = devisService.findAllDevis();

        for(Devis devis : allDevis){
            System.out.println("ID du devis: "+devis.getId());
            System.out.println("Devis du projet: "+devis.getProjet().getNom());
            System.out.println("Cout d estimation: "+devis.getMontantEstime());
            System.out.println("Date d'emission: "+devis.getDateEmission());
            System.out.println("Date de validité: "+devis.getDateValidite());
            if(devis.isAccepte()){
                System.out.println("Status : confirmer");
            }else{
                System.out.println("Status : refuser");
            }
            System.out.println("--------------------------------");

        }
    }
}
