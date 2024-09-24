package services.interfaces;

import models.entities.Devis;

import java.util.List;
import java.util.UUID;

public interface IDevisService {
    Devis addDevis(Devis devis);
    Devis getById(UUID id);
    Devis getDevisByProjetId(UUID projetId);
    List<Devis> findAllDevis();
}
