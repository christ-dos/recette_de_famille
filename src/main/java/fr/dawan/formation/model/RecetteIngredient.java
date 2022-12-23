package fr.dawan.formation.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe qui décrit une table association entre {@link Recette} et
 * {@link Ingredient}
 *
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "recette_ingredients")
public class RecetteIngredient implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Clé composite qui fait la jointure entre {@link Recette} et
     * {@link Ingredient}
     */
    @EmbeddedId
    private RecetteIngredientId id;

    @Column(nullable = false)
    private int quantite;

    @Column(name = "unite_mesure", length = 5, nullable = false)
    private String uniteMesure;

    @ManyToOne
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @ManyToOne
    @MapsId("recetteId")
    private Recette recette;
}
