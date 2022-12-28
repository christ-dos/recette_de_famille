package fr.dawan.formation.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe qui configure les clés composites de l'entité RecetteIngredient
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
@EqualsAndHashCode
@Embeddable
public class RecetteIngredientId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(precision = 10, name = "recette_id")
    private int recetteId;

    @Column(precision = 10, name = "ingredient_id")
    private int ingredientId;
}
