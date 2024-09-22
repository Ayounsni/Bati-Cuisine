package ui;

import models.entities.Materiel;
import models.entities.Projet;
import services.implementations.MaterielService;
import services.interfaces.IComposantService;
import services.interfaces.IMaterielService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MaterielUi {
    private final Scanner scanner = new Scanner(System.in);
    private final IMaterielService materielService = new MaterielService();
    private final IComposantService<Materiel> composantService = new MaterielService();

    public void ajouterMateriaux(Projet projet) {
        boolean ajouterAutre = true;

        while (ajouterAutre) {
            System.out.println("--- Ajout des matériaux ---");

            System.out.print("Entrez le nom du matériau : ");
            String nom = scanner.nextLine();

            System.out.print("Entrez la quantité de ce matériau (ex : m² ou litres) : ");
            float quantite = scanner.nextFloat();

            System.out.print("Entrez le coût unitaire de ce matériau (€/unité) : ");
            BigDecimal coutUnitaire = scanner.nextBigDecimal();

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            BigDecimal coutTransport = scanner.nextBigDecimal();



            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            float coefficientQualite = scanner.nextFloat();

            scanner.nextLine();

            Materiel materiel = new Materiel(nom, 1, projet, coutUnitaire, quantite, coutTransport, coefficientQualite);
            projet.ajouterMateriel(materiel);
            projet.ajouterComposant(materiel);

            Materiel addedMateriel = materielService.addMateriel(materiel);

            if (addedMateriel != null) {
                System.out.println("Matériau ajouté avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout du matériau.");
            }

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            String reponse = scanner.nextLine();
            ajouterAutre = reponse.equalsIgnoreCase("y");
        }
    }

    public void listMateriel(Projet projet) {
        List<Materiel> materielsDuProjet = projet.getMateriels();
        for (Materiel materiel : materielsDuProjet) {
            System.out.println("-" + materiel.getNom() + ":" + composantService.calculerCoutTotal(materiel) +"€ (quantité : " +
                    materiel.getQuantite() + "m², coût unitaire :" + materiel.getCoutUnitaire() + "€/m², qualité :"+
                    materiel.getCoefficientQualite()+  ", transport :" + materiel.getCoutTransport() + "€)");
        }
    }
}

