package org.example.digihello.test.config;

import org.example.digihello.test.entities.Departement;
import org.example.digihello.test.service.DepartementService;
import org.example.digihello.test.entities.Ville;
import org.example.digihello.test.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private VilleService villeService;

    @Autowired
    private DepartementService departementService;  // Assurez-vous d'avoir un service pour les départements

    @Override
    public void run(String... args) throws Exception {
        // Vérifiez si la base de données est vide avant d'ajouter des données
        if (villeService.getAllVilles().isEmpty()) {
            // Créez ou récupérez les départements
            Departement departement31 = departementService.findOrCreateDepartement("31", "Haute-Garonne");
            Departement departement34 = departementService.findOrCreateDepartement("34", "Hérault");
            Departement departement33 = departementService.findOrCreateDepartement("33", "Gironde");
            Departement departement11 = departementService.findOrCreateDepartement("11", "Aude");

            // Initialisation des données
            Ville ville1 = new Ville();
            ville1.setCodeDepartement("31");
            ville1.setNom("Toulouse");
            ville1.setNbHabitants(479553);
            ville1.setDepartement(departement31);

            Ville ville2 = new Ville();
            ville2.setCodeDepartement("34");
            ville2.setNom("Montpellier");
            ville2.setNbHabitants(306317);
            ville2.setDepartement(departement34);

            Ville ville3 = new Ville();
            ville3.setCodeDepartement("33");
            ville3.setNom("Bordeaux");
            ville3.setNbHabitants(254436);
            ville3.setDepartement(departement33);

            Ville ville4 = new Ville();
            ville4.setCodeDepartement("11");
            ville4.setNom("Carcassonne");
            ville4.setNbHabitants(47934);
            ville4.setDepartement(departement11);

            // Insérez les villes dans la base de données
            villeService.insertVille(ville1);
            villeService.insertVille(ville2);
            villeService.insertVille(ville3);
            villeService.insertVille(ville4);

            System.out.println("Données initiales insérées dans la base de données.");
        }
    }
}