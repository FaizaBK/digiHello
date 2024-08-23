package org.example.digihello.test.config;

import org.example.digihello.test.entities.Ville;
import org.example.digihello.test.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private VilleService villeService;

    @Override
    public void run(String... args) throws Exception {
        // Vérifiez si la base de données est vide avant d'ajouter des données
        if (villeService.getAllVilles().isEmpty()) {
            // Initialisation des données
            Ville ville1 = new Ville();
            ville1.setNom("Toulouse");
            ville1.setNbHabitants(479553);
            ville1.setCodeDepartement("31");

            Ville ville2 = new Ville();
            ville2.setNom("Montpellier");
            ville2.setNbHabitants(306317);
            ville2.setCodeDepartement("34");

            Ville ville3 = new Ville();
            ville3.setNom("Bordeaux");
            ville3.setNbHabitants(254436);
            ville3.setCodeDepartement("33");


            Ville ville4 = new Ville();
            ville4.setNom("Carcassonne");
            ville4.setNbHabitants(47934);
            ville4.setCodeDepartement("11");

            // Insérez les villes dans la base de données
            villeService.insertVille(ville1);
            villeService.insertVille(ville2);
            villeService.insertVille(ville3);
            villeService.insertVille(ville4);

            System.out.println("Données initiales insérées dans la base de données.");
        }
    }
}