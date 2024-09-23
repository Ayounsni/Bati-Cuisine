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
import java.util.UUID;

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

    @Override
    public boolean updateTvaProjet(Projet projet, float tva) {
        return projetRepository.updateTva(projet, tva);
    }
    @Override
    public BigDecimal calculerCoutApresTva(BigDecimal coutTotal, Projet projet) {
        BigDecimal tvaDecimal = BigDecimal.valueOf(projet.getTva()).divide(BigDecimal.valueOf(100));
        BigDecimal montantTva = coutTotal.multiply(tvaDecimal);
        return coutTotal.add(montantTva);
    }
    @Override
    public BigDecimal calculerMargeBenificiaire(Projet projet , BigDecimal coutTotal) {
        BigDecimal margeDecimal = BigDecimal.valueOf(projet.getMargeBeneficiaire()).divide(BigDecimal.valueOf(100));
        return coutTotal.multiply(margeDecimal);}

    @Override
    public boolean updateCoutTotal(Projet projet, BigDecimal coutTotal) {
        return projetRepository.updateCoutTotal(projet, coutTotal);
    }

    @Override
    public Projet findProjetById(UUID projetId) {
        return projetRepository.findProjetById(projetId);
    }
}
