package fr.dawan.formation.repository;

import org.springframework.data.repository.CrudRepository;

import fr.dawan.formation.model.Recette;

/**
 * Interface de gestion de requêteage de l'objet {@link Recette}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
public interface RecetteRepository extends CrudRepository<Recette, Integer> {
    Iterable<Recette> findByTitle(String title);

    Iterable<Recette> findByCategorie(String categorie);

}
