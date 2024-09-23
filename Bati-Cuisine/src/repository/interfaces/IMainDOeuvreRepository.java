package repository.interfaces;

import models.entities.MainDOeuvre;

import java.util.List;
import java.util.UUID;

public interface IMainDOeuvreRepository {
    MainDOeuvre addMainDOeuvre(MainDOeuvre mainDOeuvre);
    List<MainDOeuvre> findMainDOeuvresByProjetId(UUID projetId);
}
