package fr.dawan.formation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.dawan.formation.DTO.RecetteDTO;
import fr.dawan.formation.enumeration.CategorieEnum;
import fr.dawan.formation.enumeration.UniteMesureEnum;
import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;
import fr.dawan.formation.repository.RecetteRepository;
import fr.dawan.formation.service.interfaces.ICategorieService;
import fr.dawan.formation.service.interfaces.IIngredientService;
import fr.dawan.formation.service.interfaces.IRecetteIngredientService;
import fr.dawan.formation.service.interfaces.IRecetteService;

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

    @Autowired
    private RecetteRepository recetteRepository;
    @Autowired
    ModelMapper mapper;

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
        recette.setUrlPicture(
                "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/69e0b8aa-23ac-4292-9f6e-ab2c1a96a480/Derivates/7b6fec2b-ec98-4e00-8b11-65fcf39f4fa5.jpg");
        recette.setDifficultyLevel("facile");
        recette.setTotalTimePreparation("60 mins");
        recette.setNumberOfPeople("4");

        Recette recette1 = new Recette();
        recette1.setTitle("Tomates Mozza");
        recette1.setStepPreparation("lorem bla bla bla");
        recette1.setTimePreparation("20 min");
        recette1.setUrlPicture(
                "https://img-3.journaldesfemmes.fr/7lpM6OiA6MTyOen5pF3x3HlTbfE=/750x500/smart/f5013f26a31545f7bb5e896c3423f8e3/recipe-jdf/10026610.jpg");
        recette1.setDifficultyLevel("facile");
        recette1.setTotalTimePreparation("15 mins");
        recette1.setNumberOfPeople("4");

        Recette recette2 = new Recette();
        recette2.setTitle("Bolo arroz");
        recette2.setStepPreparation("lorem bla bla bla");
        recette2.setTimePreparation("50 min");
        recette2.setUrlPicture("https://abmauri.pt/wp-content/uploads/2020/02/receita-bolo-de-arroz.jpg");
        recette2.setDifficultyLevel("facile");
        recette2.setTotalTimePreparation("60 mins");
        recette2.setNumberOfPeople("4");
        // creation ingredients

        Ingredient poulet = new Ingredient();
        Ingredient poivron = new Ingredient();
        Ingredient mozza = new Ingredient();
        Ingredient farine = new Ingredient();

        poulet.setName("poulet");
        // poulet.setId(1);
        poivron.setName("poivron");
        // poivron.setId(2);
        mozza.setName("mozza");
        // mozza.setId(3);
        farine.setName("farine");
        // farine.setId(4);

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
        rectteIngredient4.setIngredient(mozza);
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

//        Iterable<Recette> recettes = recetteService.findByTitle("tomates mozza");
//        recettes.forEach(x -> System.out.println(x));
//
//        List<Recette> recettes1 = recetteIngredientService.findRecetteByIngredientId(3);
//        recettes.forEach(x -> System.out.println(x));
//        System.out.println(recettes1.size());
//
//        recettes = recetteService.findByCategorie(1);
//
//        recettes.forEach(x -> System.out.println(x));
//        System.out.println(recettes1.size());
        System.out.println("************************************************************");
        List<RecetteDTO> rec = recetteService.findByIngredient(3);
        rec.forEach(x -> System.out.println(x));
    }

}
