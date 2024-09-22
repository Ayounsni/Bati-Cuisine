package services.interfaces;

import models.entities.Materiel;
import models.entities.Projet;

import java.math.BigDecimal;

public interface IMaterielService {
    Materiel addMateriel(Materiel materiel);
    BigDecimal calculerTotalMateriel( Projet projet);
}
