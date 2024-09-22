package services.interfaces;

import models.entities.Projet;

import java.math.BigDecimal;

public interface IProjetService {

    Projet addProjet(Projet projet);
    boolean updateMargeBeneficiaire(Projet projet, float margeBeneficiaire);
     void updateTva(Projet projet, BigDecimal tva);
}
