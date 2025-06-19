package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    public void createUtilisateur(Utilisateur utilisateur);

    public boolean findPseudo(String pseudo);

    public boolean findEmail(String email);

    Utilisateur findByPseudo(String pseudo);

}
