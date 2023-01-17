package fr.dawan.formation.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.dawan.formation.enumeration.CategorieEnum;
import fr.dawan.formation.model.Categorie;

/**
 * Interface de gestion de requêteage de l'objet {@link Categorie}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
public interface CategorieRepository extends CrudRepository<Categorie, Integer> {

    Optional<Categorie> findByName(CategorieEnum categorieEnum);

}
