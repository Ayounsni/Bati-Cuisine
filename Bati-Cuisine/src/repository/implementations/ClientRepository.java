package repository.implementations;

import db.DbFunctions;
import models.entities.Client;
import repository.interfaces.IClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ClientRepository implements IClientRepository {

    private final DbFunctions db;

    public ClientRepository() {
        this.db = DbFunctions.getInstance();
    }
    @Override
    public Client addClient(Client client) {
        String query = "INSERT INTO clients(id, nom, adresse, telephone, estprofessionnel, remise)VALUES (?, ?, ?, ?, ?,?)";
        try(Connection conn = db.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setObject(1, client.getId());
            statement.setString(2, client.getNom());
            statement.setString(3, client.getAdresse());
            statement.setString(4, client.getTelephone());
            statement.setBoolean(5,client.isEstProfessionnel());
            statement.setFloat(6,client.getRemise());
            statement.executeUpdate();
        return client;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client findClientByName(String nom) {
        String query = "SELECT * FROM clients WHERE nom = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setId(UUID.fromString(rs.getString("id")));
                client.setNom(rs.getString("nom"));
                client.setAdresse(rs.getString("adresse"));
                client.setTelephone(rs.getString("telephone"));
                client.setEstProfessionnel(rs.getBoolean("estProfessionnel"));
                client.setRemise(rs.getFloat("remise"));
                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findClientById(UUID clientId) {
        String query = "SELECT * FROM clients WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setObject(1, clientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setId(UUID.fromString(rs.getString("id")));
                client.setNom(rs.getString("nom"));
                client.setAdresse(rs.getString("adresse"));
                client.setTelephone(rs.getString("telephone"));
                client.setEstProfessionnel(rs.getBoolean("estProfessionnel"));
                client.setRemise(rs.getFloat("remise"));
                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean updateClient(Client client) {
        String query = "UPDATE clients SET nom = ?, adresse = ?, telephone = ?, estProfessionnel = ? WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdresse());
            stmt.setString(3, client.getTelephone());
            stmt.setBoolean(4, client.isEstProfessionnel());
            stmt.setObject(5, client.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteClient(UUID clientId) {
        String query = "DELETE FROM clients WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setObject(1, clientId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
