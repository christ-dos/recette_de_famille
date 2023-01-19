package fr.dawan.formation.service.interfaces;

import java.util.List;

import fr.dawan.formation.DTO.RecetteDTO;
import fr.dawan.formation.model.Recette;

public interface IRecetteService {

    List<RecetteDTO> findAll();

    RecetteDTO findById(int id);

    RecetteDTO saveRecette(Recette recette);

    RecetteDTO updateRecette(Recette recette);

    void deleteRecette(int id);

    List<RecetteDTO> findByTitle(String title);

    List<RecetteDTO> findByCategorie(int categorieId);

    List<RecetteDTO> findByIngredient(int ingredientId);

}