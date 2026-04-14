package com.springboot.rekruteIT.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruteurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String entreprise;
}
