package services.interfaces;

import models.entities.MainDOeuvre;
import models.entities.Projet;

import java.math.BigDecimal;

public interface IMainDOeuvreService {
    MainDOeuvre addMainDOeuvre(MainDOeuvre mainDOeuvre);
    BigDecimal calculerTotalMainDOeuvre(Projet projet);
}
