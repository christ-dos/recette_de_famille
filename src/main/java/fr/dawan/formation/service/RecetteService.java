package fr.dawan.formation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

//    @Override
//    public Page<RecetteDTO> findAllPageable(Pageable pageable) {
//        List<Recette> recettesPaginé = recetteRepository.findAll(pageable).get
//        System.out.println(recetteRepository.findAll(pageable).getTotalPages());
//        System.out.println(recettesPaginé);
//        log.info("Service: Affichage de la liste de recettes");
//        List<Recette> recettesPaginéDto = recettesPaginé.get().map(r -> mapper.map(r, RecetteDTO.class))
//                .collect(Collectors.toList());
//        return 
//        // recettesPaginé.stream().map(r -> mapper.map(r,
//        // RecetteDTO.class)).collect(Collectors.toList());
//        // recettesPaginé.stream().map(r -> mapper.map(r,
//        // RecetteDTO.class)).collect(Collectors.toList());
//    }

    @Override
    public Page<RecetteDTO> findAllPageable(Pageable pageable) {
        List<Recette> recettesPaginé = recetteRepository.findAll(pageable).getContent();
        return new PageImpl<RecetteDTO>(
                recettesPaginé.stream().map(r -> mapper.map(r, RecetteDTO.class)).collect(Collectors.toList()));
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
        List<Recette> recettes = recetteRepository.findByTitleLike(title.toLowerCase());

        List<RecetteDTO> recettesDTO = recettes.stream().map(r -> mapper.map(r, RecetteDTO.class))
                .collect(Collectors.toList());

        log.debug("Service: Recette recherché par titre: " + title);
        return recettesDTO;

    }

    @Override
    public List<RecetteDTO> findByCategorieIdAndTitleLikeModel(int categorieId, String title) {
        List<Recette> recettesByTitleAndCategorie = recetteRepository.findByCategorieIdAndTitleLike(categorieId,
                title.toLowerCase());

        List<RecetteDTO> recettesDTO = recettesByTitleAndCategorie.stream().map(r -> mapper.map(r, RecetteDTO.class))
                .collect(Collectors.toList());

        log.debug("Service: Recette recherché par titre: " + title + " dans la catégorie ID: " + categorieId);
        return recettesDTO;

    }

    @Override
    public List<RecetteDTO> findByIngredientIdAndTitleLikeModel(int ingredientId, String title) {
        List<Recette> recettesByTitleAndIngredient = recetteRepository
                .findByRecettesIngredientsIngredientIdAndTitleLike(ingredientId, title.toLowerCase());

        List<RecetteDTO> recettesDTO = recettesByTitleAndIngredient.stream().map(r -> mapper.map(r, RecetteDTO.class))
                .collect(Collectors.toList());

        log.debug("Service: Recette recherché par titre: " + title + " pour  l'ingrédeint ID: " + ingredientId);
        return recettesDTO;

    }

    @Override
    public List<RecetteDTO> findByCategorie(int categorieId) {
        List<Recette> recettesByCategorie = recetteRepository.findByCategorieId(categorieId);

        List<RecetteDTO> recettesByCategorieDTO = recettesByCategorie.stream().map(r -> mapper.map(r, RecetteDTO.class))
                .collect(Collectors.toList());

        log.debug("Service: Recette recherché par catégorie ID: " + categorieId);
        return recettesByCategorieDTO;
    }

    @Override
    public List<RecetteDTO> findByIngredient(int ingredientId) {
        List<Recette> recettesByIngredient = recetteRepository.findByRecettesIngredientsIngredientId(ingredientId);

        List<RecetteDTO> recettesByIngredientDTO = recettesByIngredient.stream()
                .map(r -> mapper.map(r, RecetteDTO.class)).collect(Collectors.toList());

        log.debug("Service: Recette recherché par ingédient ID: " + ingredientId);
        return recettesByIngredientDTO;
    }

    @Override
    public RecetteDTO saveRecette(RecetteDTO recetteDTO) {
        Recette recette = mapper.map(recetteDTO, Recette.class);

        saveIngredientNotExists(recette);
        saveCategorieNotExists(recette.getCategorie());

        recette.setTitle(recette.getTitle().toLowerCase());
        Recette recetteEnregistre = recetteRepository.save(recette);

        saveRecetteIngredient(recetteEnregistre, recette.getRecettesIngredients());

        log.debug("Service: Recette ajoutée pour ID: " + recetteEnregistre.getId());
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
        if (ingredient != null
                && (ingredient.getId() != 0 || ingredientRepository.existsByName(ingredient.getName()))) {
            log.debug("Service(private): Ingédient existe en BDD");
            return true;
        }

        log.debug("Service(private): Ingédient n'existe pas en BDD");
        return false;
    }

    private boolean isCategorieExist(Categorie categorie) {
        if (categorie.getId() != 0 || categorieRepository.existsByName(categorie.getName())) {

            log.debug("Service(private): Catégorie existe en BDD");
            return true;
        }
        log.debug("Service(private): Catégorie n'existe pas en BDD");
        return false;
    }

    private void saveCategorieNotExists(Categorie categorie) {
        if (!isCategorieExist(categorie)) {
            Categorie categorieEnregistre = categorieRepository.save(categorie);
            log.debug("Service(private): Catégorie enregistré pour ID: " + categorieEnregistre.getId());
        }
    }

    private void saveIngredientNotExists(Recette recette) {
        List<Ingredient> ingredients = recette.getIngredients();

        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                if (!isIngredientExist(ingredient)) {
                    Ingredient ingredientEnregistre = ingredientRepository.save(ingredient);
                    log.debug("Service(private): Ingédient enregistré pour ID: " + ingredientEnregistre.getId());
                }
            }
        }
    }

    private void saveRecetteIngredient(Recette recetteEnregistre, List<RecetteIngredient> listRecetteIngredient) {
        if (listRecetteIngredient != null) {
            RecetteIngredient recetteIngredientEnregistre = null;
            for (RecetteIngredient recetteIngredient : listRecetteIngredient) {
                recetteIngredient.setId(
                        new RecetteIngredientId(recetteEnregistre.getId(), recetteIngredient.getIngredient().getId()));
                recetteIngredient.setRecette(recetteEnregistre);

                recetteIngredientEnregistre = recetteIngredientRepository.save(recetteIngredient);
            }
            log.debug("Service(private): RecetteIngredient enregistré pour ID: " + recetteIngredientEnregistre.getId());

        }
    }
}
