package fr.dawan.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.formation.DTO.RecetteDTO;
import fr.dawan.formation.service.interfaces.IRecetteService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/recette")
public class RecetteController {
    @Autowired
    private IRecetteService recetteService;

    @GetMapping("/all")
    public ResponseEntity<List<RecetteDTO>> getAllRecette() {
        List<RecetteDTO> recettes = recetteService.findAll();
        log.debug("Controller: Affichage de toutes les recettes");

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @GetMapping(value = "/all", params = { "page", "size" }, produces = "application/json")
    public ResponseEntity<Page<RecetteDTO>> getAllRecettePagine(Pageable page) {
        Page<RecetteDTO> recettes = recetteService.findAllPageable(page);
        log.debug("Controller: Affichage de toutes les recettes paginées");
        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RecetteDTO> getRecetteById(@Valid @PathVariable("id") int recetteId) {
        RecetteDTO recette = recetteService.findById(recetteId);
        log.debug("Controller: recette touvé par ID: " + recetteId);

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }

    @GetMapping("/find/title")
    public ResponseEntity<List<RecetteDTO>> getRecettesByTitle(@Valid @RequestParam("title") String titreRecette) {
        List<RecetteDTO> recettes = recetteService.findByTitle(titreRecette);
        log.debug("Controller: recette(s) touvé pour le titre: " + titreRecette);

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @GetMapping("/find/categorie/title")
    public ResponseEntity<List<RecetteDTO>> getRecettesByTitleAndCategorieId(@Valid @RequestParam("id") int categorieId,
            @Valid @RequestParam("title") String titreRecette) {
        List<RecetteDTO> recettes = recetteService.findByCategorieIdAndTitleLikeModel(categorieId, titreRecette);

        log.debug("Controller: recette(s) touvé pour le titre: " + titreRecette + " dans la catégorie ID: "
                + categorieId);
        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @GetMapping("/find/ingredient/title")
    public ResponseEntity<List<RecetteDTO>> getRecettesByTitleAndIngredientId(
            @Valid @RequestParam("id") int ingredientId, @Valid @RequestParam("title") String titreRecette) {
        List<RecetteDTO> recettes = recetteService.findByIngredientIdAndTitleLikeModel(ingredientId, titreRecette);

        log.debug("Controller: recette(s) touvé pour le titre: " + titreRecette + " et pour l'ingrédient  ID: "
                + ingredientId);
        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @GetMapping("/find/ingredient/{id}")
    public ResponseEntity<List<RecetteDTO>> getRecettesByIngredientId(@Valid @PathVariable("id") int ingredientId) {
        List<RecetteDTO> recettes = recetteService.findByIngredient(ingredientId);
        log.debug("Controller: recette(s) touvé pour l'ingrédient ID: " + ingredientId);

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @GetMapping("/find/categorie")
    public ResponseEntity<List<RecetteDTO>> getRecettesByCategorieId(@Valid @RequestParam("id") int categorieId) {
        List<RecetteDTO> recettes = recetteService.findByCategorie(categorieId);
        log.debug("Controller: recette(s) touvé pour la categorie ID: " + categorieId);

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<RecetteDTO> addRecette(@Valid @RequestBody RecetteDTO recette) {
        RecetteDTO recetteSaved = recetteService.saveRecette(recette);
        log.info("Controller: Recette ajouté");

        return new ResponseEntity<>(recetteSaved, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RecetteDTO> updateRecette(@Valid @RequestBody RecetteDTO recette) {
        RecetteDTO updateRecette = recetteService.updateRecette(recette);
        log.debug("Controller: Recette mit à jour pour l'ID: " + updateRecette.getId());

        return new ResponseEntity<>(updateRecette, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecette(@Valid @PathVariable("id") int recetteId) {
        recetteService.deleteRecette(recetteId);
        log.debug("Controller: Recette effacé pour l'ID: " + recetteId);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
