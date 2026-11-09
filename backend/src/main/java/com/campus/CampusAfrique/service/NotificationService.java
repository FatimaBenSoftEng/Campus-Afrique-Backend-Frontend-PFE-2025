package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Notification;
import com.campus.CampusAfrique.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    // Nouvelle méthode pour trouver les notifications d'un étudiant
    public List<Notification> findByEtudiantId(Long etudiantId) {
        return notificationRepository.findByEtudiantId(etudiantId);
    }

    // Nouvelle méthode pour marquer une notification comme lue
    public Notification markAsRead(Long id) {
        Optional<Notification> notificationOpt = notificationRepository.findById(id);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setLue(true);
            return notificationRepository.save(notification);
        }
        throw new RuntimeException("Notification non trouvée avec l'id: " + id);
    }

    // Nouvelle méthode pour marquer toutes les notifications d'un étudiant comme lues
    public void markAllAsReadByEtudiantId(Long etudiantId) {
        List<Notification> notifications = notificationRepository.findByEtudiantId(etudiantId);
        for (Notification notification : notifications) {
            notification.setLue(true);
        }
        notificationRepository.saveAll(notifications);
    }

  /*  public List<Notification> findByDestinataireId(Long destinataireId) {
        return notificationRepository.findByDestinataireId(destinataireId);
    }

    public List<Notification> findByType(String type) {
        return notificationRepository.findByType(type);
    }

    public List<Notification> findByStatut(String statut) {
        return notificationRepository.findByStatut(statut);
    }

    public List<Notification> findByDateEnvoiBetween(Date dateDebut, Date dateFin) {
        return notificationRepository.findByDateEnvoiBetween(dateDebut, dateFin);
    }*/

    public List<Notification> findByLu(boolean lu) {
        return notificationRepository.findByLue(lu);
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    public Notification update(Long id, Notification notification) {
        if (notificationRepository.existsById(id)) {
            notification.setId(id);
            return notificationRepository.save(notification);
        }
        throw new RuntimeException("Notification non trouvée avec l'id: " + id);
    }
}