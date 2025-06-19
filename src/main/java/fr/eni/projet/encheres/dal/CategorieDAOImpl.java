package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO {

    private static final String SELECT_CATEGORIE = "SELECT * FROM categories";
    private static final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM categories WHERE no_categorie = :id";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Categorie> findAll() {
        return jdbcTemplate.query(SELECT_CATEGORIE, new CategoryRowMapper());
    }

    @Override
    public Categorie findById(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        return jdbcTemplate.queryForObject(SELECT_CATEGORIE_BY_ID, namedParameters, new CategoryRowMapper());
    }

    public class CategoryRowMapper implements RowMapper<Categorie>{

        @Override
        public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categorie c = new Categorie();
            c.setId(rs.getLong("no_categorie"));
            c.setLibelle(rs.getString("libelle"));
            return c;
        }
    }


}
