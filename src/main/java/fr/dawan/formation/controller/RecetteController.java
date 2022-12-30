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

import fr.dawan.formation.exception.RecetteNotFoundException;
import fr.dawan.formation.interfaces.IRecetteService;
import fr.dawan.formation.model.Recette;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/recette")
public class RecetteController {
    @Autowired
    private IRecetteService recetteService;

    @GetMapping("/all")
    public ResponseEntity<List<Recette>> getAllRecette() {
        List<Recette> recettes = recetteService.findAll();
        log.debug("Controller: Affichage de toutes les recettes");
        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getRecetteById(@Valid @PathVariable("id") int recetteId) {
        Recette recette = recetteService.findById(recetteId);
        log.debug("Controller: recette touvé par ID: " + recetteId);
        return new ResponseEntity<>(recette, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRecette(@Valid @RequestBody Recette recette) {
        Recette newRecette = recetteService.saveRecette(recette);
        log.info("Controller: Recette ajouté");
        return new ResponseEntity<>(newRecette, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRecette(@Valid @RequestBody Recette recette) {
        Recette updateRecette = null;

        try {
            updateRecette = recetteService.updateRecette(recette);
        } catch (RecetteNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
        }

        log.debug("Controller: Recette mit à jour pour l'ID: " + updateRecette.getId());
        return new ResponseEntity<>(updateRecette, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecette(@Valid @PathVariable("id") int recetteId) {
        recetteService.deleteRecette(recetteId);
        log.debug("Controller: Recette effacé pour l'ID: " + recetteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
