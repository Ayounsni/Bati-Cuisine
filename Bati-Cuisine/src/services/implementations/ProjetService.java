package services.implementations;

import models.entities.Projet;
import repository.implementations.ProjetRepository;
import repository.interfaces.IProjetRepository;
import services.interfaces.IProjetService;

public class ProjetService implements IProjetService {

    private final IProjetRepository projetRepository;

    public ProjetService() {
        projetRepository = new ProjetRepository();
    }

    @Override
    public Projet addProjet(Projet projet) {
        return projetRepository.addProjet(projet);
    }
}
