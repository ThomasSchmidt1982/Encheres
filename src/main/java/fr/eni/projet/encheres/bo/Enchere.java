package fr.eni.projet.encheres.bo;

import java.time.LocalDate;

public class Enchere {

    private LocalDate date;
    private int montant;
    private ArticleAVendre articleAVendre;
    private Utilisateur acquereur;

    // constructeur par defaut
    public Enchere(){
    }

    // constructeur avec champs
    public Enchere(LocalDate date, int montant, ArticleAVendre articleAVendre, Utilisateur acquereur) {
        this.date = date;
        this.montant = montant;
        this.articleAVendre = articleAVendre;
        this.acquereur = acquereur;
    }

    // getters & setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public ArticleAVendre getArticleAVendre() {
        return articleAVendre;
    }

    public void setArticleAVendre(ArticleAVendre articleAVendre) {
        this.articleAVendre = articleAVendre;
    }

    public Utilisateur getAcquereur() {
        return acquereur;
    }

    public void setAcquereur(Utilisateur acquereur) {
        this.acquereur = acquereur;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enchere{");
        sb.append("date=").append(date);
        sb.append(", montant=").append(montant);
        sb.append(", articleAVendre=").append(articleAVendre);
        sb.append(", acquereur=").append(acquereur);
        sb.append('}');
        return sb.toString();
    }

}
