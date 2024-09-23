package repository.implementations;

import db.DbFunctions;
import models.entities.MainDOeuvre;
import repository.interfaces.IMainDOeuvreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MainDOeuvreRepository implements IMainDOeuvreRepository {

    public DbFunctions db ;
    public MainDOeuvreRepository() {
        this.db= DbFunctions.getInstance();
    }
    @Override
    public MainDOeuvre addMainDOeuvre(MainDOeuvre mainDOeuvre) {
        String query = "INSERT INTO maindoeuvres(id, nom, tauxTVA, typecomposant , projetId, tauxhoraire, heurestravail, productiviteouvrier) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setObject(1, mainDOeuvre.getId());
            statement.setString(2, mainDOeuvre.getNom());
            statement.setFloat(3, mainDOeuvre.getTauxTVA());
            statement.setObject(4, mainDOeuvre.getTypeComposant().name(), java.sql.Types.OTHER);
            statement.setObject(5, mainDOeuvre.getProjet().getId());
            statement.setBigDecimal(6, mainDOeuvre.getTauxHoraire());
            statement.setFloat(7, mainDOeuvre.getHeuresTravail());
            statement.setFloat(8, mainDOeuvre.getProductiviteOuvrier());

            statement.executeUpdate();
            return mainDOeuvre;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<MainDOeuvre> findMainDOeuvresByProjetId(UUID projetId) {
        String query = "SELECT * FROM maindoeuvres WHERE projetid = ?";
        List<MainDOeuvre> mainDOeuvres = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setObject(1, projetId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MainDOeuvre mainDOeuvre = new MainDOeuvre();
                mainDOeuvre.setNom(rs.getString("nom"));
                mainDOeuvre.setTauxTVA(rs.getFloat("tauxtva"));
                mainDOeuvre.setTauxHoraire(rs.getBigDecimal("tauxhoraire"));
                mainDOeuvre.setHeuresTravail(rs.getFloat("heurestravail"));
                mainDOeuvre.setProductiviteOuvrier(rs.getFloat("productiviteouvrier"));
                mainDOeuvres.add(mainDOeuvre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mainDOeuvres;
    }


}
