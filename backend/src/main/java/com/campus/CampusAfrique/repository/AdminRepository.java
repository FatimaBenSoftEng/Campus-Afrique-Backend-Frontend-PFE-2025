package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
    boolean existsByEmail(String email);
    //List<Admin> findByRole(String role);
    //List<Admin> findByStatut(String statut);
}
