package org.example.digihello.test.controleurs;

import org.example.digihello.test.model.Ville;
import org.example.digihello.test.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
        // Convertir int en Long
        Long villeId = Long.valueOf(id);
        Ville ville = villeService.getVilleById(villeId);
        if (ville != null) {
            return ResponseEntity.ok(ville);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Autres méthodes du contrôleur
}