package repository.implementations;

import db.DbFunctions;
import models.entities.Client;
import models.entities.Devis;
import models.entities.Projet;
import models.enums.EtatProjet;
import repository.interfaces.IDevisRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DevisRepository implements IDevisRepository {

    private final DbFunctions db;
    private final ProjetRepository projetRepository = new ProjetRepository();

    public DevisRepository() {
        this.db = DbFunctions.getInstance();
    }
    @Override
    public Devis addDevis(Devis devis) {
        String query = "INSERT INTO public.devis(id, montantestime, dateemission, datevalidite, accepte, projetid) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Assignation des valeurs des paramètres
            statement.setObject(1, devis.getId());
            statement.setBigDecimal(2, devis.getMontantEstime());
            statement.setObject(3, devis.getDateEmission());
            statement.setObject(4, devis.getDateValidite());
            statement.setBoolean(5, devis.isAccepte());
            statement.setObject(6, devis.getProjet().getId());


            statement.executeUpdate();
            return devis;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Devis getById(UUID id) {
        String query = "SELECT id, montantestime, dateemission, datevalidite, accepte, projetid "
                + "FROM public.devis WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Devis devis = new Devis();
                devis.setId(UUID.fromString(resultSet.getString("id")));
                devis.setMontantEstime(resultSet.getBigDecimal("montantestime"));
                devis.setDateEmission(resultSet.getObject("dateemission", LocalDate.class));
                devis.setDateValidite(resultSet.getObject("datevalidite", LocalDate.class));
                devis.setAccepte(resultSet.getBoolean("accepte"));

                UUID projetId = UUID.fromString(resultSet.getString("projetid"));
                Projet projet =projetRepository.findProjetById(projetId);
                devis.setProjet(projet);

                return devis;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Devis getDevisByProjetId(UUID projetId) {
        String query = "SELECT id, montantestime, dateemission, datevalidite, accepte, projetid "
                + "FROM public.devis WHERE projetid = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setObject(1, projetId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Devis devis = new Devis();
                devis.setId(UUID.fromString(resultSet.getString("id")));
                devis.setMontantEstime(resultSet.getBigDecimal("montantestime"));
                devis.setDateEmission(resultSet.getObject("dateemission", LocalDate.class));
                devis.setDateValidite(resultSet.getObject("datevalidite", LocalDate.class));
                devis.setAccepte(resultSet.getBoolean("accepte"));

                Projet projet =projetRepository.findProjetById(projetId);
                devis.setProjet(projet);

                return devis;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Devis> findAllDevis() {
        String query = "SELECT * FROM devis";
        List<Devis> allDevis = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Devis devis = new Devis();
                devis.setId(UUID.fromString(rs.getString("id")));
                devis.setMontantEstime(rs.getBigDecimal("montantestime"));
                devis.setDateEmission(rs.getObject("dateemission", LocalDate.class));
                devis.setDateValidite(rs.getObject("datevalidite", LocalDate.class));
                devis.setAccepte(rs.getBoolean("accepte"));
                UUID projetId = UUID.fromString(rs.getString("projetId"));

                Projet projet =projetRepository.findProjetById(projetId);
                devis.setProjet(projet);
                allDevis.add(devis);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allDevis;
    }
}
