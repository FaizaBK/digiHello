package org.example.digihello.repositories;

import org.example.digihello.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    Departement findByCode(String code);
}