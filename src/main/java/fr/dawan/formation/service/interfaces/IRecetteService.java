package fr.dawan.formation.service.interfaces;

import java.util.List;

import fr.dawan.formation.DTO.RecetteDTO;
import fr.dawan.formation.model.Recette;

public interface IRecetteService {

    List<Recette> findAll();

    Recette findById(int id);

    Recette saveRecette(Recette recette);

    Recette updateRecette(Recette recette);

    void deleteRecette(int id);

    List<Recette> findByTitle(String title);

    List<Recette> findByCategorie(int categorieId);

    List<RecetteDTO> findByIngredient(int ingredientId);

}