package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.AdresseDAO;
import fr.eni.projet.encheres.dal.UtilisateurDAO;
import fr.eni.projet.encheres.dal.UtilisateurDAOImpl;
import fr.eni.projet.encheres.exception.BusinessCode;
import fr.eni.projet.encheres.exception.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

private UtilisateurDAO utilisateurDAO;
private AdresseDAO adresseDAO;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, AdresseDAO adresseDAO) {
        this.utilisateurDAO = utilisateurDAO;
        this.adresseDAO = adresseDAO;
    }

    @Transactional
    @Override
    public void creerCompte(Utilisateur utilisateur) {

        BusinessException be = new BusinessException();

        boolean isValid = true;
        isValid &= validerPseudoUnique(utilisateur.getPseudo(), be);
        isValid &= validerEmailUnique(utilisateur.getEmail(), be);

        if (isValid) {
            adresseDAO.createAdresse(utilisateur.getAdresse());
            utilisateurDAO.createUtilisateur(utilisateur);
        }else {
            throw be;
        }

    }

    /***** Pseudo unique ******/
    private boolean validerPseudoUnique(String pseudo, BusinessException be) {

        try {
            boolean pseudoExiste = utilisateurDAO.findPseudo(pseudo);

            if (pseudoExiste) {
                be.add(BusinessCode.VALIDATION_PSEUDO_UNIQUE);
                return false;
            }
        } catch (DataAccessException e) {
            be.add(BusinessCode.VALIDATION_PSEUDO_UNIQUE);
            return false;
        }
        return true;
    }

    /**** Email Unique ****/
    private boolean validerEmailUnique(String email, BusinessException be) {

        try {
            boolean emailExiste = utilisateurDAO.findEmail(email);

            if (emailExiste) {
                be.add(BusinessCode.VALIDATION_EMAIL_UNIQUE);
                return false;
            }
        } catch (DataAccessException e) {
            be.add(BusinessCode.VALIDATION_EMAIL_UNIQUE);
            return false;
        }
        return true;
    }




//    /***** Attribution de l'erreur si Validation du pseudo unique est false ******/
//    private boolean validerPseudo(String pseudo, BusinessException be) {
//        if(pseudo == null){
//            be.add(BusinessCode.VALIDATION_PSEUDO_UNIQUE);
//            return false;
//        }
//        return true;
//    }


}
