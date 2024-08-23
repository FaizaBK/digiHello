package org.example.digihello.test.controleurs;

import org.example.digihello.test.dto.VilleDto;
import org.example.digihello.test.entities.Departement;
import org.example.digihello.test.entities.Ville;
import org.example.digihello.test.exceptions.VilleValidationException;
import org.example.digihello.test.service.DepartementService;
import org.example.digihello.test.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
     //  fonction d'exportation CSV
    @GetMapping("/exportCSV")
    public ResponseEntity<String> exportVillesToCSV() {
        List<Ville> villes = (List<Ville>) villeService.getAllVilles();

        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Nom de la ville,Nombre d'habitants,Code département,Nom du département\n");

        for (Ville ville : villes) {
            csvContent.append(ville.getNom()).append(",")
                    .append(ville.getNbHabitants()).append(",")
                    .append(ville.getDepartement().getCode()).append(",")
                    .append(ville.getDepartement().getNom()).append("\n");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=villes.csv");

        return new ResponseEntity<>(csvContent.toString(), headers, HttpStatus.OK);
    }

}