package services.interfaces;

import models.entities.Projet;

import java.math.BigDecimal;

public interface IProjetService {

    Projet addProjet(Projet projet);
    boolean updateMargeBeneficiaire(Projet projet, float margeBeneficiaire);
    void updateTva(Projet projet, BigDecimal tva);
    boolean updateTvaProjet(Projet projet, float tva);
    BigDecimal calculerCoutApresTva(BigDecimal coutTotal, Projet projet);
}
