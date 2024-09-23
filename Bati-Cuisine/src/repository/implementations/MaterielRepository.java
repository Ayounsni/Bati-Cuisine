package repository.implementations;

import db.DbFunctions;
import models.entities.Materiel;
import repository.interfaces.IMaterielRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<Materiel> findMaterielsByProjetId(UUID projetId) {
            String query = "SELECT * FROM materiaux WHERE projetid = ?";
            List<Materiel> materiels = new ArrayList<>();

            try (Connection conn = db.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setObject(1, projetId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Materiel materiel = new Materiel();
                    materiel.setNom(rs.getString("nom"));
                    materiel.setTauxTVA(rs.getFloat("tauxtva"));
                    materiel.setCoutUnitaire(rs.getBigDecimal("coutunitaire"));
                    materiel.setQuantite(rs.getFloat("quantite"));
                    materiel.setCoutTransport(rs.getBigDecimal("couttransport"));
                    materiel.setCoefficientQualite(rs.getFloat("coefficientqualite"));
                    materiels.add(materiel);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return materiels;
        }


}


