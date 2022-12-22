package fr.dawan.formation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.dawan.formation.model.Ingredient;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.model.RecetteIngredient;

@SpringBootApplication
public class RecetteEnFamille1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RecetteEnFamille1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

        Recette recette = new Recette();
        recette.setTitle("poulet basquaise");
        Ingredient poulet = new Ingredient();
        Ingredient poivron = new Ingredient();
        poulet.setName("poulet");
        poivron.setName("poivron");

        RecetteIngredient recetteIngredient = new RecetteIngredient();
        recetteIngredient.setRecette(recette);
        recetteIngredient.setIngredient(poulet);
        recetteIngredient.setQuantité(1);
        recetteIngredient.setUniteMesure("pièce");
        recette.ajouterRecetteIngredient(recetteIngredient);

    }

}
