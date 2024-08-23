package org.example.digihello.test.service;

import org.example.digihello.test.dto.VilleDto;
import org.example.digihello.test.entities.Ville;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VilleService {

    private EntityManagerFactory emf;

    public VilleService() {
        this.emf = Persistence.createEntityManagerFactory("hellodigi-unit");
    }

    public Ville createVille(VilleDto villeDto) {
        EntityManager em = emf.createEntityManager();
        Ville ville = new Ville();
        ville.setNom(villeDto.getNom());

        em.getTransaction().begin();
        em.persist(ville);
        em.getTransaction().commit();
        em.close();

        return ville;
    }

        public Collection<Ville> getAllVilles() {
            EntityManager em = emf.createEntityManager();
            try {
                return em.createQuery("SELECT v FROM Ville v", Ville.class).getResultList();
            } finally {
                em.close();
            }
        }
    public void insertVille(Ville ville) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ville);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}