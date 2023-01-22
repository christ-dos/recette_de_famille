package fr.dawan.formation.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.formation.DTO.CategorieDTO;
import fr.dawan.formation.exception.CategorieAlreadyExistException;
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
    private ModelMapper mapper;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository, ModelMapper mapper) {
        this.categorieRepository = categorieRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CategorieDTO> findAll() {
        List<Categorie> categories = (List<Categorie>) categorieRepository.findAll();
        log.info("Service: Affichage de la liste des categories");

        return categories.stream().map(c -> mapper.map(c, CategorieDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategorieDTO findById(int categorieId) {
        Optional<Categorie> categorieRecherche = categorieRepository.findById(categorieId);
        if (categorieRecherche.isEmpty()) {
            throw new CategorieNotFoundException("Cette categorie n'existe pas!");
        }
        log.debug("Service: Categorie recherché par ID: " + categorieRecherche.get().getId());

        return mapper.map(categorieRecherche.get(), CategorieDTO.class);
    }

    @Override
    public CategorieDTO saveCategorie(CategorieDTO categorieDTO) {
        Categorie categorie = mapper.map(categorieDTO, Categorie.class);

        Optional<Categorie> categorieExiste = categorieRepository.findByName(categorie.getName());

        if (!categorieExiste.isEmpty()) {
            throw new CategorieAlreadyExistException("Cette Catégorie existe déjà!!");
        }
        Categorie categorieSaved = categorieRepository.save(categorie);

        return mapper.map(categorieSaved, CategorieDTO.class);

    }

    @Override
    public CategorieDTO updateCategorie(CategorieDTO categorieDTO) {
        Categorie categorie = mapper.map(categorieDTO, Categorie.class);

        Optional<Categorie> categorieRecherche = categorieRepository.findById(categorie.getId());
        if (categorieRecherche.isEmpty()) {
            throw new CategorieNotFoundException("Cette catégorie n'existe pas!");
        }
//        if (categorieRepository.existsByName(categorie.getName())) {
//            throw new CategorieAlreadyExistException("Le nom de cette catégorie existe déja!!");
//        }
        /**
         * recuperation de la version de la catégorie enregistré en BDD
         */
        categorie.setVersion(categorieRecherche.get().getVersion());
        Categorie categorieModifie = categorieRepository.save(categorie);

        log.debug("Service: Categorie modifiée avec ID: " + categorieModifie.getId());

        return mapper.map(categorieModifie, CategorieDTO.class);
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
