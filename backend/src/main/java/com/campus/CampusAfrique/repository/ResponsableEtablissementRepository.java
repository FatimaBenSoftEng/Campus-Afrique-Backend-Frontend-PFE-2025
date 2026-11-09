package com.campus.CampusAfrique.repository;


import com.campus.CampusAfrique.model.ResponsableEtablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsableEtablissementRepository extends JpaRepository<ResponsableEtablissement, Long> {
    Optional<ResponsableEtablissement> findByEmail(String email);
    Optional<ResponsableEtablissement> findByNom(String nom);
    boolean existsByEmail(String email);
    List<ResponsableEtablissement> findByEtablissementId(Long etablissementId);
}
