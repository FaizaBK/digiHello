package org.example.digihello.dto;

public class DepartementDto {
    private String code;
    private String nom;

    // Constructeur
    public DepartementDto(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}