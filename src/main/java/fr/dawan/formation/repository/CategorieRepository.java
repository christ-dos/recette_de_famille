package fr.dawan.formation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.formation.model.Categorie;

/**
 * Interface de gestion de requêteage de l'objet {@link Categorie}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Integer> {

}
