package com.pfe.reservation.reservation.dto;

import lombok.Data;

@Data
public class Appartement {

    private String id;
    private String adresse;
    private int surface;
    private double price;
    private int numbers_chambre;
    private boolean disponible;
    private String latitude;
    private String longitude;

}
