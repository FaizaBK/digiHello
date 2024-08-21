package org.example.digihello.villes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    // Liste des villes (base de données temporaire)
    private List<Ville> villes = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(); // Compteur pour générer des ID uniques

    public VilleControleur() {
        // Initialisation avec quelques villes
        villes.add(new Ville(idCounter.incrementAndGet(), "Rabat", 577827));
        villes.add(new Ville(idCounter.incrementAndGet(), "Rome", 2873000));
        villes.add(new Ville(idCounter.incrementAndGet(), "London", 8982000));
        villes.add(new Ville(idCounter.incrementAndGet(), "Paris", 2148327));
    }

    @GetMapping("/liste")
    public List<Ville> getListeVilles() {
        return villes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
        // Recherche de la ville par ID
        Optional<Ville> ville = villes.stream().filter(v -> v.getId() == id).findFirst();
        return ville.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterVille(@RequestBody Ville nouvelleVille) {
        // Vérifier si la ville existe déjà
        for (Ville ville : villes) {
            if (ville.getNom().equalsIgnoreCase(nouvelleVille.getNom())) {
                return new ResponseEntity<>("La ville existe déjà", HttpStatus.CONFLICT);
            }
        }
        // Assigner un nouvel ID à la nouvelle ville
        nouvelleVille.setId(idCounter.incrementAndGet());
        // Ajouter la nouvelle ville à la liste
        villes.add(nouvelleVille);
        return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.OK);
    }

    @PutMapping("/ajouter")
    public ResponseEntity<String> putVille(@RequestBody Ville nouvelleVille) {
        // Vérifier si la ville avec cet ID existe déjà
        for (Ville ville : villes) {
            if (ville.getId() == nouvelleVille.getId()) {
                return new ResponseEntity<>("La ville avec cet ID existe déjà", HttpStatus.CONFLICT);
            }
        }
        // Assigner un nouvel ID à la nouvelle ville si elle n'existe pas
        nouvelleVille.setId(idCounter.incrementAndGet());
        // Ajouter la nouvelle ville à la liste
        villes.add(nouvelleVille);
        return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.OK);
    }

    @PostMapping("/modifier/{id}")
    public ResponseEntity<String> modifierVille(@PathVariable int id, @RequestBody Ville villeModifiee) {
        // Recherche de la ville par ID
        for (Ville ville : villes) {
            if (ville.getId() == id) {
                // Mettre à jour les informations de la ville
                ville.setNom(villeModifiee.getNom());
                ville.setNbHabitants(villeModifiee.getNbHabitants());
                return new ResponseEntity<>("Ville modifiée avec succès", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerVille(@PathVariable int id) {
        // Recherche de la ville par ID
        boolean removed = villes.removeIf(ville -> ville.getId() == id);
        if (removed) {
            return new ResponseEntity<>("Ville supprimée avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
        }
    }
}