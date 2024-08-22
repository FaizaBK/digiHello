package org.example.digihello.test.repositories;


import org.example.digihello.test.model.Ville;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Long> {

    // Recherche de toutes les villes dont le nom commence par une chaîne de caractères donnée
    List<Ville> findByNomStartingWith(String prefix);

    // Recherche de toutes les villes dont la population est supérieure à un minimum donné
    List<Ville> findByNbHabitantsGreaterThan(int min);

    // Recherche de toutes les villes dont la population est supérieure à min et inférieure à max
    List<Ville> findByNbHabitantsBetween(int min, int max);

    // Recherche de toutes les villes d’un département dont la population est supérieure à un minimum donné
    List<Ville> findByCodeDepartementAndNbHabitantsGreaterThan(String codeDepartement, int min);

    // Recherche de toutes les villes d’un département dont la population est supérieure à min et inférieure à max
    List<Ville> findByCodeDepartementAndNbHabitantsBetween(String codeDepartement, int min, int max);

    // Recherche des n villes les plus peuplées d’un département donné
    @Query("SELECT v FROM Ville v WHERE v.codeDepartement = :departement ORDER BY v.nbHabitants DESC")
    List<Ville> findTopNByCodeDepartementOrderByNbHabitantsDesc(@Param("departement") String departement, Pageable pageable);
}