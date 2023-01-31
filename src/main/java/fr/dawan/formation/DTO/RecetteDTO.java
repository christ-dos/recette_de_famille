package fr.dawan.formation.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fr.dawan.formation.model.Categorie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecetteDTO {
    private int id;

    private String title;

    private String urlPicture;

    private String totalTimePreparation;

    private String timePreparation;

    private String cookingTime;

    private String restTime;

    private String stepPreparation;

    private String difficultyLevel;

    private String numberOfPeople;

    private Categorie categorie;

    @JsonManagedReference
    private List<RecetteIngredientDTO> recettesIngredients;

}
