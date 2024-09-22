package ui;


import models.entities.Client;
import models.entities.Materiel;
import models.entities.Projet;
import services.implementations.MaterielService;
import services.implementations.ProjetService;
import services.interfaces.IMaterielService;
import services.interfaces.IProjetService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProjetUi {

    private final Scanner scanner = new Scanner(System.in);
    private final IProjetService projetService = new ProjetService();
    private final IMaterielService materielService = new MaterielService();
    private final MaterielUi materielUi = new MaterielUi();
    private final MainDOeuvreUi mainDOeuvreUi = new MainDOeuvreUi();

    public void addProjet(Client client) {
        System.out.print("Entrez le nom du projet : ");
        String nomProjet = scanner.nextLine();
        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        float surface = scanner.nextFloat();

        Projet projet =  new Projet(nomProjet,surface,client);
        Projet addProjet =projetService.addProjet(projet);
        if(addProjet != null) {
            materielUi.ajouterMateriaux(projet);
            List<Materiel> materielsDuProjet = projet.getMateriels();
            System.out.println("Nombre de matériaux dans le projet : " + materielsDuProjet.size());
            for (Materiel materiel : materielsDuProjet) {
                System.out.println("-" + materiel.getNom() + ":" + materielService.calculerCoutTotal(materiel) +"€ (quantité : " +
                        materiel.getQuantite() + "m², coût unitaire :" + materiel.getCoutUnitaire() + "€/m², qualité :"+
                        materiel.getCoefficientQualite()+  ", transport :" + materiel.getCoutTransport() + "€)");
            }
            BigDecimal total = materielService.calculerTotalMateriel(projet);
            System.out.println("Total de materiels : " + total);

            mainDOeuvreUi.ajouterMainDOeuvre(projet);
        }else{
            System.out.println("projet not added!!");
        }


    }
}
