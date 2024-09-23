package ui;

import models.entities.MainDOeuvre;
import models.entities.Materiel;
import models.entities.Projet;
import services.implementations.MainDOeuvreService;
import services.implementations.MaterielService;
import services.implementations.ProjetService;
import services.interfaces.IComposantService;
import services.interfaces.IMainDOeuvreService;
import services.interfaces.IMaterielService;
import services.interfaces.IProjetService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MainDOeuvreUi {

    private final Scanner scanner = new Scanner(System.in);
    private final IMainDOeuvreService mainDOeuvreService = new MainDOeuvreService();
    private final IComposantService<MainDOeuvre> composantService = new MainDOeuvreService();
    private final IProjetService projetService = new ProjetService();

    public void ajouterMainDOeuvre(Projet projet) {
        boolean ajouterAutre = true;

        while (ajouterAutre) {
            System.out.println("--- Ajout de la main-d'œuvre ---");

            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String nom = scanner.nextLine();

            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            BigDecimal tauxHoraire = scanner.nextBigDecimal();

            System.out.print("Entrez le nombre d'heures travaillées : ");
            float heuresTravail = scanner.nextFloat();



            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            float productiviteOuvrier = scanner.nextFloat();

            scanner.nextLine();

            MainDOeuvre mainDOeuvre = new MainDOeuvre(nom, 0, projet, tauxHoraire, heuresTravail, productiviteOuvrier);
            projet.ajouterMainDOeuvre(mainDOeuvre);
            projet.ajouterComposant(mainDOeuvre);

            MainDOeuvre addedMainDOeuvre = mainDOeuvreService.addMainDOeuvre(mainDOeuvre);

            if (addedMainDOeuvre != null) {
                System.out.println("Main-d'œuvre ajoutée avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout de la main-d'œuvre.");
            }

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (oui/non) : ");
            String reponse = scanner.nextLine();
            ajouterAutre = reponse.equalsIgnoreCase("oui");
        }
    }
    public BigDecimal listMainDOeuvre(Projet projet){
        List<MainDOeuvre> mainDOeuvresDuProjet = projet.getMainDOeuvres();
        for (MainDOeuvre mainDOeuvre : mainDOeuvresDuProjet) {
            System.out.println("-" + mainDOeuvre.getNom() + ":" + composantService.calculerCoutTotal(mainDOeuvre) +"€ (taux horaire : " +
                    mainDOeuvre.getTauxHoraire() + "€/h, heures travaillées :" + mainDOeuvre.getHeuresTravail() + "h, productivité :"+
                    mainDOeuvre.getProductiviteOuvrier());
        }
        BigDecimal total = mainDOeuvreService.calculerTotalMainDOeuvre(projet);
        System.out.println("**Coût total des matériaux avant TVA : " + total+"€**\n");
        BigDecimal totalAfterTva = projetService.calculerCoutApresTva(total,projet);
        System.out.println("**Coût total des matériaux avec TVA ("+ projet.getTva()+"%) : " + totalAfterTva +"€**\n");
        return totalAfterTva;

    }
}
