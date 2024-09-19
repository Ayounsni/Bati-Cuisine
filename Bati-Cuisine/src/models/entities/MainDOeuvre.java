package models.entities;

import models.enums.TypeComposant;

import java.math.BigDecimal;

public class MainDOeuvre extends Composant {
    private BigDecimal tauxHoraire;
    private float heuresTravail;
    private float productiviteOuvrier;

    public MainDOeuvre() {
        super();
    }

    public MainDOeuvre(String nom, float tauxTVA, Projet projet, BigDecimal tauxHoraire, float heuresTravail, float productiviteOuvrier) {
        super(nom, tauxTVA, TypeComposant.MAINDOEUVRE, projet);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public BigDecimal getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(BigDecimal tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public float getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(float heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public float getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(float productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }
}

