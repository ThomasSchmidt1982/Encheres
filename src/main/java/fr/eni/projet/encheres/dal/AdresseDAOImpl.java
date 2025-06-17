package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AdresseDAOImpl implements AdresseDAO{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private String INSERT_ADRESSE = "INSERT INTO adresses (rue, code_postal, ville, adresse_eni) VALUES (:rue, :code_postal, :ville, :adresse_eni)";

    @Override
    public void createAdresse(Adresse adresse) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("rue", adresse.getRue())
            .addValue("code_postal", adresse.getCodePostal())
            .addValue("ville", adresse.getVille())
            .addValue("adresse_eni", 0);

        jdbcTemplate.update(INSERT_ADRESSE, namedParameters, keyHolder);


        // récup de l'id du film venant de la BDD
        if (keyHolder != null && keyHolder.getKey() != null) {
            adresse.setId(keyHolder.getKey()
                    .longValue());
        }



    }
}
