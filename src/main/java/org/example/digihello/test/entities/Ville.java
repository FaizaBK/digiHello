package org.example.digihello.test.entities;

import jakarta.persistence.*;

@Entity
public class Ville {

    @Id
    private Long id;

    private String codeDepartement;

    private int nbHabitants;


    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    public Ville(int nbHabitants) {
    }

    public Ville() {

    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) {
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getNbHabitants() { return nbHabitants; }
    public void setNbHabitants(int nbHabitants) {
    }

    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }
}