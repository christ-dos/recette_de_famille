package fr.dawan.formation.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe qui décrit un objet de type {@link Ingredient}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    int version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "url_picture", length = 255)
    private String urlPicture;

    @OneToMany(mappedBy = "ingredient")
    private List<RecetteIngredient> recettesIngredients;

}
