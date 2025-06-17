package fr.eni.projet.encheres.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class EnchereSecurityConfig {

    // script SQL qui recupère les infos d'authentification de l'utilisateur email et password hashé le 1 signifie activé
    private static final String SELECT_USER = "SELECT email, mot_de_passe, 1 FROM utilisateurs WHERE email = ? ";

    // script SQL avec jointure qui lie la table roles et membres par la colonne is_admin et admin
    private static final String SELECT_ROLES = "SELECT u.email, r.role FROM utilisateurs u INNER JOIN roles r "
                                                + " ON r.is_admin = u.administrateur WHERE u.email = ? ";

    /**    - méthode qui crée un gestionnaire d'utilisateurs
        - prend en paramètre une source de données (DataSource) qui sera injectée par Spring
        - cette source de données permet d'accéder à la base de données pour l'authentification
        - Retourne un , une interface Spring Security pour gérer les détails des utilisateurs
        `UserDetailsManager` **/
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);
        return jdbcUserDetailsManager;
    }

    // processus de connexion
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // gere les accès aux URL "/", "/home" et "/register" sans authentification
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/",
                                "Index",
                                "/home",
                                "/register",
                                "/css/**",
                                "/images/**",
                                "login").permitAll() // en acces libre

                        .anyRequest().authenticated()   // pour tout le reste acces authentifié
                )

                // definit URL de la page de connexion personnalisé
                .formLogin(form -> form
                        .loginPage("/login") // localisation de la page de login
                        .loginProcessingUrl("/login")  // URL de traitement du formulaire
                        .usernameParameter("username") // nom du champ HTML pour le nom d'utilisateur
                        .passwordParameter("password") // nom du champ HTML pour le mot de passe
                        .defaultSuccessUrl("/")    // redirection après connexion réussie
                        .failureUrl("/login?error=true") // redirection en cas d'échec
                        .permitAll()
                );

        // logout personnalisé
        http.logout(logout -> {
            logout	.invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutRequestMatcher((request) -> request.getRequestURI().equals("/logout"))
                    .logoutSuccessUrl("/")
                    .permitAll();
        });


        return http.build();
    }

}