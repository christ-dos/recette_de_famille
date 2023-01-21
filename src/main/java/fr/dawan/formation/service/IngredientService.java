package fr.dawan.formation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.DTO.IngredientDTO;
import fr.dawan.formation.exception.IngredientNotFoundException;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.repository.IngredientRepository;
import fr.dawan.formation.service.interfaces.IIngredientService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class IngredientService implements IIngredientService {

    private IngredientRepository ingredientRepository;
    private ModelMapper mapper;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, ModelMapper mapper) {
        this.mapper=mapper;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<IngredientDTO> findAll() {
        log.info("service : affichage liste des ingredients recherchés :  ");
        List<Ingredient> ingredients= (List<Ingredient>) ingredientRepository.findAll();
        
        return ingredients.stream().map(i->mapper.map(ingredients, IngredientDTO.class)).collect(Collectors.toList());
    }

    @Override
    public IngredientDTO findById(int id) {
        Optional<Ingredient> ingredientRecherche = ingredientRepository.findById(id);

        if (ingredientRecherche.isEmpty()) {
            log.error("Service: Ingrédient non trouvé");
            throw new IngredientNotFoundException("Cet ingrédient n'existe pas! ");
        }
        log.debug("service : affichage de l'ingredient recherché avec l'id :  " + ingredientRecherche.get().getId());

        return mapper.map(ingredientRecherche.get(), IngredientDTO.class);
    }

    @Override
    public IngredientDTO saveIngredient(Ingredient ingredient) {

        Ingredient ingredientEnregistre = ingredientRepository.save(ingredient);

        log.debug("service : Ingrédient enregistré pour l'id :  " + ingredientEnregistre.getId());
        return mapper.map(ingredientEnregistre, IngredientDTO.class) ;
    }

    @Override
    public IngredientDTO updateIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingredientRecherche = ingredientRepository.findById(ingredient.getId());

        if (ingredientRecherche.isEmpty()) {
            log.error("Service: Ingrédient non trouvé");
            throw new IngredientNotFoundException("Cet ingrédient n'existe pas! ");
        }
        /**
         * recuperation de la version de l'ingrédient enregistré en BDD
         */
        ingredient.setVersion(ingredientRecherche.get().getVersion());
        Ingredient ingredientModifie = ingredientRepository.save(ingredient);

        log.debug("service : ingredient modifié avec l'id :  " + ingredientModifie.getId());
        return mapper.map(ingredientModifie, IngredientDTO.class) ;

    }

    @Override
    public void deleteIngredient(int id) {
        Optional<Ingredient> ingredientAEffacer = ingredientRepository.findById(id);

        if (ingredientAEffacer.get().getId() == id) {
            ingredientRepository.deleteById(id);
            log.debug("service : l'ingredient effacé avec succés:  " + ingredientAEffacer.get().getName());
        }

    }
}
