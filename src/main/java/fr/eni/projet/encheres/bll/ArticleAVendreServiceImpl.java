package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.ArticleAVendre;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.dal.AdresseDAO;
import fr.eni.projet.encheres.dal.ArticleAVendreDAO;
import fr.eni.projet.encheres.dal.CategorieDAO;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import fr.eni.projet.encheres.exception.BusinessCode;
import fr.eni.projet.encheres.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleAVendreServiceImpl implements ArticleAVendreService{

    private ArticleAVendreDAO articleAVendreDAO;
    private UtilisateurDAO utilisateurDAO;
    private AdresseDAO adresseDAO;
    private CategorieDAO categorieDAO;

    //constructeur
    public ArticleAVendreServiceImpl(ArticleAVendreDAO articleAVendreDAO, UtilisateurDAO utilisateurDAO, AdresseDAO adresseDAO, CategorieDAO categorieDAO) {
        this.articleAVendreDAO = articleAVendreDAO;
        this.utilisateurDAO = utilisateurDAO;
        this.adresseDAO = adresseDAO;
        this.categorieDAO = categorieDAO;
    }

    @Override
    public List<ArticleAVendre> consulterArticles() {
        List<ArticleAVendre> articleAVendre = articleAVendreDAO.findAll();
        return articleAVendre;
    }

    @Override
    public List<ArticleAVendre> consulterArticlesActifs() {
        List<ArticleAVendre> articleAVendreActif = articleAVendreDAO.findActive();
        return articleAVendreActif;
    }

    @Transactional
    @Override
    public void creerArticleAVendre(ArticleAVendre articleAVendre) {

            BusinessException be = new BusinessException();

            // tests sur l'article à créer
            boolean isValid = validerArticle(articleAVendre, be);


            if (isValid) {
                articleAVendreDAO.insertArticle(articleAVendre);
            }else {
                throw be;
            }

        }


    private boolean validerArticle(ArticleAVendre a, BusinessException be) {
        if(a.getNomArticle() == null || a.getNomArticle().isBlank()){
            be.add(BusinessCode.VALIDATION_NOMARTICLE_BLANK);
            return false;
        }
        if (a.getNomArticle().length() > 30){
            be.add(BusinessCode.VALIDATION_NOMARTICLE_MAX);
            return false;
        }
        return true;
    }

    @Override
    public List<Categorie> consulterCategories() {
        return categorieDAO.findAll();
    }

    @Override
    public Categorie consulterCategorieParId(Long id) {
        return categorieDAO.findById(id);
    }

    @Override
    public boolean ArticleEnCours(ArticleAVendre articleAVendre) {

        LocalDate aujourdhui = LocalDate.now();

        return articleAVendre != null
                && articleAVendre.getDateFinEncheres() != null
                && articleAVendre.getStatut() < 100
                && !aujourdhui.isAfter(articleAVendre.getDateFinEncheres());

    }

}
