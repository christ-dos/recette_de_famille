package fr.dawan.formation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.DTO.RecetteDTO;
import fr.dawan.formation.exception.RecetteNotFoundException;
import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;
import fr.dawan.formation.model.RecetteIngredientId;
import fr.dawan.formation.repository.CategorieRepository;
import fr.dawan.formation.repository.IngredientRepository;
import fr.dawan.formation.repository.RecetteIngredientRepository;
import fr.dawan.formation.repository.RecetteRepository;
import fr.dawan.formation.service.interfaces.IRecetteService;
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
    private ModelMapper mapper;

    @Autowired
    public RecetteService(RecetteRepository recetteRepository, CategorieRepository categorieRepository,
            IngredientRepository ingredientRepository, RecetteIngredientRepository recetteIngredientRepository,
            ModelMapper mapper) {
        this.recetteRepository = recetteRepository;
        this.categorieRepository = categorieRepository;
        this.ingredientRepository = ingredientRepository;
        this.recetteIngredientRepository = recetteIngredientRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RecetteDTO> findAll() {
        List<Recette> recettes = (List<Recette>) recetteRepository.findAll();
        log.info("Service: Affichage de la liste de recettes");

        return recettes.stream().map(r -> mapper.map(r, RecetteDTO.class)).collect(Collectors.toList());
    }

    @Override
    public RecetteDTO findById(int id) {
        Optional<Recette> recetteRecherche = recetteRepository.findById(id);
        if (recetteRecherche.isEmpty()) {
            log.error("Service: Recette non trouvé");
            throw new RecetteNotFoundException("Cette recette n'existe pas!");
        }
        log.debug("Service: Recette recherché par ID: " + recetteRecherche.get().getId());

        return mapper.map(recetteRecherche.get(), RecetteDTO.class);

    }

    @Override
    public List<RecetteDTO> findByTitle(String title) {
        List<Recette> recettes = recetteRepository.findByTitle(title.toLowerCase());
        List<RecetteDTO> recettesDTO = recettes.stream().map(r -> mapper.map(r, RecetteDTO.class))
                .collect(Collectors.toList());
        return recettesDTO;

    }

    public List<RecetteDTO> findByCategorie(int categorieId) {
        // Categorie categorie = categorieRepository.findById(categorieId).get();
        List<Recette> recettesByCategorie = recetteRepository.findByCategorieId(categorieId);

        List<RecetteDTO> recettesByCategorieDTO = recettesByCategorie.stream().map(r -> mapper.map(r, RecetteDTO.class))
                .collect(Collectors.toList());
        return recettesByCategorieDTO;
    }

    public List<RecetteDTO> findByIngredient(int ingredientId) {
        List<Recette> recettesByIngredient = recetteRepository.findByRecettesIngredientsIngredientId(ingredientId);
        List<RecetteDTO> recettesByIngredientDTO = recettesByIngredient.stream()
                .map(r -> mapper.map(r, RecetteDTO.class)).collect(Collectors.toList());
        return recettesByIngredientDTO;
    }

    @Override
    public RecetteDTO saveRecette(RecetteDTO recetteDTO) {
        Recette recette = mapper.map(recetteDTO, Recette.class);

        System.out.println(isCategorieExist(recette.getCategorie()));
        saveIngredientNotExists(recette);
        saveCategorieNotExists(recette.getCategorie());

        recette.setTitle(recette.getTitle().toLowerCase());
        Recette recetteEnregistre = recetteRepository.save(recette);

        saveRecetteIngredient(recetteEnregistre, recette.getRecettesIngredients());

        return mapper.map(recetteEnregistre, RecetteDTO.class);
    }

    @Override
    public RecetteDTO updateRecette(RecetteDTO recetteDTO) {
        Recette recette = mapper.map(recetteDTO, Recette.class);

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

        return mapper.map(recetteModifie, RecetteDTO.class);
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
        if (ingredient.getId() != 0 || ingredientRepository.existsByName(ingredient.getName())) {
            return true;
        }
        return false;
    }

    private boolean isCategorieExist(Categorie categorie) {
        if (categorie.getId() != 0 || categorieRepository.existsByName(categorie.getName())) {
            return true;
        }
        return false;
    }

    private void saveCategorieNotExists(Categorie categorie) {
        if (!isCategorieExist(categorie)) {
            categorieRepository.save(categorie);
        }
    }

    private void saveIngredientNotExists(Recette recette) {

        List<Ingredient> ingredients = recette.getIngredients();
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                if (!isIngredientExist(ingredient)) {
                    ingredientRepository.save(ingredient);
                }
            }
        }
    }

    private void saveRecetteIngredient(Recette recetteEnregistre, List<RecetteIngredient> listRecetteIngredient) {
        if (listRecetteIngredient != null) {
            for (RecetteIngredient recetteIngredient : listRecetteIngredient) {
                recetteIngredient.setId(
                        new RecetteIngredientId(recetteEnregistre.getId(), recetteIngredient.getIngredient().getId()));
                recetteIngredient.setRecette(recetteEnregistre);
                System.out.println(recetteIngredient);
                recetteIngredientRepository.save(recetteIngredient);
            }
        }
    }
}
