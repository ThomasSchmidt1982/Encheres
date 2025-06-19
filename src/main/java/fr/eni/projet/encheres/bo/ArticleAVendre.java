package fr.eni.projet.encheres.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ArticleAVendre {


    private long id;

    @NotBlank(message = "validation.article.nom.notblank")
    @Size(max = 30, message = "validation.article.nom.max")
    private String nomArticle;

    @NotBlank(message = "validation.article.description.notblank")
    @Size(max = 300, message = "validation.article.description.max")
    private String description;

    //photo

    @NotNull(message = "validation.article.dateDebutEncheres.notNull")
    private LocalDate dateDebutEncheres;

    @NotNull(message = "validation.article.dateFinEncheres.notNull")
    private LocalDate dateFinEncheres;

    private int statut;

    @NotNull(message = "validation.article.prixInitial.notNull")
    private int prixInitial;

    private int prixVente;

    private Utilisateur vendeur;

    private Adresse retrait;

    private Categorie categorie;


    // constructeur par defaut
    public ArticleAVendre() {}

    // constructeur avec champs
    public ArticleAVendre(long id, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int statut, int prixInitial, int prixVente, Utilisateur vendeur, Adresse retrait, Categorie categorie) {
        this.id = id;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.statut = statut;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.vendeur = vendeur;
    }

    // getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    public Adresse getRetrait() {
        return retrait;
    }

    public void setRetrait(Adresse retrait) {
        this.retrait = retrait;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArticleAVendre{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nomArticle).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dateDebutEncheres=").append(dateDebutEncheres);
        sb.append(", dateFinEncheres=").append(dateFinEncheres);
        sb.append(", statut=").append(statut);
        sb.append(", prixInitial=").append(prixInitial);
        sb.append(", prixVente=").append(prixVente);
        sb.append(", vendeur=").append(vendeur);
        sb.append(", retrait=").append(retrait);
        sb.append(", categorie=").append(categorie);
        sb.append('}');
        return sb.toString();
    }

}
