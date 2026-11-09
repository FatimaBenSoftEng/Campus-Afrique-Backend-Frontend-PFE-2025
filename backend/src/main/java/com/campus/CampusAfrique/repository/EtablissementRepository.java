package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
    Optional<Etablissement> findByEmail(String email);
    List<Etablissement> findByNomContaining(String nom);
    List<Etablissement> findByBourseDisponible(boolean bourseDisponible);
    boolean existsByEmail(String email);
    List<Etablissement> findByType(String type);
    List<Etablissement> findByVille(String ville);
    List<Etablissement> findByPays(String pays);
    @Query("SELECT e FROM Etablissement e WHERE " +
            "LOWER(e.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.nomAbr) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.ville) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Etablissement> searchByKeyword(@Param("keyword") String keyword);

}
