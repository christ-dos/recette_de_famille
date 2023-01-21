package fr.dawan.formation.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.dawan.formation.enumeration.CategorieEnum;
import fr.dawan.formation.model.Recette;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategorieDTO {

    private int id;

    private CategorieEnum name;

    private String urlPicture;

    @JsonIgnore
    private List<Recette> recettes;
}