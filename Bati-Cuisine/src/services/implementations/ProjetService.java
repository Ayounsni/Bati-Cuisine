package services.implementations;

import models.entities.Composant;
import models.entities.Materiel;
import models.entities.Projet;
import repository.implementations.ComposantRepository;
import repository.implementations.ProjetRepository;
import repository.interfaces.IComposantRepository;
import repository.interfaces.IProjetRepository;
import services.interfaces.IProjetService;

import java.math.BigDecimal;
import java.util.List;

public class ProjetService implements IProjetService {

    private final IProjetRepository projetRepository;
    private final IComposantRepository composantRepository;

    public ProjetService() {
        projetRepository = new ProjetRepository();
        composantRepository = new ComposantRepository();
    }

    @Override
    public Projet addProjet(Projet projet) {
        return projetRepository.addProjet(projet);

    }

    @Override
    public boolean updateMargeBeneficiaire(Projet projet, float margeBeneficiaire) {
        return projetRepository.updateMargeBeneficiaire(projet, margeBeneficiaire);
    }

    public void updateTva(Projet projet, BigDecimal tva) {
        List<Composant> composantsDuProjet = projet.getComposants();
        for (Composant composant : composantsDuProjet){
            composantRepository.updateTva(composant, tva);
        }
    }
}
