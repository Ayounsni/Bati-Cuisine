package repository.interfaces;

import models.entities.Materiel;

import java.util.List;
import java.util.UUID;

public interface IMaterielRepository {
    Materiel addMateriel(Materiel materiel);
}
