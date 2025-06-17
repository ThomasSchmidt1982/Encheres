package fr.eni.projet.encheres.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    // liste des clefs externes
    private List<String> clefsExternalisations;

    // constructeur par defaut
    public BusinessException() {
        super();
    }

    // constructeur avec parametres
    public BusinessException(Throwable cause) {
        super(cause);
    }

    public List<String> getClefsExternalisations() {
        return clefsExternalisations;
    }

    /**
     * Permet d'ajouter une clef d'erreur
     * @param clef
     * @comportement initialise la liste si besoin
     */
    public void add(String clef) {
        if (clefsExternalisations == null) {
            clefsExternalisations = new ArrayList<>();
        }
        clefsExternalisations.add(clef);
    }

    /**
     * @return permet de confirmer si des erreurs ont été chargées
     */
    public boolean isValid() {
        return clefsExternalisations == null || clefsExternalisations.isEmpty();
    }

}
