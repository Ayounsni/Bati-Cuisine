package services.implementations;

import models.entities.Devis;
import repository.implementations.DevisRepository;
import repository.interfaces.IDevisRepository;
import services.interfaces.IDevisService;

import java.util.UUID;

public class DevisService implements IDevisService {

    private final IDevisRepository devisRepository;

    public DevisService() {
        this.devisRepository = new DevisRepository();

    }
    @Override
    public Devis addDevis(Devis devis) {
        return devisRepository.addDevis(devis);
    }

    @Override
    public Devis getById(UUID id) {
        return devisRepository.getById(id);
    }

    @Override
    public Devis getDevisByProjetId(UUID projetId) {
        return devisRepository.getDevisByProjetId(projetId);
    }
}
