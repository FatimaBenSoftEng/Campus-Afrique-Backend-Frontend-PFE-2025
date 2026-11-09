package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Admin;
import com.campus.CampusAfrique.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    /*public List<Admin> findByRole(String role) {
        return adminRepository.findByRole(role);
    }*/

    /*public List<Admin> findByStatut(String statut) {
        return adminRepository.findByStatut(statut);
    }*/

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    public Admin update(Long id, Admin admin) {
        if (adminRepository.existsById(id)) {
            admin.setId(id);
            return adminRepository.save(admin);
        }
        throw new RuntimeException("Administrateur non trouvé avec l'id: " + id);
    }
}
