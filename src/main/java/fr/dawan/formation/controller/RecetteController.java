package fr.dawan.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.formation.interfaces.IRecetteService;
import fr.dawan.formation.model.Recette;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/recette")
public class RecetteController {
    @Autowired
    private IRecetteService recetteService;

    @GetMapping("/all")
    public ResponseEntity<List<Recette>> getAllRecette() {
        List<Recette> recettes = recetteService.findAll();
        return new ResponseEntity<>(recettes, HttpStatus.OK);

    }
}
