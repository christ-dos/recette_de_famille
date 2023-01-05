package fr.dawan.formation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;
import fr.dawan.formation.repository.RecetteIngredientRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class RecetteIngredientService {

    private RecetteIngredientRepository recetteIngredientRepository;

    @Autowired
    public RecetteIngredientService(RecetteIngredientRepository recetteIngredientRepository) {
        this.recetteIngredientRepository = recetteIngredientRepository;
    }

    public void saveRecetteIngredient(RecetteIngredient recetteIngredient) {
        RecetteIngredient recetteIngredientEnregistre = recetteIngredientRepository.save(recetteIngredient);
        log.debug("Service: Recette ingredient enregistré avec ID: " + recetteIngredientEnregistre.getId());
    }

    public List<Recette> getRecetteByIngredient(int ingredientId) {
        List<RecetteIngredient> recettesIngredients = (List<RecetteIngredient>) recetteIngredientRepository
                .findByIngredientId(ingredientId);

        List<Recette> recettes = recettesIngredients.stream().map(RecetteIngredient -> RecetteIngredient.getRecette())
                .collect(Collectors.toList());

        return recettes;

    }

}
