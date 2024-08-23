package org.example.digihello.test.repositories;

import org.example.digihello.test.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    Departement findByCode(String code);
}