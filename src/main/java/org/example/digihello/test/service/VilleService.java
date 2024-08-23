package org.example.digihello.test.service;

import org.example.digihello.test.dto.VilleDto;
import org.example.digihello.test.entities.Ville;
import org.example.digihello.test.exceptions.VilleValidationException;
import org.example.digihello.test.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    public Ville createVille(VilleDto villeDto) throws VilleValidationException {
        // Validation des données du DTO
        if (villeDto.getNbHabitants() < 10) {
            throw new VilleValidationException("La ville doit avoir au moins 10 habitants.");
        }
        if (villeDto.getNom() == null || villeDto.getNom().length() < 2) {
            throw new VilleValidationException("Le nom de la ville doit contenir au moins 2 lettres.");
        }
        if (villeDto.getCodeDepartement() == null || villeDto.getCodeDepartement().length() != 2) {
            throw new VilleValidationException("Le code département doit avoir exactement 2 caractères.");
        }

        // Vérifier l'unicité du nom de la ville dans le département
        Ville existingVille = villeRepository.findByNomAndCodeDepartement(villeDto.getNom(), villeDto.getCodeDepartement());
        if (existingVille != null) {
            throw new VilleValidationException("Le nom de la ville doit être unique pour un département donné.");
        }

        // Création de l'entité Ville à partir du DTO
        Ville ville = new Ville();
        ville.setNom(villeDto.getNom());
        ville.setNbHabitants(villeDto.getNbHabitants());
        ville.setCodeDepartement(villeDto.getCodeDepartement());

        // Persister la ville dans la base de données
        return villeRepository.save(ville);
    }

    public void insertVille(Ville ville1) {
    }
}