package com.appartement.appartement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
    
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "appartements")
public class Appartement {
    @Id
    private String id;
    private String adresse;
    private int surface;
    private int Numbers_chambre;
    private boolean disponible;
    // Getters et setters
}
