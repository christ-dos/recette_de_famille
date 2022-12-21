package fr.dawan.formation.model;

import java.io.Serializable;

import fr.dawan.formation.enumeration.CategorieEnum;

import jakarta.persistence.*;
import lombok.*;

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
@Getter
@Setter
@ToString
@Entity
@Table(name="categories")
public class Categorie implements Serializable {

    @Version
    int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private CategorieEnum name;

}
