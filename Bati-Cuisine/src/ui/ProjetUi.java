package ui;


import models.entities.Client;
import models.entities.Materiel;
import models.entities.Projet;
import services.implementations.MaterielService;
import services.implementations.ProjetService;
import services.interfaces.IComposantService;
import services.interfaces.IMaterielService;
import services.interfaces.IProjetService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProjetUi {

    private final Scanner scanner = new Scanner(System.in);
    private final IProjetService projetService = new ProjetService();
    private final IMaterielService materielService = new MaterielService();
    private final IComposantService<Materiel> composantService = new MaterielService();
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
            mainDOeuvreUi.ajouterMainDOeuvre(projet);
        }else{
            System.out.println("projet not added!!");
        }
    }
    public void coutTotal(Projet projet) {

        System.out.println("--- Calcul du coût total ---");

        System.out.print("Souhaitez-vous appliquer une TVA au projet ? (oui/non) : ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("oui")) {
            System.out.print("Entrez le pourcentage de TVA (%) : ");
            BigDecimal tvaPourcentage = scanner.nextBigDecimal();
            projetService.updateTva(projet, tvaPourcentage);

        }

        System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (oui/non) : ");
        String respons = scanner.nextLine();
        if (respons.equalsIgnoreCase("oui")) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            float marge = scanner.nextFloat();
            projetService.updateMargeBeneficiaire(projet,marge);
        }

        System.out.println("Calcul du coût en cours...\n");

        System.out.println("--- Résultat du Calcul ---");
        System.out.println("Nom du projet :" + projet.getNom());
        System.out.println("Client :" + projet.getClient().getNom());
        System.out.println("Adresse du chantier :" + projet.getClient().getAdresse());
        System.out.println("Surface :" + projet.getSurface() + "m²");

        System.out.println("--- Détail des Coûts ---");

        System.out.println("1. Matériaux :\n");

        List<Materiel> materielsDuProjet = projet.getMateriels();
        for (Materiel materiel : materielsDuProjet) {
            System.out.println("-" + materiel.getNom() + ":" + composantService.calculerCoutTotal(materiel) +"€ (quantité : " +
                    materiel.getQuantite() + "m², coût unitaire :" + materiel.getCoutUnitaire() + "€/m², qualité :"+
                    materiel.getCoefficientQualite()+  ", transport :" + materiel.getCoutTransport() + "€)");
        }
        BigDecimal total = materielService.calculerTotalMateriel(projet);
        System.out.println("Total de materiels : " + total);


    }
}
