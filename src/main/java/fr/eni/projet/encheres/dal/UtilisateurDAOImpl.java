package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    String INSERT_USER = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, mot_de_passe, credit, administrateur, no_adresse) VALUES (:pseudo, :nom, :prenom, :email, :telephone, :mot_de_passe, :credit, :administrateur, :no_adresse)";
    String FIND_UNIQUE_PSEUDO = "SELECT COUNT(*) FROM utilisateurs WHERE pseudo = :pseudo";
    String FIND_UNIQUE_EMAIL = "SELECT COUNT(*) FROM utilisateurs WHERE email = :email";

    @Override
    public void createUtilisateur(Utilisateur utilisateur){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("pseudo", utilisateur.getPseudo())
                .addValue("nom", utilisateur.getNom())
                .addValue("prenom", utilisateur.getPrenom())
                .addValue("email", utilisateur.getEmail())
                .addValue("telephone", utilisateur.getTelephone())
                .addValue("mot_de_passe", utilisateur.getMotDePasse())
                .addValue("credit", 10)
                .addValue("administrateur", false)
                .addValue("no_adresse", utilisateur.getAdresse().getId());

        jdbcTemplate.update(INSERT_USER, namedParameters);
    }

    /*** renvoi true si pseudo existe et false si n'existe pas ***/
    @Override
    public boolean findPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", pseudo);
        int val = jdbcTemplate.queryForObject(FIND_UNIQUE_PSEUDO, namedParameters, Integer.class);
       return val >= 1;
    }

    @Override
    public boolean findEmail(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);
        int val = jdbcTemplate.queryForObject(FIND_UNIQUE_EMAIL, namedParameters, Integer.class);
        return val >= 1;
    }

}
