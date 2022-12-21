package fr.dawan.formation.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

/**
 * Classe qui décrit un objet de type {@link Recette}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */

//@AllArgsConstructor
/*
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @ToString
 */
@Entity
@Table(name="recettes")
public class Recette implements Serializable {
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

    public Recette(String title, String urlPicture, String totalTimePreparation, String timePreparation,
            String cookingTime, String restTime, String stepPreparation, String difficultyLevel,
            String numberOfPeople) {
        super();
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

    public Recette() {

    }


    public int getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public String getTotalTimePreparation() {
        return totalTimePreparation;
    }

    public String getTimePreparation() {
        return timePreparation;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public String getRestTime() {
        return restTime;
    }

    public String getStepPreparation() {
        return stepPreparation;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public void setTotalTimePreparation(String totalTimePreparation) {
        this.totalTimePreparation = totalTimePreparation;
    }

    public void setTimePreparation(String timePreparation) {
        this.timePreparation = timePreparation;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }

    public void setStepPreparation(String stepPreparation) {
        this.stepPreparation = stepPreparation;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}

