package fr.dawan.formation.interfaces;

import java.util.List;

import fr.dawan.formation.model.Recette;

public interface IRecetteIngredientService {

    // void saveRecetteIngredient(RecetteIngredient recetteIngredient);//todo clean
    // code

    List<Recette> findRecetteByIngredientId(int ingredientId);

}