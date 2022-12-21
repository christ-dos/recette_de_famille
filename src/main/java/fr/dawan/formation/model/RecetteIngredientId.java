package fr.dawan.formation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
public class RecetteIngredientId implements Serializable {
    private int recetteId;

    private int ingredientId;
}
