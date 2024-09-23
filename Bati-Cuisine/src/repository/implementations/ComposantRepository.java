package repository.implementations;

import db.DbFunctions;
import models.entities.Composant;
import models.enums.TypeComposant;
import repository.interfaces.IComposantRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComposantRepository implements IComposantRepository {

    public DbFunctions db ;
    public ComposantRepository() {
        this.db= DbFunctions.getInstance();
    }

    @Override
    public boolean updateTva(Composant composant, BigDecimal tva) {
        String sql = "UPDATE composants SET tauxtva = ? WHERE id = ?";

        try (Connection conn = db.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setBigDecimal(1, tva);
            statement.setObject(2, composant.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Composant> findComposantsByProjetId(UUID projetId) {
        String query = "SELECT * FROM composants WHERE projetid = ?";
        List<Composant> composants = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setObject(1, projetId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Composant composant = new Composant();
                composant.setNom(rs.getString("nom"));
                composant.setTauxTVA(rs.getFloat("tva"));
                composant.setTypeComposant(TypeComposant.valueOf(rs.getString("typecomposant")));
                composants.add(composant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return composants;
    }

}
