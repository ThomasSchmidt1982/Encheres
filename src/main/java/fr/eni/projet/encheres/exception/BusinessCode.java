package fr.eni.projet.encheres.exception;

public class BusinessCode {

    // Utilisateur
    public static final String VALIDATION_PSEUDO_BLANK = "validation.utilisateur.pseudo.notblank";
    public static final String VALIDATION_PSEUDO_PATTERN = "validation.utilisateur.pseudo.pattern";
    public static final String VALIDATION_PSEUDO_MAX = "validation.utilisateur.pseudo.max";
    public static final String VALIDATION_NOM_BLANK = "validation.utilisateur.nom.notblank";
    public static final String VALIDATION_NOM_MAX = "validation.utilisateur.nom.max";
    public static final String VALIDATION_PRENOM_BLANK = "validation.utilisateur.prenom.notblank";
    public static final String VALIDATION_PRENOM_MAX = "validation.utilisateur.prenom.max";
    public static final String VALIDATION_EMAIL_BLANK = "validation.utilisateur.email.notblank";
    public static final String VALIDATION_EMAIL_MAX = "validation.utilisateur.email.max";
    public static final String VALIDATION_EMAIL_PATTERN = "validation.utilisateur.email.pattern";
    public static final String VALIDATION_TELEPHONE_MAX = "validation.utilisateur.telephone.max";
    public static final String VALIDATION_MOTDEPASSE_BLANK = "validation.utilisateur.motDePasse.notblank";
    public static final String VALIDATION_MOTDEPASSE_SIZE = "validation.utilisateur.motDePasse.size";
    public static final String VALIDATION_MOTDEPASSE_PATTERN = "validation.utilisateur.motDePasse.pattern";
    public static final String VALIDATION_ADRESSE_NULL = "validation.utilisateur.adresse.notnull";

    public static final String VALIDATION_PSEUDO_UNIQUE = "validation.utilisateur.pseudo.unique";
    public static final String VALIDATION_EMAIL_UNIQUE = "validation.utilisateur.email.unique";

    // ArticleAVendre
    public static final String VALIDATION_NOMARTICLE_BLANK = "validation.article.nom.notblank";
    public static final String VALIDATION_NOMARTICLE_MAX = "validation.article.nom.max";
    public static final String VALIDATION_DESCRIPTION_BLANK = "validation.article.description.notblank";
    public static final String VALIDATION_DESCRIPTION_MAX = "validation.article.description.max";
    public static final String VALIDATION_DATE_DEBUT_ENCHERES_NULL = "validation.article.dateDebutEncheres.notNull";
    public static final String VALIDATION_DATE_FIN_ENCHERES_NULL = "validation.article.dateFinEncheres.notNull";
    public static final String VALIDATION_PRIX_INITIAL_NULL = "validation.article.prixInitial.notNull";


}