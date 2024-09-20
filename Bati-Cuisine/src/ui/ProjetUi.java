package ui;


import models.entities.Client;
import models.entities.Projet;
import services.implementations.ProjetService;
import services.interfaces.IProjetService;

import java.util.Scanner;

public class ProjetUi {

    private final Scanner scanner = new Scanner(System.in);
    private final IProjetService projetService = new ProjetService();

    public void addProjet(Client client) {
        System.out.print("Entrez le nom du projet : ");
        String nomProjet = scanner.nextLine();
        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        float surface = scanner.nextFloat();

        Projet projet =  new Projet(nomProjet,surface,client);
        Projet addProjet =projetService.addProjet(projet);
        if(addProjet != null) {
            System.out.println("projet added!!");
        }else{
            System.out.println("projet not added!!");
        }


    }
}
