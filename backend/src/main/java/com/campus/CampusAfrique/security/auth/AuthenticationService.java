package com.campus.CampusAfrique.security.auth;

import com.campus.CampusAfrique.dto.EtudiantDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.security.JwtService;
import com.campus.CampusAfrique.security.user.Role;
import com.campus.CampusAfrique.security.user.User;
import com.campus.CampusAfrique.security.user.UserRepository;
import com.campus.CampusAfrique.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EtudiantRepository etudiantRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .build();
        userRepository.save(user);

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(request.getNom());
        etudiant.setPrenom(request.getPrenom());
        etudiant.setEmail(request.getEmail());
        etudiant.setMotDePasse(user.getPassword());
        etudiant.setStatut("Actif");
        etudiant.setUser(user);
        etudiantRepository.save(etudiant);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .etudiant(mapToDTO(etudiant))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        Etudiant etudiant = etudiantRepository.findByEmail(request.getEmail());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .etudiant(mapToDTO(etudiant))
                .build();
    }

    private EtudiantDTO mapToDTO(Etudiant etudiant) {
        EtudiantDTO dto = new EtudiantDTO();
        dto.setId(etudiant.getId());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setEmail(etudiant.getEmail());
        dto.setStatut(etudiant.getStatut());
        dto.setPhotoProfil(etudiant.getPhotoProfil());
        dto.setTelephone(etudiant.getTelephone());
        return dto;
    }
}
