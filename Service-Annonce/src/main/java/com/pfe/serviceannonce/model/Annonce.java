package com.pfe.serviceannonce.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "annonce")
public class Annonce {
    @Id
    private String id;
    private String titre;
    private String description;
    private double prix;
    private String type; // voiture ou appartement
    private boolean disponible;
}
