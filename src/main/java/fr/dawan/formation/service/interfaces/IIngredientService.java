package fr.dawan.formation.service.interfaces;

import java.util.List;

import fr.dawan.formation.model.Ingredient;

public interface IIngredientService {

    Ingredient saveIngredient(Ingredient ingredient);

    Ingredient updateIngredient(Ingredient ingredient);

    List<Ingredient> findAll();

    Ingredient findById(int id);

    void deleteIngredient(int id);

}