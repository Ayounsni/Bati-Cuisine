package repository.implementations;

import db.DbFunctions;
import models.entities.*;
import models.enums.EtatProjet;
import repository.interfaces.IProjetRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjetRepository implements IProjetRepository {

    private final DbFunctions db;
    private final ClientRepository clientRepository = new ClientRepository();
    private final ComposantRepository composantRepository = new ComposantRepository();
    private final MaterielRepository materielRepository = new MaterielRepository();
    private final MainDOeuvreRepository mainDOeuvreRepository = new MainDOeuvreRepository();

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
    @Override
    public Projet findProjetById(UUID projetId) {
        String query = "SELECT * FROM projets WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setObject(1, projetId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Projet projet = new Projet();
                projet.setId(UUID.fromString(rs.getString("id")));
                projet.setNom(rs.getString("nom"));
                projet.setSurface(rs.getFloat("surface"));
                projet.setCoutTotal(rs.getBigDecimal("couttotal"));
                projet.setEtatProjet(EtatProjet.valueOf(rs.getString("etatprojet")));
                projet.setMargeBeneficiaire(rs.getFloat("margebeneficiaire"));
                projet.setTva(rs.getFloat("tva"));

                UUID clientId = UUID.fromString(rs.getString("clientid"));
                Client client = clientRepository.findClientById(clientId);
                projet.setClient(client);

                List<Materiel> materiels = materielRepository.findMaterielsByProjetId(projetId);
                for(Materiel materiel : materiels) {
                    projet.ajouterComposant(materiel);
                }
                List<MainDOeuvre> mainDOeuvres = mainDOeuvreRepository.findMainDOeuvresByProjetId(projetId);
                for(MainDOeuvre mainDOeuvre : mainDOeuvres) {
                    projet.ajouterComposant(mainDOeuvre);
                }


                return projet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Projet> findAllProjets() {
        String query = "SELECT * FROM projets";
        List<Projet> projets = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Projet projet = new Projet();
                projet.setId(UUID.fromString(rs.getString("id")));
                projet.setNom(rs.getString("nom"));
                projet.setSurface(rs.getFloat("surface"));
                projet.setCoutTotal(rs.getBigDecimal("couttotal"));
                projet.setEtatProjet(EtatProjet.valueOf(rs.getString("etatprojet")));
                projet.setMargeBeneficiaire(rs.getFloat("margebeneficiaire"));
                projet.setTva(rs.getFloat("tva"));

                UUID clientId = UUID.fromString(rs.getString("clientid"));
                Client client = clientRepository.findClientById(clientId);
                projet.setClient(client);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projets;
    }


}
