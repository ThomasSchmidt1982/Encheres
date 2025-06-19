package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.ArticleAVendre;
import fr.eni.projet.encheres.bo.Categorie;

import java.util.List;

public interface ArticleAVendreService {

        List<ArticleAVendre> consulterArticles();

        List<ArticleAVendre> consulterArticlesActifs();

        void creerArticleAVendre(ArticleAVendre articleAVendre);

        public List<Categorie> consulterCategories();

        public Categorie consulterCategorieParId(Long id);

        public boolean ArticleEnCours(ArticleAVendre articleAVendre);

}
