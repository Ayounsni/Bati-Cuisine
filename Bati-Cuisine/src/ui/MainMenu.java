package ui;

import db.DbFunctions;

import java.util.Scanner;

public class MainMenu {


    private final Scanner scanner = new Scanner(System.in);

    private final DbFunctions db = DbFunctions.getInstance();

    private final ClientUi clientUi = new ClientUi();
    private final ProjetUi projetUi = new ProjetUi();


    public void showMainMenu() {
        boolean running = true;


        while (running) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("    1. Créer un nouveau projet");
            System.out.println("    2. Afficher les projets existants");
            System.out.println("    3. Calculer le coût d'un projet");
            System.out.println("    4. Quitter");

            System.out.print("Choisissez une option :");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();


            switch (mainChoice) {
                case 1:
                    clientUi.manageClient();
                    break;
                case 2:

                    break;
                case 3:
                    projetUi.calculerCoutProjet();
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    running = false;
                    System.out.println("Quitter le système.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir à nouveau.");
            }
        }
        scanner.close();
        db.closeConnection();
    }

}
