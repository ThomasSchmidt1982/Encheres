package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Adresse;
import fr.eni.projet.encheres.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    String INSERT_USER = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, mot_de_passe, credit, administrateur, no_adresse) VALUES (:pseudo, :nom, :prenom, :email, :telephone, :mot_de_passe, :credit, :administrateur, :no_adresse)";

    String FIND_UNIQUE_PSEUDO = "SELECT COUNT(*) FROM utilisateurs WHERE pseudo = :pseudo";
    String FIND_UNIQUE_EMAIL = "SELECT COUNT(*) FROM utilisateurs WHERE email = :email";
    String FIND_BY_PSEUDO = "SELECT * FROM utilisateurs u LEFT JOIN adresses a ON u.no_adresse = a.no_adresse WHERE u.pseudo = :pseudo";

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
    /*** renvoi true si email existe et false si n'existe pas ***/
    @Override
    public boolean findEmail(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);
        int val = jdbcTemplate.queryForObject(FIND_UNIQUE_EMAIL, namedParameters, Integer.class);
        return val >= 1;
    }

    /*** retrouver utilisateur par son pseudo ***/
    @Override
    public Utilisateur findByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", pseudo);
        return jdbcTemplate.queryForObject(FIND_BY_PSEUDO, namedParameters,
                (rs, rowNum) -> {
                    // creation de l'objet Adresse a
                    Adresse a = new Adresse();
                    a.setId(rs.getLong("no_adresse"));
                    a.setRue(rs.getString("rue"));
                    a.setCodePostal(rs.getString("code_postal"));
                    a.setVille(rs.getString("ville"));

                    // creation de l'objet Utilisateur u
                    Utilisateur u = new Utilisateur();
                    u.setPseudo(rs.getString("pseudo"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setEmail(rs.getString("email"));
                    u.setTelephone(rs.getString("telephone"));
                    u.setAdresse(a);
                    return u;
                });
    }




}
