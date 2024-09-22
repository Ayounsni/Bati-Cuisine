package repository.implementations;

import db.DbFunctions;
import models.entities.Composant;
import repository.interfaces.IComposantRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
