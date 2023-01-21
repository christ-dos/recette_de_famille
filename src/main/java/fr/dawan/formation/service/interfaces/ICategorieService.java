package fr.dawan.formation.service.interfaces;

import java.util.List;

import fr.dawan.formation.DTO.CategorieDTO;

public interface ICategorieService {

    List<CategorieDTO> findAll();

    CategorieDTO findById(int categorieId);

    CategorieDTO updateCategorie(CategorieDTO categorie);

    CategorieDTO saveCategorie(CategorieDTO categorie);

    void deleteCategorie(int id);

}