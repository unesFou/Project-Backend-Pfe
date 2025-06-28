package pfe.vehicle.vehicle.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

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
    private String userId; // Owner/User reference

    // Basic vehicle information
    private String color;
    private String vehicleType; // SEDAN, SUV, TRUCK, etc.
    private String transmissionType; // AUTOMATIC, MANUAL
    private String fuelType; // GASOLINE, DIESEL, ELECTRIC, HYBRID
    private int numberOfSeats;
    private int numberOfDoors;

    // Rental-specific fields
    private double dailyRate;
    private double hourlyRate;
    private double mileage;
    private String location; // Where the vehicle is normally parked/available
    private List<String> features; // GPS, Bluetooth, Sunroof, etc.
    private List<String> imageUrls; // URLs to vehicle images

    // Availability fields
    private boolean isAvailable;
    private LocalDate nextAvailableDate;
    private List<LocalDate> bookedDates;

    // Maintenance information
    private LocalDate lastMaintenanceDate;
    private LocalDate nextMaintenanceDate;
    private String maintenanceNotes;

    // Insurance information
    private String insuranceProvider;
    private LocalDate insuranceExpiry;
    private String insurancePolicyNumber;

    // Vehicle condition
    private String condition; // NEW, EXCELLENT, GOOD, FAIR, POOR
    private String notes; // Any additional notes about the vehicle

    // Tracking information
    private LocalDate createdAt;
    private LocalDate updatedAt;
}

