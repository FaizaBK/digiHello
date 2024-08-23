package org.example.digihello.test.service;

import org.example.digihello.test.entities.Departement;
import org.example.digihello.test.entities.Ville;
import org.example.digihello.test.repositories.DepartementRepository;

import org.example.digihello.test.repositories.IVilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementServiceImpl extends DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private IVilleRepository villeRepository;

    @Override
    public Departement createDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public Departement getDepartementByCode(String code) {
        return departementRepository.findById(Long.valueOf(code)).orElse(null);
    }

    @Override
    public Departement updateDepartement(String code, Departement departement) {
        if (departementRepository.existsById(Long.valueOf(code))) {
            departement.setCode(code);
            return departementRepository.save(departement);
        }
        return null;
    }

    @Override
    public boolean deleteDepartement(String code) {
        if (departementRepository.existsById(Long.valueOf(code))) {
            departementRepository.deleteById(Long.valueOf(code));
            return true;
        }
        return false;
    }

    @Override
    public List<Ville> getTopNByDepartement(String code, int n) {
        return villeRepository.findTopNByCodeDepartementOrderByNbHabitantsDesc(code, Pageable.ofSize(n));
    }

    @Override
    public List<Ville> getVillesByPopulationRange(String code, int min, int max) {
        return villeRepository.findByCodeDepartementAndNbHabitantsBetween(code, min, max);
    }
}
