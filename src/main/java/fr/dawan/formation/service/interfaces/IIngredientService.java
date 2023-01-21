package fr.dawan.formation.service.interfaces;

import java.util.List;

import fr.dawan.formation.DTO.IngredientDTO;
import fr.dawan.formation.model.Ingredient;

public interface IIngredientService {

    IngredientDTO saveIngredient(Ingredient ingredient);

    IngredientDTO updateIngredient(Ingredient ingredient);

    List<IngredientDTO> findAll();

    IngredientDTO findById(int id);

    void deleteIngredient(int id);

}