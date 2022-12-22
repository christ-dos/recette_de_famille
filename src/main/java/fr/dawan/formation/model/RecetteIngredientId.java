package fr.dawan.formation.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private static final long serialVersionUID = 1L;

    private int recetteId;

    private int ingredientId;
}
