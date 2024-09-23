package repository.interfaces;

import models.entities.Composant;
import models.entities.Projet;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IComposantRepository {

    boolean updateTva(Composant composant, BigDecimal tva);
    List<Composant> findComposantsByProjetId(UUID projetId);
}
