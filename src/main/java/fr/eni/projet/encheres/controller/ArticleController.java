package fr.eni.projet.encheres.controller;

import fr.eni.projet.encheres.bll.ArticleAVendreService;
import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.ArticleAVendre;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ArticleController {

    private final ArticleAVendreService articleAVendreService;
    private final UtilisateurService utilisateurService;

    //constructeur
    public ArticleController(ArticleAVendreService articleAVendreService, UtilisateurService utilisateurService) {
        this.articleAVendreService = articleAVendreService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/creer-article")
    public String creerArticle(Model model) {
        model.addAttribute("articleAVendre", new ArticleAVendre());
        return "creer-article";
    }

    @PostMapping("/creer-article")
    public String creerArticle(@Valid @ModelAttribute ArticleAVendre articleAV,
                               BindingResult bindingResult,
                               Principal principal,
                               Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "creer-article";
        }

            Utilisateur user = utilisateurService.consulterUtilisateur(principal.getName());
            articleAV.setVendeur(user);
            articleAVendreService.creerArticleAVendre(articleAV);

            return "redirect:/";

    }


}
