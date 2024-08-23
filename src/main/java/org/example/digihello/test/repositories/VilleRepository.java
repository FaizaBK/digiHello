package org.example.digihello.test.repositories;


import org.example.digihello.test.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Long> {

    @Query("SELECT v FROM Ville v WHERE v.codeDepartement = :codeDepartement ORDER BY v.nbHabitants DESC")
    List<Ville> findTopNByCodeDepartementOrderByNbHabitantsDesc(String codeDepartement, int pageable);


    List<Ville> findByCodeDepartementAndNbHabitantsBetween(String code, int min, int max);
}