package fr.dawan.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.exception.CategorieNotFoundException;
import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.repository.CategorieRepository;
import fr.dawan.formation.service.interfaces.ICategorieService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CategorieService implements ICategorieService {
    private CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public List<Categorie> findAll() {
        log.info("Service: Affichage de la liste des categories");
        return (List<Categorie>) categorieRepository.findAll();
    }

    @Override
    public Categorie findById(int categorieId) {
        Optional<Categorie> categorieRecherche = categorieRepository.findById(categorieId);
        if (categorieRecherche.isEmpty()) {
            throw new CategorieNotFoundException("Cette categorie n'existe pas!");
        }
        log.debug("Service: Categorie recherché par ID: " + categorieRecherche.get().getId());
        return categorieRecherche.get();

    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        Optional<Categorie> categorieRecherche = categorieRepository.findById(categorie.getId());
        if (categorieRecherche.isEmpty()) {
            throw new CategorieNotFoundException("Cette categorie n'existe pas!");
        }
        /**
         * recuperation de la version de la catégorie enregistré en BDD
         */
        categorie.setVersion(categorieRecherche.get().getVersion());
        Categorie categorieModifie = categorieRepository.save(categorie);

        log.debug("Service: Categorie modifiée avec ID: " + categorieModifie.getId());
        return categorieModifie;
    }

    @Override
    public Categorie saveCategorie(Categorie categorie) {
        Optional<Categorie> categorieExiste = categorieRepository.findByName(categorie.getName());
        Categorie categorieSaved = null;
        if (categorieExiste.isEmpty()) {
            categorieSaved = categorieRepository.save(categorie);
        }
        return categorieSaved;

    }

    @Override
    public void deleteCategorie(int id) {
        Optional<Categorie> categorieAEffacer = categorieRepository.findById(id);

        if (categorieAEffacer.get().getId() == id) {
            categorieRepository.deleteById(id);
            log.debug("Categorie effacée avec succés" + categorieAEffacer.get().getName());
        }
    }

}
