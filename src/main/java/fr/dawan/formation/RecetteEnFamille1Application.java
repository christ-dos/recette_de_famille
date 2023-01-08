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
import fr.dawan.formation.interfaces.IRecetteIngredientService;
import fr.dawan.formation.interfaces.IRecetteService;
import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;

@SpringBootApplication
public class RecetteEnFamille1Application implements CommandLineRunner {
    @Autowired
    private IRecetteService recetteService;

    @Autowired
    private ICategorieService categorieService;

    @Autowired
    private IRecetteIngredientService recetteIngredientService;

    @Autowired
    private IIngredientService ingredientService;

    public static void main(String[] args) {
        SpringApplication.run(RecetteEnFamille1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // creation d'une recette
        Recette recette = new Recette();
        recette.setTitle("poulet basquaise");
        recette.setStepPreparation("lorem bla bla bla");
        recette.setTimePreparation("20 min");

        Recette recette1 = new Recette();
        recette1.setTitle("Tomates Mozza");
        recette1.setStepPreparation("lorem bla bla bla");
        recette1.setTimePreparation("20 min");

        Recette recette2 = new Recette();
        recette2.setTitle("Bolo arroz");
        recette2.setStepPreparation("lorem bla bla bla");
        recette2.setTimePreparation("50 min");

        // creation ingredients

        Ingredient poulet = new Ingredient();
        Ingredient poivron = new Ingredient();
        Ingredient mozza = new Ingredient();
        Ingredient farine = new Ingredient();
        poulet.setName("poulet");
        poivron.setName("poivron");
        mozza.setName("mozza");
        farine.setName("farine");

//        ingredientService.saveIngredient(poulet);
//        ingredientService.saveIngredient(poivron);
//        ingredientService.saveIngredient(mozza);

        // mettre une ctagorie à la recette
        Categorie plat = new Categorie();
        plat.setName(CategorieEnum.PLATS);
        recette.setCategorie(plat);

        Categorie entree = new Categorie();
        entree.setName(CategorieEnum.ENTREES);
        recette1.setCategorie(entree);

        Categorie dessert = new Categorie();
        dessert.setName(CategorieEnum.DESSERTS);
        recette2.setCategorie(dessert);

//        // ajiuter d'un IngredientRecette à le recette
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

        RecetteIngredient rectteIngredient3 = new RecetteIngredient();
        rectteIngredient3.setIngredient(mozza);
        rectteIngredient3.setQuantite(1);
        rectteIngredient3.setUniteMesure(UniteMesureEnum.PIECE);
        rectteIngredient3.setRecette(recette1);

        RecetteIngredient rectteIngredient4 = new RecetteIngredient();
        rectteIngredient4.setIngredient(farine);
        rectteIngredient4.setQuantite(1);
        rectteIngredient4.setUniteMesure(UniteMesureEnum.PIECE);
        rectteIngredient4.setRecette(recette2);

        recette.ajouterRecetteIngredient(rectteIngredient1);
        recette.ajouterRecetteIngredient(rectteIngredient2);
        recette1.ajouterRecetteIngredient(rectteIngredient3);
        recette2.ajouterRecetteIngredient(rectteIngredient4);

        recetteService.saveRecette(recette);
        recetteService.saveRecette(recette1);
        recetteService.saveRecette(recette2);
        // recetteService.updateRecette(recette)
        // categorieService.deleteCategorie(1);
        // ingredientService.deleteIngredient(1);
        // recetteService.deleteRecette(1);

//        List<Recette> recettes = recetteService.findAll();
//        System.out.println(recettes);

//        Ingredient tomate = new Ingredient();
//        tomate.setName("poires");
//        System.out.println("version de tomate avnat la suvegerde:" + tomate.getVersion());
//        tomate.setId(4);

        // Ingredient ingredient = ingredientService.updateIngredient(tomate);
        // System.out.println("version de tomate avnat la suvegerde:" +
        // ingredient.getVersion());
        // System.out.println(ingredient);
        // tomate.setVersion(ingredient.getVersion());

        Iterable<Recette> recettes = recetteService.findByTitle("tomates mozza");
        recettes.forEach(x -> System.out.println(x));

        List<Recette> recettes1 = recetteIngredientService.findRecetteByIngredientId(3);
        recettes.forEach(x -> System.out.println(x));
        System.out.println(recettes1.size());

        recettes = recetteService.findByCategorie(1);

        recettes.forEach(x -> System.out.println(x));
        System.out.println(recettes1.size());

    }

}
