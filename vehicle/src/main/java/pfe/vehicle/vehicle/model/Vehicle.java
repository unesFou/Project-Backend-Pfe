package pfe.vehicle.vehicle.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicules")
@Data
public class Vehicle {

    @Id
    private Long id;


    private String licensePlate;

    private String make;

    private String model;

    private int year;
}