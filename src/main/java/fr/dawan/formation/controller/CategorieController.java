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

import fr.dawan.formation.DTO.CategorieDTO;
import fr.dawan.formation.service.interfaces.ICategorieService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    private ICategorieService categorieService;

    @GetMapping("/all")
    public ResponseEntity<List<CategorieDTO>> getAllCategorie() {
        List<CategorieDTO> categories = categorieService.findAll();
        log.info("Controller: Affichage de toutes les catégories");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CategorieDTO> getCategorieById(@Valid @PathVariable("id") int categorieId) {
        CategorieDTO categorie = categorieService.findById(categorieId);
        log.debug("Controller: trouver une categorie par l'ID: " + categorieId);
        return new ResponseEntity<>(categorie, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CategorieDTO> addCategorie(@Valid @RequestBody CategorieDTO categorie) {

        CategorieDTO newCategorie = categorieService.saveCategorie(categorie);
        log.info("Controller: Catégorie added");
        return new ResponseEntity<>(categorie, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CategorieDTO> updateCategorie(@Valid @RequestBody CategorieDTO categorie) {
        CategorieDTO updatecategorieDTO = categorieService.updateCategorie(categorie);
        log.debug("Controller: catégorie modifié grâce à son ID: " + updatecategorieDTO.getId());
        return new ResponseEntity<>(updatecategorieDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategorie(@Valid @PathVariable("id") int categorieId) {
        categorieService.deleteCategorie(categorieId);
        log.debug("Controller: Catégorie effacé pour l'ID: " + categorieId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}