import db.DbFunctions;
import ui.MainMenu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.showMainMenu();
    }
}