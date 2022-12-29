package fr.dawan.formation.interfaces;

import java.util.List;

import fr.dawan.formation.model.Recette;

public interface IRecetteService {

    List<Recette> findAll();

    Recette findById(int id);

    Recette saveRecette(Recette recette);

    Recette updateRecette(Recette recette);

    void deleteRecette(int id);

}