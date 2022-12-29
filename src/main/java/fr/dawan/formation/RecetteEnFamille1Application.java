package fr.dawan.formation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.dawan.formation.enumeration.CategorieEnum;
import fr.dawan.formation.enumeration.UniteMesureEnum;
import fr.dawan.formation.interfaces.ICategorieService;
import fr.dawan.formation.interfaces.IIngredientService;
import fr.dawan.formation.interfaces.IRecetteService;
import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;
import fr.dawan.formation.service.RecetteIngredientService;

@SpringBootApplication
public class RecetteEnFamille1Application implements CommandLineRunner {
    @Autowired
    private IRecetteService recetteService;

    @Autowired
    private ICategorieService categorieService;

    @Autowired
    private RecetteIngredientService recetteIngredientService;

    @Autowired
    private IIngredientService ingredientService;

    public static void main(String[] args) {
        SpringApplication.run(RecetteEnFamille1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

        Recette recette = new Recette();
        recette.setTitle("poulet basquaise");
        recette.setStepPreparation("lorem bla bla bla");
        recette.setTimePreparation("20 min");

        Ingredient poulet = new Ingredient();
        Ingredient poivron = new Ingredient();
        poulet.setName("poulet");
        poivron.setName("poivron");

//        ingredientService.saveIngredient(poulet);
//        ingredientService.saveIngredient(poivron);

        Categorie plat = new Categorie();
        plat.setName(CategorieEnum.PLATS);
        recette.setCategorie(plat);

        RecetteIngredient rectteIngredient1 = new RecetteIngredient();
        rectteIngredient1.setIngredient(poulet);
        rectteIngredient1.setQuantite(1);
        rectteIngredient1.setUniteMesure(UniteMesureEnum.PIECE);
        rectteIngredient1.setRecette(recette);

        RecetteIngredient rectteIngredient2 = new RecetteIngredient();
        rectteIngredient2.setIngredient(poivron);
        rectteIngredient2.setQuantite(100);
        rectteIngredient2.setUniteMesure(UniteMesureEnum.GRAMME);
        rectteIngredient2.setRecette(recette);

        recette.ajouterRecetteIngredient(rectteIngredient1);
        recette.ajouterRecetteIngredient(rectteIngredient2);

        recetteService.saveRecette(recette);
        // recetteService.deleteRecette(1);
        // categorieService.deleteCategorie(1);
        // ingredientService.deleteIngredient(1);
        // recetteService.deleteRecette(1);

        List<Recette> recettes = recetteService.findAll();
        System.out.println(recettes);

    }

}
