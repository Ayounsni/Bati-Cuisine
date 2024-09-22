package services.implementations;

import models.entities.Materiel;
import models.entities.Projet;
import repository.implementations.MaterielRepository;
import repository.interfaces.IMaterielRepository;
import services.interfaces.IComposantService;
import services.interfaces.IMaterielService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MaterielService implements IMaterielService, IComposantService<Materiel> {

    private IMaterielRepository materielRepository ;

    public MaterielService() {
        this.materielRepository = new MaterielRepository() ;
    }


    @Override
    public Materiel addMateriel(Materiel materiel) {
        return materielRepository.addMateriel(materiel) ;
    }
    @Override
    public BigDecimal calculerCoutTotal(Materiel materiel) {

        BigDecimal quantite = BigDecimal.valueOf(materiel.getQuantite());
        BigDecimal coutUnitaire = materiel.getCoutUnitaire();
        BigDecimal qualite = BigDecimal.valueOf(materiel.getCoefficientQualite());
        BigDecimal transport = materiel.getCoutTransport();


        BigDecimal coutTotal = quantite.multiply(coutUnitaire)
                .multiply(qualite)
                .add(transport)
                .setScale(2, RoundingMode.HALF_UP);

        return coutTotal;
    }


    @Override
    public BigDecimal calculerTotalMateriel(Projet projet) {
    List<Materiel> materielsDuProjet = projet.getMateriels();

    return materielsDuProjet.stream()
            .map(this::calculerCoutTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
