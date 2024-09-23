package repository.interfaces;

import models.entities.Projet;

public interface IProjetRepository {

    Projet addProjet(Projet projet);
    boolean updateMargeBeneficiaire(Projet projet, float margeBeneficiaire);
    boolean updateTva(Projet projet, float tva);
}
