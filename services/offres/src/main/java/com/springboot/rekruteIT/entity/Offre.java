package com.springboot.rekruteIT.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private String experience;

    @Lob
    private String connaissances;

    private String contrat;
    private String formation;

    @Lob
    private String mission;

    private int salaire;
    private int nbr_postes;
    private String specialite;
    private String region;
    private String ville;

    @Lob
    private String add_notes;

    private LocalDate datePosted;

    @Lob
    private String description;

    private String languages;

    //ID reference instead of FK
    private Long recruteurId;

    @PrePersist
    public void prePersist() {
        this.datePosted = LocalDate.now();
    }
g
}
