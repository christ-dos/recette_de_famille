package fr.dawan.formation.DTO;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    // @JsonManagedReference
    @Fetch(value = FetchMode.SELECT)
    private List<RecetteIngredientDTO> recettesIngredients;

}
