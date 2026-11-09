package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.DashboardStatsDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.service.DashboardService;
import com.campus.CampusAfrique.service.EtudiantService;
import com.campus.CampusAfrique.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired private DashboardService dashboardService;
    @Autowired private EtudiantService etudiantService;
    @Autowired private JwtService jwtService;

    @GetMapping
    public ResponseEntity<DashboardStatsDTO> getDashboardStats(@RequestHeader("Authorization") String token) {
        String email = jwtService.extractUsername(token.replace("Bearer ", ""));
        Etudiant etudiant = etudiantService.getEtudiantByEmail(email);
        return ResponseEntity.ok(dashboardService.getDashboardStats(etudiant));
    }
}

