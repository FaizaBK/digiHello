package org.example.digihello.services;

import org.example.digihello.entities.Departement;
import org.example.digihello.entities.Ville;
import org.example.digihello.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public abstract class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public void validateDepartement(Departement departement) throws DepartementValidationException {
        if (departement.getCode().length() < 2 || departement.getCode().length() > 3) {
            throw new DepartementValidationException("Le code département doit comporter entre 2 et 3 caractères.");
        }

        if (departement.getNom().length() < 3) {
            throw new DepartementValidationException("Le nom du département doit comporter au moins 3 lettres.");
        }
    }

    // Méthodes d'insertion et de mise à jour
    public Departement insertDepartement(Departement departement) throws DepartementValidationException {
        validateDepartement(departement);
        return departementRepository.save(departement);
    }

    public Departement updateDepartement(Departement departement) throws DepartementValidationException {
        validateDepartement(departement);
        return departementRepository.save(departement);
    }

    public abstract Departement createDepartement(Departement departement);

    public abstract Departement getDepartementByCode(String code);

    public abstract Departement updateDepartement(String code, Departement departement);

    public abstract boolean deleteDepartement(String code);

    public abstract List<Ville> getTopNByDepartement(String code, int n);

    public abstract List<Ville> getVillesByPopulationRange(String code, int min, int max);


    public Departement findOrCreateDepartement(String code, String nom) {
        // Recherche du département par son code
        Departement departement = departementRepository.findByCode(code);

        // Si le département n'existe pas, créez-le et enregistrez-le
        if (departement == null) {
            departement = new Departement();
            departement.setCode(code);
            departement.setNom(nom);
            departement = departementRepository.save(departement);
        }

        return departement;
    }

    public List<Departement> getAllDepartements() {
        return null;
    }
}
