package org.example.digihello.test.controleurs;

import org.example.digihello.test.dto.VilleDto;
import org.example.digihello.test.entities.Ville;
import org.example.digihello.test.exceptions.VilleValidationException;
import org.example.digihello.test.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/villes")
public class VilleController {

    @Autowired
    private VilleService villeService;

    @PostMapping
    public ResponseEntity<Ville> createVille(@RequestBody VilleDto villeDto) throws VilleValidationException {
        Ville ville = villeService.createVille(villeDto);
        return ResponseEntity.ok(ville);
    }
}