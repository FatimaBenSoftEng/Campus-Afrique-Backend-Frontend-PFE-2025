package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.NotificationDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.Notification;
import com.campus.CampusAfrique.repository.EtudiantRepository;
import com.campus.CampusAfrique.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @PostMapping("/add")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(dto.getEtudiantId());

        if (etudiantOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Notification notification = new Notification();
        notification.setDestinataire(dto.getDestinataire());
        notification.setMessage(dto.getMessage());
        notification.setDateEnvoi(LocalDate.now());
        notification.setLue(false);
        notification.setEtudiant(etudiantOpt.get());

        Notification saved = notificationRepository.save(notification);

        NotificationDTO result = new NotificationDTO(
                saved.getId(),
                saved.getDestinataire(),
                saved.getMessage(),
                saved.getDateEnvoi(),
                saved.isLue(),
                saved.getEtudiant().getId()
        );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll().stream().map(notification -> new NotificationDTO(
                notification.getId(),
                notification.getDestinataire(),
                notification.getMessage(),
                notification.getDateEnvoi(),
                notification.isLue(),
                notification.getEtudiant().getId()
        )).collect(Collectors.toList());
    }

    @GetMapping("/etudiant/{id}")
    public List<NotificationDTO> getByEtudiant(@PathVariable Long id) {
        return notificationRepository.findByEtudiantId(id).stream().map(notification -> new NotificationDTO(
                notification.getId(),
                notification.getDestinataire(),
                notification.getMessage(),
                notification.getDateEnvoi(),
                notification.isLue(),
                notification.getEtudiant().getId()
        )).collect(Collectors.toList());
    }
}
