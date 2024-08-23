package org.example.digihello.test.repositories;

import org.example.digihello.test.entities.Ville;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Long> {

    @Query("SELECT v FROM Ville v WHERE v.codeDepartement = :codeDepartement ORDER BY v.nbHabitants DESC")
    List<Ville> findTopNByCodeDepartement(@Param("codeDepartement") String codeDepartement, Pageable pageable);

    Ville findByNomAndCodeDepartement(String nom, String codeDepartement);

    // Si vous avez besoin de trouver des villes dans une certaine plage d'habitants, définissez la méthode suivante :
    List<Ville> findByCodeDepartementAndNbHabitantsBetween(String codeDepartement, int minHabitants, int maxHabitants);

    List<Ville> findTopNByCodeDepartementOrderByNbHabitantsDesc(String CodeDepartement, int nHabitants);
}