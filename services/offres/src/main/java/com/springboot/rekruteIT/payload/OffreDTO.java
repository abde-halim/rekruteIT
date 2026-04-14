package com.springboot.rekruteIT.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OffreDTO {
    private int id;
    private String titre;
    private String experience;
    private String connaissances;
    private String contrat;
    private String formation;
    private String mission;
    private int salaire;
    private int nbr_postes;
    private String specialite;
    private String region;
    private String ville;
    private String add_notes;
    private LocalDate datePosted;
    private String description;
    private String languages;

    private Long recruteurId;
}
