package fr.eni.projet.encheres.bo;

public enum StatutArticle {
    NON_COMMENCEE(0, "Non commencée"),
    EN_COURS(1, "En cours"),
    CLOTUREE(2, "Clôturée"),
    LIVREE(3, "Livrée"),
    ANNULEE(100, "Annulée");

    private final int code;
    private final String libelle;

    //constructeur
    StatutArticle(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static StatutArticle fromCode(int code) {
        for (StatutArticle statut : StatutArticle.values()) {
            if (statut.getCode() == code) {
                return statut;
            }
        }
        throw new IllegalArgumentException("Code de statut inconnu : " + code);
    }

    // Méthodes utilitaires
    public boolean isModifiable() {
        return this == NON_COMMENCEE || this == EN_COURS;
    }

    public boolean isTerminee() {
        return this == CLOTUREE || this == LIVREE || this == ANNULEE;
    }

    public boolean peutEtreLivree() {
        return this == CLOTUREE;
    }

    public boolean peutEtreAnnulee() {
        return this != LIVREE && this != ANNULEE;
    }
}