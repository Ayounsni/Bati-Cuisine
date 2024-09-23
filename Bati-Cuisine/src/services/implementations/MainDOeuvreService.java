package services.implementations;

import models.entities.MainDOeuvre;
import models.entities.Materiel;
import models.entities.Projet;
import repository.implementations.MainDOeuvreRepository;
import repository.interfaces.IMainDOeuvreRepository;
import services.interfaces.IComposantService;
import services.interfaces.IMainDOeuvreService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MainDOeuvreService implements IMainDOeuvreService, IComposantService<MainDOeuvre> {

    private final IMainDOeuvreRepository mainDOeuvreRepository;
    public MainDOeuvreService() {
        mainDOeuvreRepository = new MainDOeuvreRepository();
    }
    @Override
    public MainDOeuvre addMainDOeuvre(MainDOeuvre mainDOeuvre) {
         return mainDOeuvreRepository.addMainDOeuvre(mainDOeuvre) ;
    }

    @Override
    public BigDecimal calculerTotalMainDOeuvre(Projet projet) {
        List<MainDOeuvre> mainDOeuvresDuProjet = projet.getMainDOeuvres();

        return mainDOeuvresDuProjet.stream()
                .map(this::calculerCoutTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calculerCoutTotal(MainDOeuvre mainDOeuvre) {
        BigDecimal heuresTravail = BigDecimal.valueOf(mainDOeuvre.getHeuresTravail());
        BigDecimal tauxHoraire = mainDOeuvre.getTauxHoraire();
        BigDecimal productiviteOuvrier = BigDecimal.valueOf(mainDOeuvre.getProductiviteOuvrier());

        BigDecimal coutTotal = tauxHoraire.multiply(heuresTravail)
                .multiply(productiviteOuvrier)
                .setScale(2, RoundingMode.HALF_UP);
        return coutTotal ;
    }
}
