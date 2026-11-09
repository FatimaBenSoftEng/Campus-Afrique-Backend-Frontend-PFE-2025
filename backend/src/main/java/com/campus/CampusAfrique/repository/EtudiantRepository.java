package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Etudiant findByEmail(String email);
    boolean existsByEmail(String email);
    List<Etudiant> findByPaysOrigine(String paysOrigine);
    List<Etudiant> findByNiveauScolaireId(Long niveauScolaireId);
    //List<Etudiant> findByDomaine(String domaine);
    List<Etudiant> findByStatut(String statut);
    List<Etudiant> findByVille(String ville);
    // ✅ AJOUTER CETTE MÉTHODE
    Optional<Etudiant> findByUser(User user);
    Optional<Etudiant> findByUserEmail(String email);

}

