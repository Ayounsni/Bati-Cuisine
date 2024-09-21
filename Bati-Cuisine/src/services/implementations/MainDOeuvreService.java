package services.implementations;

import models.entities.MainDOeuvre;
import repository.implementations.MainDOeuvreRepository;
import repository.interfaces.IMainDOeuvreRepository;
import services.interfaces.IMainDOeuvreService;

public class MainDOeuvreService implements IMainDOeuvreService {

    private final IMainDOeuvreRepository mainDOeuvreRepository;
    public MainDOeuvreService() {
        mainDOeuvreRepository = new MainDOeuvreRepository();
    }
    @Override
    public MainDOeuvre addMainDOeuvre(MainDOeuvre mainDOeuvre) {
         return mainDOeuvreRepository.addMainDOeuvre(mainDOeuvre) ;
    }
}
