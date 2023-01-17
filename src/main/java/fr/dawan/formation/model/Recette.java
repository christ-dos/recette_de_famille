package fr.dawan.formation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Setter
@Getter
@Entity
@Table(name = "recettes")
public class Recette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(name = "url_picture", length = 255)
    private String urlPicture;

    @Column(name = "total_time_preparation", length = 10)
    private String totalTimePreparation;

    @Column(name = "time_preparation", length = 10, nullable = false)
    private String timePreparation;

    @Column(name = "cooking_time", length = 10)
    private String cookingTime;

    @Column(name = "rest_time", length = 10)
    private String restTime;

    @Column(name = "step_preparation", length = 1024, nullable = false)
    private String stepPreparation;

    @Column(name = "difficulty_level", length = 150)
    private String difficultyLevel;

    @Column(name = "number_of_people", length = 2)
    private String numberOfPeople;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    private Categorie categorie;

    @OneToMany(mappedBy = "recette", cascade = { CascadeType.MERGE, CascadeType.REFRESH }, orphanRemoval = true)
    @JsonIgnore
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
        if (this.recettesIngredients == null) {
            this.recettesIngredients = new ArrayList<>();
        }
        this.recettesIngredients.add(recetteIngredient);
    }

    public List<Ingredient> getIngredients() {
        return this.recettesIngredients.stream().map((recetteIngredient) -> recetteIngredient.getIngredient())
                .collect(Collectors.toList());
    }

    // todo implementer une methode permettant de recupererl'ingredient des
    // receetteIngredient
    // pour appeller dans le save de service et verifier si ingredient
    // n'existe pas ds BDD alors le save sion le merge fera le taf

    @Override
    public String toString() {
        return "Recette [id=" + id + ", title=" + title + ", urlPicture=" + urlPicture + ", totalTimePreparation="
                + totalTimePreparation + ", timePreparation=" + timePreparation + ", cookingTime=" + cookingTime
                + ", restTime=" + restTime + ", stepPreparation=" + stepPreparation + ", difficultyLevel="
                + difficultyLevel + ", numberOfPeople=" + numberOfPeople + ", categorie=" + categorie + "]";
    }

}
