package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;

import java.util.Set;

public interface UtilisateurService {

    Utilisateur creer(String email, String motDePasse, Set<String> roles);


}
