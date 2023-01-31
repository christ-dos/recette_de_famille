package fr.dawan.formation.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fr.dawan.formation.enumeration.UniteMesureEnum;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredientId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecetteIngredientDTO {

    private RecetteIngredientId id = new RecetteIngredientId();

    private int quantite;

    private UniteMesureEnum uniteMesure;

    private Ingredient ingredient;

    @JsonBackReference
    private Recette recette;

}
