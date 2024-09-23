package models.entities;

import models.enums.EtatProjet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static models.enums.EtatProjet.ENCOURS;

public class Projet {
    private UUID id;
    private String nom;
    private float surface;
    private BigDecimal coutTotal;
    private EtatProjet etatProjet;
    private float margeBeneficiaire;
    private float tva;
    private Client client;
    private final List<Composant> composants ;
    private final List<MainDOeuvre> mainDOeuvres;
    private final List<Materiel> materiels;


    public Projet() {
        this.mainDOeuvres = new ArrayList<>();
        this.materiels = new ArrayList<>();
        this.composants = new ArrayList<>();
    }

    public Projet(String nom, float surface, BigDecimal coutTotal, EtatProjet etatProjet,float margeBeneficiaire,float tva, Client client) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.surface = surface;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.tva = tva;
        this.client = client;
        this.mainDOeuvres = new ArrayList<>();
        this.materiels = new ArrayList<>();
        this.composants = new ArrayList<>();
    }

    public Projet(String nom, float surface, Client client) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.surface = surface;
        this.coutTotal = null;
        this.etatProjet = ENCOURS;
        this.margeBeneficiaire = 0;
        this.tva = 0;
        this.client = client;
        this.mainDOeuvres = new ArrayList<>();
        this.materiels = new ArrayList<>();
        this.composants = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public BigDecimal getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(BigDecimal coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }
    public float getMargeBeneficiaire() {
        return margeBeneficiaire;
    }
    public void setMargeBeneficiaire(float margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<MainDOeuvre> getMainDOeuvres() {
        return mainDOeuvres;
    }

    public float getTva(){
        return tva;
    }
    public void setTva(float tva) {
        this.tva = tva;
    }

    public void ajouterMateriel(Materiel materiel) {
        this.materiels.add(materiel);
    }


    public List<Materiel> getMateriels() {
        return materiels;
    }

    public void ajouterMainDOeuvre(MainDOeuvre mainDOeuvre) {
        this.mainDOeuvres.add(mainDOeuvre);
    }
    public List<Composant> getComposants() {
        return composants;
    }
    public void ajouterComposant(Composant composant) {
        this.composants.add(composant);
    }

}

