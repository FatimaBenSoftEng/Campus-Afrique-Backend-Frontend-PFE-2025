package com.campus.CampusAfrique.controller;


import com.campus.CampusAfrique.model.Admin;
import com.campus.CampusAfrique.service.AdminService;
import com.campus.CampusAfrique.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.save(admin));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return adminService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
        return adminService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*@GetMapping("/role/{role}")
    public ResponseEntity<List<Admin>> getAdminsByRole(@PathVariable String role) {
        return ResponseEntity.ok(adminService.findByRole(role));
    }*/

    /*@GetMapping("/statut/{statut}")
    public ResponseEntity<List<Admin>> getAdminsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(adminService.findByStatut(statut));
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        try {
            return ResponseEntity.ok(adminService.update(id, admin));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
