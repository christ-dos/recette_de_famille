package fr.dawan.formation.DTO;

import fr.dawan.formation.enumeration.UniteMesureEnum;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.model.Recette;

public class RecetteIngredientDTO {

    private int quantite;

    private UniteMesureEnum uniteMesure;

    private Ingredient ingredient;

    private Recette recette;

}
