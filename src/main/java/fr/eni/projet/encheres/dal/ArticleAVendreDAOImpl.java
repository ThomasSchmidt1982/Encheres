package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.ArticleAVendre;
import fr.eni.projet.encheres.bo.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ArticleAVendreDAOImpl implements ArticleAVendreDAO{

    private static final String FIND_ALL = "SELECT * FROM articles_a_vendre";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ArticleAVendre> findAll() {
        return jdbcTemplate.query(FIND_ALL, new ArticleAVendreRowMapper());
    }

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
