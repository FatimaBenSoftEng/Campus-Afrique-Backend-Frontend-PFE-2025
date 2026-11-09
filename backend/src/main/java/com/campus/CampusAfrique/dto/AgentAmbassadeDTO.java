package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentAmbassadeDTO {
    private Long id;
    private String nom;
    private String paysResponsable;
    private String email;
    private String motDePasse;
}
