package fr.eni.projet.encheres.controller;

import fr.eni.projet.encheres.bll.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
//@SessionAttributes({"utilisateur"})

    private UtilisateurService utilisateurService;

    public RegisterController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute Utilisateur utilisateur,
                                 BindingResult bindingResult,
                                 Model model) {

           try {
            utilisateur.setMotDePasse(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(utilisateur.getMotDePasse()));
            utilisateurService.creerCompte(utilisateur);
            return "redirect:/";

        } catch (BusinessException e) {
            e.getClefsExternalisations()
                    .forEach(key -> {
                        // creation d'une erreur globale
                        ObjectError error = new ObjectError("global", key);
                        bindingResult.addError(error);
                    });
            return "register";

        }


    }
}