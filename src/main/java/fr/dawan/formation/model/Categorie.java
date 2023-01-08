package fr.dawan.formation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.dawan.formation.enumeration.CategorieEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

/**
 * Classe qui décrit un objet de type {@link Categorie}
 * 
 * @author Kelly Ribeiro
 * @author Amandine Pesquet
 * @author Christine Dos Santos
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categories")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private CategorieEnum name;

    /**
     * A la suppresion d'une catégorie je supprimes les recettes qui y sont liée
     */
    @OneToMany(mappedBy = "categorie")
    @JsonIgnore
    private List<Recette> recettes;

    public void addRecette(Recette recette) {
        if (this.recettes == null) {
            this.recettes = new ArrayList<>();
        }
        this.recettes.add(recette);
    }

    @Override
    public String toString() {
        return "Categorie [id=" + id + ", name=" + name + "]";
    }

}
