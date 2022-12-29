package fr.dawan.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.exception.RecetteNotFoundException;
import fr.dawan.formation.interfaces.IRecetteService;
import fr.dawan.formation.model.Recette;
import fr.dawan.formation.repository.RecetteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecetteService implements IRecetteService {

    private RecetteRepository recetteRepository;

    @Autowired
    public RecetteService(RecetteRepository recetteRepository) {
        this.recetteRepository = recetteRepository;
    }

    @Override
    public List<Recette> findAll() {
        log.info("Service: Affichage de la liste de recettes");
        return (List<Recette>) recetteRepository.findAll();
    }

    @Override
    public Recette findById(int id) {
        Optional<Recette> recetteRecherche = recetteRepository.findById(id);
        if (recetteRecherche.isEmpty()) {
            throw new RecetteNotFoundException("Cette recette n'existe pas!");
        }
        log.debug("Service: Recette recherché par ID: " + recetteRecherche.get().getId());
        return recetteRecherche.get();

    }

    @Override
    public void saveRecette(Recette recette) {
        Recette recetteEnregistre = recetteRepository.save(recette);
        log.debug("Service: Recette enregistré avec ID: " + recetteEnregistre.getId());
    }

    @Override
    public Recette updateRecette(Recette recette) {
        Optional<Recette> recetteRecherche = recetteRepository.findById(recette.getId());
        if (recetteRecherche.isEmpty()) {
            throw new RecetteNotFoundException("Cette recette n'existe pas!");
        }
        Recette recetteModifie = recetteRepository.save(recette);

        log.debug("Service: Recette modifiée avec ID: " + recetteModifie.getId());
        return recetteModifie;
    }

    @Override
    public void deleteRecette(int id) {
        Optional<Recette> recetteAEffacer = recetteRepository.findById(id);
        if (recetteAEffacer.get().getId() == id) {
            recetteRepository.deleteById(id);
            log.debug("Recette effacée avec succés" + recetteAEffacer.get().getTitle());
        }
    }

}
