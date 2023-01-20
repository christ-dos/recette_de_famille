package fr.dawan.formation.DTO;

import java.util.List;

import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.model.RecetteIngredient;
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

    private List<RecetteIngredient> recettesIngredient;
    // un commentaitre de test
}
