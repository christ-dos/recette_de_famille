package fr.dawan.formation.service.interfaces;

import java.util.List;

import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;

public interface IRecetteIngredientService {

    // void saveRecetteIngredient(RecetteIngredient recetteIngredient);//todo clean
    // code

    List<Recette> findRecetteByIngredientId(int ingredientId);

    void saveRecetteIngredient(RecetteIngredient recetteIngredient);

}