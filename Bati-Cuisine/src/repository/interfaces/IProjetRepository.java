package repository.interfaces;

import models.entities.Projet;

import java.math.BigDecimal;

public interface IProjetRepository {

    Projet addProjet(Projet projet);
    boolean updateMargeBeneficiaire(Projet projet, float margeBeneficiaire);
    boolean updateTva(Projet projet, float tva);
    boolean updateCoutTotal(Projet projet, BigDecimal coutTotal);
}
