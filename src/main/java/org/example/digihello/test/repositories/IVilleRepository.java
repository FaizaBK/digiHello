package org.example.digihello.test.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.example.digihello.test.entities.Ville;

import java.util.List;

public interface IVilleRepository extends JpaRepository<Ville, Long> {

        // Requête pour obtenir les villes par code département et avec nbHabitants entre min et max
        @Query("SELECT v FROM Ville v WHERE v.codeDepartement = :codeDepartement AND v.nbHabitants BETWEEN :min AND :max")
        List<Ville> findByCodeDepartementAndNbHabitantsBetween(@Param("codeDepartement") String codeDepartement, @Param("min") int min, @Param("max") int max);

        // Requête pour obtenir les "top N" villes d'un département en fonction du nombre d'habitants
        @Query("SELECT v FROM Ville v WHERE v.codeDepartement = :codeDepartement ORDER BY v.nbHabitants DESC")
        List<Ville> findTopNByCodeDepartementOrderByNbHabitantsDesc(@Param("codeDepartement") String codeDepartement, Pageable pageable);
    }

