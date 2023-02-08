package fr.dawan.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.formation.model.RecetteIngredient;

/**
 * Interface de gestion de requêteage de l'objet {@link RecetteIngredient}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
public interface RecetteIngredientRepository extends JpaRepository<RecetteIngredient, Integer> {

    List<RecetteIngredient> findByIngredientId(int ingredientId);

    List<RecetteIngredient> findByRecetteId(int recetteId);
}
