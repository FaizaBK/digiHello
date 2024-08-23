package org.example.digihello.test.controleurs;

import org.example.digihello.test.dto.VilleDto;
import org.example.digihello.test.entities.Departement;
import org.example.digihello.test.entities.Ville;
import org.example.digihello.test.exceptions.VilleValidationException;
import org.example.digihello.test.service.DepartementService;
import org.example.digihello.test.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/villes")
public class VilleController {

    @Autowired
    private VilleService villeService;
    @Autowired
    private DepartementService departementService;

    @PostMapping
    public ResponseEntity<Ville> createVille(@RequestBody VilleDto villeDto) throws VilleValidationException {
        // Trouver le département associé
     //  Departement departement = departementService.getDepartementByCode(villeDto.getCodeDepartement())
        //        .orElseThrow(() -> new VilleValidationException("Département non trouvé"));

        // Mapper VilleDto en Ville
        Ville ville = new Ville();
        ville.setNom(villeDto.getNom());
        ville.setCodeDepartement(villeDto.getCodeDepartement());
        ville.setNbHabitants(villeDto.getNbHabitants());


        // Enregistrer la ville
        Ville savedVille = villeService.insertVille(ville);

        return ResponseEntity.ok(savedVille);
    }

}