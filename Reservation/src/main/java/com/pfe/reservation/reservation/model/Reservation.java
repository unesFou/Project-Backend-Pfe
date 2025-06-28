package com.pfe.reservation.reservation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "reservation")
public class Reservation {

    @Id
    private String id;
    private Date dateDebut;
    private Date dateFin;
    private String utilisateurId;

    private String type; // "vehicule" ou "appartement"
    private String annonceId; // L'id du véhicule ou de l'appartement
    private String statut;
    private Boolean disponible;
}
