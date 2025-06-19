package fr.eni.projet.encheres.controller.converter;

import fr.eni.projet.encheres.bll.ArticleAVendreService;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.controller.ArticleController;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<String, Categorie> {


    private final ArticleAVendreService articleAVendreService;


    public StringToCategoryConverter(ArticleAVendreService articleAVendreService) {
        this.articleAVendreService = articleAVendreService;
    }

    @Override
    public Categorie convert(String id) {
        return articleAVendreService.consulterCategorieParId(Long.valueOf(id));
    }

}
