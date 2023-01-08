package fr.dawan.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.formation.interfaces.IRecetteIngredientService;
import fr.dawan.formation.model.Recette;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RecetteIngredientController {
    @Autowired
    private IRecetteIngredientService RecetteIngredientService;

    @GetMapping("/recettes/find/ingredient")
    public ResponseEntity<List<Recette>> getRecettesByIngredientId(@Valid @RequestParam("id") int ingredientId) {
        List<Recette> recettes = RecetteIngredientService.findRecetteByIngredientId(ingredientId);
        log.debug("Controller: recette(s) touvé pour l'ingrédeint ID: " + ingredientId);

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

}
