package fr.eni.projet.encheres.bo;

public class Categorie {

    private long id;
    private String libelle;

    // constructeur par defaut
    public Categorie() {
    }

    // constructeur avec champs
    public Categorie(long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    // getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Categorie{");
        sb.append("id=").append(id);
        sb.append(", libelle='").append(libelle).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
