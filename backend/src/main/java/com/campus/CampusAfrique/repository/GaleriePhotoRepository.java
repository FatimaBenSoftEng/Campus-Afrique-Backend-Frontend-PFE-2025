package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.GaleriePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GaleriePhotoRepository extends JpaRepository<GaleriePhoto, Long> {
    List<GaleriePhoto> findByEtablissementId(Long etablissementId);
    List<GaleriePhoto> findByDescriptionContaining(String description);
    //List<GaleriePhoto> findByType(String type);
    //List<GaleriePhoto> findByStatut(String statut);
}
