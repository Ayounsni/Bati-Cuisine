package repository.interfaces;

import models.entities.Projet;

import java.math.BigDecimal;
import java.util.UUID;

public interface IProjetRepository {

    Projet addProjet(Projet projet);
    boolean updateMargeBeneficiaire(Projet projet, float margeBeneficiaire);
    boolean updateTva(Projet projet, float tva);
    boolean updateCoutTotal(Projet projet, BigDecimal coutTotal);
    Projet findProjetById(UUID projetId);
}
