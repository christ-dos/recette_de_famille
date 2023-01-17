package fr.dawan.formation.repository;

import org.springframework.data.repository.CrudRepository;

import fr.dawan.formation.model.Ingredient;

/**
 * Interface de gestion de requêteage de l'objet {@link Ingredient}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}
