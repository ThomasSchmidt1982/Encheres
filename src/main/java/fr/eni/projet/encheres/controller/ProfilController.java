package fr.eni.projet.encheres.controller;


import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ProfilController {

    private final UtilisateurService utilisateurService;

    public ProfilController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/view-profil")
    public String afficherProfil(Model model){
        // Récupérer l'authentification courante
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Récupérer le nom d'utilisateur (pseudo)
        String pseudo = authentication.getName();

        Utilisateur utilisateur = utilisateurService.consulterUtilisateur(pseudo);

        model.addAttribute("profilUtilisateur", utilisateur);
        return "view-profil";
    }
}
