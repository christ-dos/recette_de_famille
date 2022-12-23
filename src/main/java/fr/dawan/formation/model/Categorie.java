package fr.dawan.formation.model;

import java.io.Serializable;
import java.util.List;

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
import lombok.ToString;

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
@Table(name = "categories")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private CategorieEnum name;

    @OneToMany(mappedBy = "categorie")
    private List<Recette> recettes;

}
