package org.example.digihello.test.entities;

import jakarta.persistence.*;

@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisation d'une génération automatique d'identifiant
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String codeDepartement;

    @Column(nullable = false)
    private int nbHabitants;

    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    // Constructeurs
    public Ville() {
    }

    public Ville(String nom, String codeDepartement, int nbHabitants, Departement departement) {
        this.nom = nom;
        this.codeDepartement = codeDepartement;
        this.nbHabitants = nbHabitants;
        this.departement = departement;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCodeDepartement() { return codeDepartement; }
    public void setCodeDepartement(String codeDepartement) { this.codeDepartement = codeDepartement; }

    public int getNbHabitants() { return nbHabitants; }
    public void setNbHabitants(int nbHabitants) { this.nbHabitants = nbHabitants; }

    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }
}