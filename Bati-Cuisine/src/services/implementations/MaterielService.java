package services.implementations;

import models.entities.Materiel;
import repository.implementations.MaterielRepository;
import repository.interfaces.IMaterielRepository;
import services.interfaces.IMaterielService;

public class MaterielService implements IMaterielService {

    private IMaterielRepository materielRepository ;

    public MaterielService() {
        this.materielRepository = new MaterielRepository() ;
    }


    @Override
    public Materiel addMateriel(Materiel materiel) {
        return materielRepository.addMateriel(materiel) ;
    }
}
