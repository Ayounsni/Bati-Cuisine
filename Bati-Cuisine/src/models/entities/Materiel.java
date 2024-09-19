package models.entities;

import models.enums.TypeComposant;

import java.math.BigDecimal;

public class Materiel extends Composant {
    private BigDecimal coutUnitaire;
    private float quantite;
    private BigDecimal coutTransport;
    private float coefficientQualite;

    public Materiel() {
        super();
    }

    public Materiel(String nom, float tauxTVA, Projet projet, BigDecimal coutUnitaire, float quantite, BigDecimal coutTransport, float coefficientQualite) {
        super(nom, tauxTVA, TypeComposant.MATERIEL, projet);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public BigDecimal getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(BigDecimal coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(BigDecimal coutTransport) {
        this.coutTransport = coutTransport;
    }

    public float getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(float coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }
}

