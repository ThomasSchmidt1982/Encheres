package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Categorie;

import java.util.List;

public interface CategorieDAO {

    public List<Categorie> findAll();

    public Categorie findById(long id);

}
