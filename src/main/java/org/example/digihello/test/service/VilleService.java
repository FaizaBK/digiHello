package org.example.digihello.test.service;

import org.example.digihello.test.dto.VilleDto;
import org.example.digihello.test.entities.Ville;
import org.example.digihello.test.repositories.IVilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class VilleService {

    @Autowired
    private IVilleRepository villeRepository;

    // Retrieve all cities
    public Collection<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    // Find a city by ID
    public Ville findById(Long id) {
        return villeRepository.findById(id).orElse(null); // Return null if not found
    }

    // Create a new city
    public Ville create(Ville ville) {
        return villeRepository.save(ville);
    }

    // Update an existing city
    public Ville update(Long id, Ville villeModifie) {
        // Check if the city exists
        if (villeRepository.existsById(id)) {
            villeModifie.setId(id); // Set the ID of the city to be updated
            return villeRepository.save(villeModifie);
        }
        return null; // Return null if city with given ID does not exist
    }

    // Delete a city by ID
    public void delete(Long id) {
        if (villeRepository.existsById(id)) {
            villeRepository.deleteById(id);
        }
    }

    public Ville insertVille(Ville ville1) {
        return villeRepository.save(ville1);
    }


}