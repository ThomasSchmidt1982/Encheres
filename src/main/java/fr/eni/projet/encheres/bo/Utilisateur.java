package fr.eni.projet.encheres.bo;

import jakarta.validation.constraints.*;

public class Utilisateur {

    @NotBlank(message = "validation.utilisateur.pseudo.notblank")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "validation.utilisateur.pseudo.pattern")
    @Size(max = 30, message = "validation.utilisateur.pseudo.max")
    private String pseudo;

    @NotBlank(message = "validation.utilisateur.nom.notblank")
    @Size(max = 40, message = "validation.utilisateur.nom.max")
    private String nom;

    @NotBlank(message = "validation.utilisateur.prenom.notblank")
    @Size(max=50, message = "validation.utilisateur.prenom.max")
    private String prenom;

    @Email
    @NotBlank(message = "validation.utilisateur.email.notblank")
    @Size(max = 100, message = "validation.utilisateur.email.max")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "validation.utilisateur.email.pattern")
    private String email;

    @Size(max = 15, message = "validation.utilisateur.telephone.max")
    private String telephone;

    @NotBlank(message = "validation.utilisateur.motDePasse.notblank")
    @Size(min = 8, max = 20, message = "validation.utilisateur.motDePasse.size")
    @Pattern(regexp = "[A-Za-z\\d@$!%*?&]{8,20}", message = "validation.utilisateur.motDePasse.pattern")
    private String motDePasse;

    private int credit;

    private boolean admin;

    @NotNull(message = "validation.utilisateur.adresse.notnull")
    private Adresse adresse;

    // Constructeur par defaut
    public Utilisateur() {
    }

    // Constructeur avec Champs
    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, int credit, boolean admin, Adresse adresse, String motDePasse) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.credit = credit;
        this.admin = admin;
        this.adresse = adresse;
        this.motDePasse = motDePasse;
    }

    // getters & setters
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Utilisateur{");
        sb.append("pseudo='").append(pseudo).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", credit=").append(credit);
        sb.append(", admin=").append(admin);
        sb.append(", adresse=").append(adresse);
        sb.append(", motDePasse='").append(motDePasse).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
