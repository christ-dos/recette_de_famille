package fr.dawan.formation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe qui décrit un objet de type {@link Ingredient}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
@Getter
@Setter
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "url_picture", length = 500)
    private String urlPicture;

    @OneToMany(mappedBy = "ingredient")
    @JsonIgnore
    private List<RecetteIngredient> recettesIngredients;

    public void addRecetteIngredient(RecetteIngredient recetteIngredient) {
        if (this.recettesIngredients == null) {
            this.recettesIngredients = new ArrayList<>();
        }
        this.recettesIngredients.add(recetteIngredient);

    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + ", urlPicture=" + urlPicture + "]";
    }

}
