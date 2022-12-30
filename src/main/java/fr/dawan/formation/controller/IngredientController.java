package fr.dawan.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.formation.interfaces.IIngredientService;
import fr.dawan.formation.model.Ingredient;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ingredient")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class IngredientController {

    @Autowired
    private IIngredientService ingredientService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Ingredient>> getAllIngredient() {
        List<Ingredient> ingredients = ingredientService.findAll();
        log.info("Controller: Affichage de la liste d'ingredients");
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getIngredientById(@Valid @PathVariable("id") int ingredientId) {
        Ingredient ingredient = ingredientService.findById(ingredientId);
        log.debug("Controller: Ingrédient trouvé pour l'ID: " + ingredientId);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIngredient(@Valid @RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.saveIngredient(ingredient);
        log.debug("Controller: Ingredient ajouté pour ID: " + newIngredient.getId());
        return new ResponseEntity<>(newIngredient, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateIngredient(@Valid @RequestBody Ingredient ingredient) {
        Ingredient updateIngredient = ingredientService.updateIngredient(ingredient);
        log.debug("Controller: Ingredient modifié grâce à son ID: " + updateIngredient.getId());
        return new ResponseEntity<>(updateIngredient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIngredient(@Valid @PathVariable("id") int ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        log.debug("Controller: Ingredient effacé pour l'ID: " + ingredientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
