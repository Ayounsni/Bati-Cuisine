package repository.implementations;

import db.DbFunctions;
import models.entities.Projet;
import repository.interfaces.IProjetRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjetRepository implements IProjetRepository {

    private final DbFunctions db;

    public ProjetRepository() {
        this.db = DbFunctions.getInstance();
    }

    @Override
    public Projet addProjet(Projet projet) {
        String query = "INSERT INTO projets(id, nom, surface, couttotal, etatprojet,margeBeneficiaire, clientid) VALUES (?,?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setObject(1, projet.getId());
            statement.setString(2, projet.getNom());
            statement.setFloat(3, projet.getSurface());
            statement.setObject(4, projet.getCoutTotal());
            statement.setObject(5, projet.getEtatProjet().name(), java.sql.Types.OTHER);
            statement.setFloat(6, projet.getMargeBeneficiaire());
            statement.setObject(7, projet.getClient().getId());

            statement.executeUpdate();
            return projet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean updateMargeBeneficiaire(Projet projet, float margeBeneficiaire) {
        projet.setMargeBeneficiaire(margeBeneficiaire);
        String sql = "UPDATE projets SET margeBeneficiaire = ? WHERE id = ?";

        try (Connection conn = db.getConnection() ;PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setFloat(1, margeBeneficiaire);
            statement.setObject(2, projet.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean updateTva(Projet projet, float tva) {
        projet.setTva(tva);
        String sql = "UPDATE projets SET tva = ? WHERE id = ?";

        try (Connection conn = db.getConnection() ;PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setFloat(1, tva);
            statement.setObject(2, projet.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean updateCoutTotal(Projet projet, BigDecimal coutTotal) {
        projet.setCoutTotal(coutTotal);
        String sql = "UPDATE projets SET couttotal = ? WHERE id = ?";

        try (Connection conn = db.getConnection() ;PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setBigDecimal(1, coutTotal);
            statement.setObject(2, projet.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
