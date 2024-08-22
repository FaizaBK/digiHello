package org.example.digihello.test.service;

import org.example.digihello.test.model.Ville;
import org.example.digihello.test.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public Ville getVilleById(Long id) {
        return villeRepository.findById(id).orElse(null);
    }

    public Collection<Ville> extractVilles() {
        return villeRepository.findAll();
    }

    public Ville insertVille(Ville ville) {
        return villeRepository.save(ville);
    }

    // Autres méthodes du service
}