package fr.dawan.formation.DTO;



import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.model.RecetteIngredient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IngredientDTO {
    //test commentaires

    private int id;

    private String name;

    private String urlPicture;

}
