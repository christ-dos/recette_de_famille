package fr.dawan.formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.model.RecetteIngredient;
import fr.dawan.formation.repository.RecetteIngredientRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

}
