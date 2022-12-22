package fr.dawan.formation.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe qui décrit un objet de type {@link Recette}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "recettes")
public class Recette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(name = "url_picture")
    private String urlPicture;

    @Column(name = "total_time_preparation")
    private String totalTimePreparation;

    @Column(name = "time_preparation")
    private String timePreparation;

    @Column(name = "cooking_time")
    private String cookingTime;

    @Column(name = "rest_time")
    private String restTime;

    @Column(name = "step_preparation")
    private String stepPreparation;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "number_of_people")
    private String numberOfPeople;

    @ManyToOne
    private Categorie categorie;

    @OneToMany(mappedBy = "recette")
    private List<RecetteIngredient> recettesIngredients;

    public Recette(String title, String urlPicture, String totalTimePreparation, String timePreparation,
            String cookingTime, String restTime, String stepPreparation, String difficultyLevel,
            String numberOfPeople) {
        this.title = title;
        this.urlPicture = urlPicture;
        this.totalTimePreparation = totalTimePreparation;
        this.timePreparation = timePreparation;
        this.cookingTime = cookingTime;
        this.restTime = restTime;
        this.stepPreparation = stepPreparation;
        this.difficultyLevel = difficultyLevel;
        this.numberOfPeople = numberOfPeople;
    }

    public void ajouterRecetteIngredient(RecetteIngredient recetteIngredient) {
        this.recettesIngredients.add(recetteIngredient);
    }

}
