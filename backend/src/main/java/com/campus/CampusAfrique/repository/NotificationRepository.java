package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByEtudiantId(Long etudiantId);
    List<Notification> findByLue(boolean lue);
    //List<Notification> findByDateEnvoiBetween(Date dateDebut, Date dateFin);
    //List<Notification> findByDestinataireId(Long destinataireId);
    //List<Notification> findByType(String type);
    //List<Notification> findByStatut(String statut);
}
