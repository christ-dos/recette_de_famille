package fr.dawan.formation.service.interfaces;

import java.util.List;

import fr.dawan.formation.model.Categorie;

public interface ICategorieService {

    List<Categorie> findAll();

    Categorie findById(int categorieId);

    Categorie updateCategorie(Categorie categorie);

    Categorie saveCategorie(Categorie categorie);

    void deleteCategorie(int id);

}