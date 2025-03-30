package com.pfe.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private String id;
    private String userId;
    private String licensePlate;
    private String make;
    private String model;
    private String year;
}
