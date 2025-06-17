package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.ArticleAVendre;
import fr.eni.projet.encheres.dal.AdresseDAO;
import fr.eni.projet.encheres.dal.ArticleAVendreDAO;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import fr.eni.projet.encheres.exception.BusinessCode;
import fr.eni.projet.encheres.exception.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleAVendreServiceImpl implements ArticleAVendreService{

    private ArticleAVendreDAO articleAVendreDAO;
    private UtilisateurDAO utilisateurDAO;
    private AdresseDAO adresseDAO;

    //constructeur
    public ArticleAVendreServiceImpl(ArticleAVendreDAO articleAVendreDAO, UtilisateurDAO utilisateurDAO, AdresseDAO adresseDAO) {
        this.articleAVendreDAO = articleAVendreDAO;
        this.utilisateurDAO = utilisateurDAO;
        this.adresseDAO = adresseDAO;
    }

    @Override
    public List<ArticleAVendre> consulterArticles() {
        List<ArticleAVendre> articleAVendre = articleAVendreDAO.findAll();
        return articleAVendre;
    }



}
