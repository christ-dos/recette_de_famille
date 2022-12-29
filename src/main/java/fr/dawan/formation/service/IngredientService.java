package fr.dawan.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.exception.IngredientNotFoundException;
import fr.dawan.formation.interfaces.IIngredientService;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientService implements IIngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {

        Ingredient ingredientEnregistre = ingredientRepository.save(ingredient);

        log.debug("service : ingredient enregistré avec l'id :  " + ingredientEnregistre.getId());
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {

        Optional<Ingredient> ingredientRecherche = ingredientRepository.findById(ingredient.getId());

        if (ingredientRecherche.isEmpty()) {
            throw new IngredientNotFoundException("cet ingrédient n'existe pas! ");

        }

        Ingredient ingredientModifie = ingredientRepository.save(ingredient);

        log.debug("service : ingredient modifié avec l'id :  " + ingredientModifie.getId());

        return ingredientModifie;

    }

    @Override
    public List<Ingredient> findAll() {

        log.info("service : affichage liste des ingredients recherchés :  ");
        return (List<Ingredient>) ingredientRepository.findAll();
    }

    @Override
    public Ingredient findById(int id) {

        Optional<Ingredient> ingredientRecherche = ingredientRepository.findById(id);

        if (ingredientRecherche.isEmpty()) {

            throw new IngredientNotFoundException("cet ingrédient n'existe pas! ");

        }
        log.debug("service : affichage de l'ingredient recherché avec l'id :  " + ingredientRecherche.get().getId());

        return ingredientRecherche.get();
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
