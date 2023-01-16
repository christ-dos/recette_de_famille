package fr.dawan.formation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.exception.RecetteNotFoundException;
import fr.dawan.formation.interfaces.IRecetteService;
import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;
import fr.dawan.formation.model.RecetteIngredientId;
import fr.dawan.formation.repository.CategorieRepository;
import fr.dawan.formation.repository.IngredientRepository;
import fr.dawan.formation.repository.RecetteIngredientRepository;
import fr.dawan.formation.repository.RecetteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class RecetteService implements IRecetteService {

    private RecetteRepository recetteRepository;
    private CategorieRepository categorieRepository;
    private IngredientRepository ingredientRepository;
    private RecetteIngredientRepository recetteIngredientRepository;

    @Autowired
    public RecetteService(RecetteRepository recetteRepository, CategorieRepository categorieRepository,
            IngredientRepository ingredientRepository, RecetteIngredientRepository recetteIngredientRepository) {
        this.recetteRepository = recetteRepository;
        this.categorieRepository = categorieRepository;
        this.ingredientRepository = ingredientRepository;
        this.recetteIngredientRepository = recetteIngredientRepository;
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

    public List<Recette> findByCategorie(int categorieId) {
        Categorie categorie = categorieRepository.findById(categorieId).get();
        return (List<Recette>) recetteRepository.findByCategorie(categorie);
    }

    public List<Recette> findByIngredient(int ingredientId) {
//        Iterable<Recette> recettes = recetteRepository.findAll();
//
//        List<Recette> recettesByIngredient = new ArrayList<>();
//
//        if (recettes != null) {
//            List<RecetteIngredient> recettesIngredientByIngredient = new ArrayList<>();
//            for (Recette recette : recettes) {
//                recettesIngredientByIngredient = recette.getRecettesIngredients().stream()
//                        .filter(recetteIngredient -> recetteIngredient.getIngredient().getId() == ingredientId)
//                        .collect(Collectors.toList());
//            }
//            System.out.println("mes recettesIngredient: " + recettesIngredientByIngredient);
//            recettesByIngredient = recettesIngredientByIngredient.stream()
//                    .map(recetteIngredient -> recetteRepository.findById(recetteIngredient.getRecette().getId()).get())
//                    .collect(Collectors.toList());
//
//            // todo faire la requete sur la table RecetteIngredient sera mieux
//        }
//
//        System.out.println("mes recettes: " + recettesByIngredient);
        return null;
//
    }// todo clean code

    @Override
    public Recette saveRecette(Recette recette) {
        saveIngredientNotExists(recette);

        recette.setTitle(recette.getTitle().toLowerCase());
        Recette recetteEnregistre = recetteRepository.save(recette);

        saveCategorieNotExists(recette.getCategorie());

        saveRecetteIngredient(recetteEnregistre, recette.getRecettesIngredients());

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

    private boolean isIngredientExist(Ingredient ingredient) {
        if (ingredient.getId() != 0) {
            return true;
        }
        return false;
    }

    private void saveCategorieNotExists(Categorie categorie) {
        if (categorie.getId() == 0) {
            categorieRepository.save(categorie);
        }
    }

    private void saveIngredientNotExists(Recette recette) {

        List<Ingredient> ingredients = recette.getIngredients();
        ingredients.stream().filter(ingredient -> !isIngredientExist(ingredient))
                .map(ingredient -> ingredientRepository.save(ingredient)).collect(Collectors.toList());
    }

    private void saveRecetteIngredient(Recette recetteEnregistre, List<RecetteIngredient> listRecetteIngredient) {
        for (RecetteIngredient recetteIngredient : listRecetteIngredient) {
            recetteIngredient.setId(new RecetteIngredientId(recetteIngredient.getRecette().getId(),
                    recetteIngredient.getIngredient().getId()));
            System.out.println(recetteIngredient);
            recetteIngredientRepository.save(recetteIngredient);
        }
    }
}
