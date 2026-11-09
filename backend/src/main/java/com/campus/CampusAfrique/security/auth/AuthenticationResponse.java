package com.campus.CampusAfrique.security.auth;

import com.campus.CampusAfrique.dto.EtudiantDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private EtudiantDTO etudiant;
}