package org.example.digihello.test.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.digihello.villes.Ville;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VilleService {

    @PersistenceContext
    private EntityManager entityManager;

    // Méthode pour extraire et retourner toutes les villes
    public List<Ville> extractVilles() {
        TypedQuery<Ville> query = entityManager.createQuery("SELECT v FROM Ville v", Ville.class);
        return query.getResultList();
    }

    // Méthode pour extraire une ville par ID
    public Ville extractVille(int idVille) {
        return entityManager.find(Ville.class, idVille);
    }

    // Méthode pour extraire une ville par nom
    public Ville extractVille(String nom) {
        TypedQuery<Ville> query = entityManager.createQuery("SELECT v FROM Ville v WHERE v.nom = :nom", Ville.class);
        query.setParameter("nom", nom);
        return query.getSingleResult();
    }

    // Méthode pour insérer une nouvelle ville et retourner la liste des villes après insertion
    @Transactional
    public List<Ville> insertVille(Ville ville) {
        entityManager.persist(ville);
        return extractVilles();
    }

    // Méthode pour modifier une ville et retourner la liste des villes après modification
    @Transactional
    public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
        Ville ville = entityManager.find(Ville.class, idVille);
        if (ville != null) {
            ville.setNom(villeModifiee.getNom());
            ville.setNbHabitants(villeModifiee.getNbHabitants());
            entityManager.merge(ville);
        }
        return extractVilles();
    }

    // Méthode pour supprimer une ville et retourner la liste des villes après suppression
    @Transactional
    public List<Ville> supprimerVille(int idVille) {
        Ville ville = entityManager.find(Ville.class, idVille);
        if (ville != null) {
            entityManager.remove(ville);
        }
        return extractVilles();
    }
}