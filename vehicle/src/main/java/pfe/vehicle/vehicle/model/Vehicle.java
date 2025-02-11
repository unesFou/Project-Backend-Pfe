package pfe.vehicle.vehicle.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "vehicules")
public class Vehicle {

    @Id
    private String id;
    private String licensePlate;
    private String make;
    private String model;
    private int year;
}

