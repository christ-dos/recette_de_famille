package fr.dawan.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.formation.interfaces.ICategorieService;
import fr.dawan.formation.model.Categorie;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    private ICategorieService categorieService;

    @GetMapping("/all")
    public ResponseEntity<List<Categorie>> getAllCategorie() {
        List<Categorie> categories = categorieService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getCategorieById(@Valid @PathVariable("id") int categorieId) {
        Categorie categorie = categorieService.findById(categorieId);
        log.debug("Controller: trouver une categorie par l'id: " + categorieId);
        return new ResponseEntity<>(categorie, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategorie(@Valid @RequestBody Categorie categorie) {
        Categorie newCategorie = categorieService.saveCategorie(categorie);
        log.info("Controller: Child added");
        return new ResponseEntity<>(categorie, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategorie(@Valid @RequestBody Categorie categorie) {
        Categorie updatecategorie = categorieService.updateCategorie(categorie);
        log.debug("Controller: categorie modifié grâce à son ID: " + updatecategorie.getId());
        return new ResponseEntity<>(updatecategorie, HttpStatus.OK);
    }
}