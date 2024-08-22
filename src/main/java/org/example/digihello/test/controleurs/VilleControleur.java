package org.example.digihello.test.controleurs;

import org.example.digihello.test.service.VilleService;
import org.example.digihello.villes.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    // Méthode GET pour obtenir la liste de toutes les villes
    @GetMapping
    public ResponseEntity<List<Ville>> getAllVilles() {
        List<Ville> villes = villeService.extractVilles();
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    // Méthode GET pour obtenir une ville par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable("id") int id) {
        Ville ville = villeService.extractVille(id);
        if (ville != null) {
            return new ResponseEntity<>(ville, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Méthode GET pour obtenir une ville par son nom
    @GetMapping("/nom/{nom}")
    public ResponseEntity<Ville> getVilleByNom(@PathVariable("nom") String nom) {
        Ville ville = villeService.extractVille(nom);
        if (ville != null) {
            return new ResponseEntity<>(ville, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Méthode PUT pour ajouter une nouvelle ville
    @PutMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville) {
        List<Ville> villes = villeService.insertVille(ville);
        return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.OK);
    }

    // Méthode POST pour modifier une ville existante
    @PostMapping("/{id}")
    public ResponseEntity<String> updateVille(@PathVariable("id") int id, @RequestBody Ville villeModifiee) {
        List<Ville> villes = villeService.modifierVille(id, villeModifiee);
        if (villes != null) {
            return new ResponseEntity<>("Ville modifiée avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
        }
    }

    // Méthode DELETE pour supprimer une ville
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable("id") int id) {
        List<Ville> villes = villeService.supprimerVille(id);
        if (villes != null) {
            return new ResponseEntity<>("Ville supprimée avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
        }
    }
}

