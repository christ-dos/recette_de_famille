package fr.dawan.formation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.exception.RecetteNotFoundException;
import fr.dawan.formation.interfaces.IRecetteService;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;
import fr.dawan.formation.repository.RecetteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class RecetteService implements IRecetteService {

    private RecetteRepository recetteRepository;

    @Autowired
    public RecetteService(RecetteRepository recetteRepository) {
        this.recetteRepository = recetteRepository;
    }

    @Override
    public List<Recette> findAll() {
        log.info("Service: Affichage de la liste de recettes");

        return (List<Recette>) recetteRepository.findAll();
    }

    @Override
    public Recette findById(int id) {
        Optional<Recette> recetteRecherche = recetteRepository.findById(id);
        if (recetteRecherche.isEmpty()) {
            log.error("Service: Recette non trouvé");
            throw new RecetteNotFoundException("Cette recette n'existe pas!");
        }
        log.debug("Service: Recette recherché par ID: " + recetteRecherche.get().getId());

        return recetteRecherche.get();
    }

    @Override
    public List<Recette> findByTitle(String title) {
        return (List<Recette>) recetteRepository.findByTitle(title.toLowerCase());
    }

    public List<Recette> findByIngredient(int ingredientId) {
        Iterable<Recette> recettes = recetteRepository.findAll();

        List<Recette> recettesByIngredient = new ArrayList<>();

        if (recettes != null) {
            List<RecetteIngredient> recettesIngredientByIngredient = new ArrayList<>();
            for (Recette recette : recettes) {
                recettesIngredientByIngredient = recette.getRecettesIngredients().stream()
                        .filter(recetteIngredient -> recetteIngredient.getIngredient().getId() == ingredientId)
                        .collect(Collectors.toList());
            }
            System.out.println("mes recettesIngredient: " + recettesIngredientByIngredient);
            recettesByIngredient = recettesIngredientByIngredient.stream()
                    .map(recetteIngredient -> recetteRepository.findById(recetteIngredient.getRecette().getId()).get())
                    .collect(Collectors.toList());

            // todo faire la requete sur la table RecetteIngredient sera mieux
        }

        System.out.println("mes recettes: " + recettesByIngredient);
        return recettesByIngredient;

    }

    @Override
    public Recette saveRecette(Recette recette) {
        recette.setTitle(recette.getTitle().toLowerCase());
        Recette recetteEnregistre = recetteRepository.save(recette);
        log.debug("Service: Recette enregistré avec ID: " + recetteEnregistre.getId());

        return recetteEnregistre;
    }

    @Override
    public Recette updateRecette(Recette recette) {
        Optional<Recette> recetteRecherche = recetteRepository.findById(recette.getId());
        if (recetteRecherche.isEmpty()) {
            log.error("Service: Recette non trouvé");
            throw new RecetteNotFoundException("Cette recette n'existe pas!");
        }
        /**
         * recuperation des versions de la recette et de la catégorie enregistré en BDD
         */
        recette.setVersion(recetteRecherche.get().getVersion());
        recette.getCategorie().setVersion(recetteRecherche.get().getCategorie().getVersion());
        recette.setTitle(recette.getTitle().toLowerCase());
        Recette recetteModifie = recetteRepository.save(recette);
        log.debug("Service: Recette modifiée avec ID: " + recetteModifie.getId());

        return recetteModifie;
    }

    @Override
    public void deleteRecette(int id) {
        Optional<Recette> recetteAEffacer = recetteRepository.findById(id);
        if (recetteAEffacer.get().getId() == id) {
            recetteRepository.deleteById(id);
            log.debug("Recette effacée avec succés" + recetteAEffacer.get().getTitle());
        }
    }

}
