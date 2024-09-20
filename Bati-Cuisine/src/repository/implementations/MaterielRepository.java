package repository.implementations;

import db.DbFunctions;
import models.entities.Materiel;
import repository.interfaces.IMaterielRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterielRepository implements IMaterielRepository {

    private final DbFunctions db;

    public MaterielRepository() {
        this.db = DbFunctions.getInstance();
    }

    @Override
    public Materiel addMateriel(Materiel materiel) {
        String query = "INSERT INTO materiaux(id, nom, tauxTVA, typecomposant , projetId, coutUnitaire, quantite, coutTransport, coefficientQualite) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = db.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setObject(1, materiel.getId());
            statement.setString(2, materiel.getNom());
            statement.setFloat(3, materiel.getTauxTVA());
            statement.setObject(4, materiel.getTypeComposant().name(), java.sql.Types.OTHER);
            statement.setObject(5, materiel.getProjet().getId());
            statement.setBigDecimal(6, materiel.getCoutUnitaire());
            statement.setFloat(7, materiel.getQuantite());
            statement.setBigDecimal(8, materiel.getCoutTransport());
            statement.setFloat(9, materiel.getCoefficientQualite());

            statement.executeUpdate();
            return materiel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


