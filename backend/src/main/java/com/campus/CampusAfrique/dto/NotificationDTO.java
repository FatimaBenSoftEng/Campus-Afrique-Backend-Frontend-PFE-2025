package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private Long id;
    private String destinataire;
    private String message;
    private LocalDate dateEnvoi;
    private boolean lue;
    private Long etudiantId;
}

