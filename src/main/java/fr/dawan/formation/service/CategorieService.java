package fr.dawan.formation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.interfaces.ICategorieService;
import fr.dawan.formation.model.Categorie;
import fr.dawan.formation.repository.CategorieRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CategorieService implements ICategorieService {
    private CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        super();
        this.categorieRepository = categorieRepository;
    }

    @Override
    public void deleteCategorie(int id) {
        Optional<Categorie> categorieAEffacer = categorieRepository.findById(id);
        if (categorieAEffacer.get().getId() == id) {
            categorieRepository.deleteById(id);
            log.debug("Categorie effacée avec succés" + categorieAEffacer.get().getName());
        }
    }

    public Categorie saveCategorie(Categorie categorie) {
        Optional<Categorie> categorieExiste = categorieRepository.findByName(categorie.getName());
        Categorie categorieSaved = null;
        if (categorieExiste.isEmpty()) {
            categorieSaved = categorieRepository.save(categorie);
        }
        return categorieSaved;

    }

}
