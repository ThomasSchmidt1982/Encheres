package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleAVendre;

import java.util.List;

public interface ArticleAVendreDAO {

    List<ArticleAVendre> findAll();

    List<ArticleAVendre> findActive();

    void insertArticle(ArticleAVendre article);

}
