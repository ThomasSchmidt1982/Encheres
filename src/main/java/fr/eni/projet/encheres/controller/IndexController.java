package fr.eni.projet.encheres.controller;

import fr.eni.projet.encheres.bll.ArticleAVendreService;
import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.ArticleAVendre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final UtilisateurService utilisateurService;
    private final ArticleAVendreService articleAVendreService;

    public IndexController(UtilisateurService utilisateurService,
                           ArticleAVendreService articleAVendreService) {
        this.utilisateurService = utilisateurService;
        this.articleAVendreService = articleAVendreService;
    }

    @GetMapping("/")
    public String afficherArticle(Model model) {
        List<ArticleAVendre> articleAVendre = articleAVendreService.consulterArticles();
        // Ajouter des données pour la page d'accueil
        model.addAttribute("articleAV", articleAVendre);
        System.out.println(articleAVendre);
        return "view-article";
    }
}