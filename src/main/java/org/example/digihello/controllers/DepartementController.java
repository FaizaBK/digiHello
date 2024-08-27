package org.example.digihello.controllers;

import org.example.digihello.dto.DepartementDto;
import org.example.digihello.entities.Departement;
import org.example.digihello.entities.Ville;
import org.example.digihello.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @PostMapping
    public ResponseEntity<Departement> createDepartement(@RequestBody DepartementDto departementDto) {
        Departement departement = new Departement();
        departement.setCode(departementDto.getCode());
        departement.setNom(departementDto.getNom());

        Departement createdDepartement = departementService.createDepartement(departement);
        return new ResponseEntity<>(createdDepartement, HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Departement> getDepartementByCode(@PathVariable String code) {
        Departement departement = departementService.getDepartementByCode(code);
        if (departement == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departement, HttpStatus.OK);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Departement> updateDepartement(
            @PathVariable String code,
            @RequestBody DepartementDto departementDto) {

        Departement departement = new Departement();
        departement.setCode(code);
        departement.setNom(departementDto.getNom());

        Departement updatedDepartement = departementService.updateDepartement(code, departement);
        if (updatedDepartement == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDepartement, HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable String code) {
        if (departementService.deleteDepartement(code)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{code}/topVilles")
    public ResponseEntity<List<Ville>> getTopNByDepartement(
            @PathVariable String code,
            @RequestParam int n) {

        List<Ville> villes = departementService.getTopNByDepartement(code, n);
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    @GetMapping("/{code}/villesPopulation")
    public ResponseEntity<List<Ville>> getVillesByPopulationRange(
            @PathVariable String code,
            @RequestParam int min,
            @RequestParam int max) {

        List<Ville> villes = departementService.getVillesByPopulationRange(code, min, max);
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    // fontion export CSV
    @GetMapping("/export-departements")
    public ResponseEntity<String> exportDepartementsToCSV() {
        List<Departement> departements = departementService.getAllDepartements();

        StringBuilder csvData = new StringBuilder();
        csvData.append("Code Département,Nom du Département\n");

        for (Departement departement : departements) {
            csvData.append(departement.getCode())
                    .append(",")
                    .append(departement.getNom())
                    .append("\n");
        }

        String filename = "departements.csv";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csvData.toString());
    }
}