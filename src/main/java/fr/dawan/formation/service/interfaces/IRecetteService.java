package fr.dawan.formation.service.interfaces;

import java.util.List;

import fr.dawan.formation.DTO.RecetteDTO;

public interface IRecetteService {

    List<RecetteDTO> findAll();

    RecetteDTO findById(int id);

    RecetteDTO saveRecette(RecetteDTO recette);

    RecetteDTO updateRecette(RecetteDTO recette);

    void deleteRecette(int id);

    List<RecetteDTO> findByTitle(String title);

    List<RecetteDTO> findByCategorie(int categorieId);

    List<RecetteDTO> findByIngredient(int ingredientId);

    List<RecetteDTO> findByCategorieIdAndTitleLikeModel(int categorieId, String title);

    List<RecetteDTO> findByIngredientIdAndTitleLikeModel(int ingredientId, String title);

}