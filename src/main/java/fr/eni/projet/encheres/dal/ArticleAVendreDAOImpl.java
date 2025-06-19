package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleAVendre;
import fr.eni.projet.encheres.bo.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ArticleAVendreDAOImpl implements ArticleAVendreDAO{

    // scripts sql
    private static final String INSERT_ARTICLE = "INSERT INTO articles_a_vendre " +
            "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, id_utilisateur, no_categorie, no_adresse_retrait) " +
            "VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :id_utilisateur, :no_categorie, :no_adresse_retrait)";

    private static final String FIND_ALL = "SELECT * FROM articles_a_vendre";
    /***  Statut d'Encheres  -->  0 : PAS COMMENCEE, 1 : EN COURS, 2 : CLOTUREE, 3 : LIVREE,  100 : ANNULEE ***/
    private static final String FIND_ACTIVE = "SELECT * FROM articles_a_vendre WHERE statut_enchere IN (1, 2) ";


    // classe utilitaire de Spring pour gestion de bdd
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /*** Méthode création d'article à vendre ***/
    @Override
    public void insertArticle(ArticleAVendre articleAV) {
        // permet de créer un article en choisissant les valeurs à ajouter
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("nom_article", articleAV.getNomArticle())
                .addValue("description", articleAV.getDescription())
                .addValue("date_debut_encheres", Date.valueOf(articleAV.getDateDebutEncheres()))
                .addValue("date_fin_encheres", Date.valueOf(articleAV.getDateFinEncheres()))
                .addValue("prix_initial", articleAV.getPrixInitial())
                .addValue("id_utilisateur", articleAV.getVendeur().getPseudo())
                .addValue("no_categorie", articleAV.getCategorie().getId())
                .addValue("no_adresse_retrait", articleAV.getVendeur().getAdresse().getId());
        // màj de la table avec le script, les parametres nommés
        jdbcTemplate.update(INSERT_ARTICLE, namedParameters);
    }

    /*** Methode qui retourne la liste de TOUS les articles à vendre ***/
    @Override
    public List<ArticleAVendre> findAll() {
        return jdbcTemplate.query(FIND_ALL, new ArticleAVendreRowMapper());
    }

    /*** Methode qui retourne les articles actifs (statut d'enchère 1) ***/
    @Override
    public List<ArticleAVendre> findActive() {
        return jdbcTemplate.query(FIND_ACTIVE, new ArticleAVendreRowMapper());
    }

    /*** - Cette classe sert de convertisseur entre les données de la bdd et les objets Java ***/
     class ArticleAVendreRowMapper implements RowMapper<ArticleAVendre> {
        @Override
        public ArticleAVendre mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleAVendre av = new ArticleAVendre();
            av.setId(rs.getLong("no_article"));
            av.setNomArticle(rs.getString("nom_article"));
            av.setDescription(rs.getString("description"));
            av.setDateDebutEncheres(LocalDate.parse(rs.getString("date_debut_encheres")));
            av.setDateFinEncheres(LocalDate.parse(rs.getString("date_fin_encheres")));
            av.setStatut(rs.getInt("statut_enchere"));
            av.setPrixInitial(rs.getInt("prix_initial"));
            av.setPrixVente(rs.getInt("prix_vente"));

            return av;
        }
    }

}
