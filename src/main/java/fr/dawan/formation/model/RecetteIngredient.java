package fr.dawan.formation.model;

import java.io.Serializable;

import fr.dawan.formation.enumeration.UniteMesureEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe qui décrit une table association entre {@link Recette} et
 * {@link Ingredient}
 *
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "recettes_ingredients")
public class RecetteIngredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private int version;

    /**
     * Clé composite qui fait la jointure entre {@link Recette} et
     * {@link Ingredient}
     */
    @EmbeddedId
    private RecetteIngredientId id = new RecetteIngredientId();

    @Column(nullable = false)
    private int quantite;

    @Enumerated(EnumType.STRING)
    @Column(name = "unite_mesure", length = 30, nullable = false)
    private UniteMesureEnum uniteMesure;

//    @Column(name = "unite_mesure", length = 5, nullable = false)
//    private String uniteMesure;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @MapsId("ingredientId")
    private Ingredient ingredient;

    /**
     * A la suppresion d'un ingrédient je souhaite supprimer les recettes qui le
     * contiennent
     */

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("recetteId")
    private Recette recette;

    @Override
    public String toString() {
        return "RecetteIngredient [id=" + id + ", quantite=" + quantite + ", uniteMesure=" + uniteMesure
                + ", ingredient=" + ingredient + ", recette=" + recette + "]";
    }

}
