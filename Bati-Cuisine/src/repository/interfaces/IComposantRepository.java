package repository.interfaces;

import models.entities.Composant;
import models.entities.Projet;

import java.math.BigDecimal;

public interface IComposantRepository {

    boolean updateTva(Composant composant, BigDecimal tva);
}
