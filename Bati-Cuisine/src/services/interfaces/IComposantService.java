package services.interfaces;

import models.entities.Composant;
import models.entities.Materiel;

import java.math.BigDecimal;

public interface IComposantService<T> {

    BigDecimal calculerCoutTotal(T composant);
}
