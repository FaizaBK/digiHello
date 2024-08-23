package org.example.digihello.test.service;


import org.example.digihello.test.entities.Departement;
import org.example.digihello.test.entities.Ville;

import java.util.List;


public interface DepartementService {
    Departement createDepartement(Departement departement);
    Departement getDepartementByCode(String code);
    Departement updateDepartement(String code, Departement departement);
    boolean deleteDepartement(String code);
    List<Ville> getTopNByDepartement(String code, int n);
    List<Ville> getVillesByPopulationRange(String code, int min, int max);
}