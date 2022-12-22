package fr.dawan.formation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.formation.model.Recette;

/**
 * Interface de gestion de requêteage de l'objet {@link Recette}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
@Repository
public interface RecetteRepositery extends CrudRepository<Recette, Integer> {

}
