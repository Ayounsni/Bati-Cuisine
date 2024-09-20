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
    private Client client;
    private final List<Composant> composants;


    public Projet() {
        this.composants = new ArrayList<>();
    }

    public Projet(String nom, float surface, BigDecimal coutTotal, EtatProjet etatProjet, Client client) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.surface = surface;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
        this.composants = new ArrayList<>();
    }

    public Projet(String nom, float surface, Client client) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.surface = surface;
        this.coutTotal = null;
        this.etatProjet = ENCOURS;
        this.client = client;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Composant> getComposants() {
        return composants;
    }
}

