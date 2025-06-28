package pfe.vehicle.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import pfe.vehicle.vehicle.client.UserClient;
//import pfe.vehicle.vehicle.model.User;
import pfe.vehicle.vehicle.model.Vehicle;
import pfe.vehicle.vehicle.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

//    @Autowired
//    private UserClient userClient;

    // Basic CRUD operations
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(String id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        // Set creation/update timestamps
        if(vehicle.getId() == null) {
            vehicle.setCreatedAt(LocalDate.now());
        }
        vehicle.setUpdatedAt(LocalDate.now());

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(String id) {
        vehicleRepository.deleteById(id);
    }

    // User-related operations
    public List<Vehicle> findByUserId(String userId) {
        return vehicleRepository.findByUserId(userId);
    }

//    public Map<String, Object> getUserWithVehicles(String userId) {
//        User user = userClient.getUserById(userId);
//        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("user", user);
//        response.put("vehicles", vehicles);
//        return response;
//    }

    // Search and filter operations
    public List<Vehicle> searchVehicles(String make, String model, Integer minYear, Integer maxYear) {
        if(make != null && model != null) {
            return vehicleRepository.findByMakeAndModel(make, model);
        } else if(make != null) {
            return vehicleRepository.findByMake(make);
        } else if(model != null) {
            return vehicleRepository.findByModel(model);
        } else if(minYear != null && maxYear != null) {
            return vehicleRepository.findByYearBetween(minYear, maxYear);
        }
        return vehicleRepository.findAll();
    }

    //update Vehicule
    public Vehicle updateVehicule(String id, Vehicle updatedVehicule) {
        return vehicleRepository.findById(id).map(vehicule -> {
        vehicule.setAvailable(updatedVehicule.isAvailable());
        vehicule.setCreatedAt(updatedVehicule.getCreatedAt());
        vehicule.setBookedDates(updatedVehicule.getBookedDates());
        vehicule.setColor(updatedVehicule.getColor());
        vehicule.setCondition(updatedVehicule.getCondition());
        vehicule.setDailyRate(updatedVehicule.getDailyRate());
        vehicule.setFuelType(updatedVehicule.getFuelType());
        vehicule.setHourlyRate(updatedVehicule.getHourlyRate());

            return vehicleRepository.save(vehicule);
        }).orElse(null);
    }


    public List<Vehicle> findAvailableVehicles(LocalDate startDate, LocalDate endDate) {
        if(startDate != null && endDate != null) {
            return vehicleRepository.findAvailableVehiclesBetweenDates(startDate, endDate);
        }
        return vehicleRepository.findByIsAvailableTrue();
    }

    public List<Vehicle> findVehiclesByFeatures(List<String> features) {
        return vehicleRepository.findByFeatures(features);
    }

    // Paginated results
    public Page<Vehicle> getVehiclesByMakePaginated(String make, Pageable pageable) {
        return vehicleRepository.findByMake(make, pageable);
    }

    public Page<Vehicle> getAvailableVehiclesPaginated(Pageable pageable) {
        return vehicleRepository.findByIsAvailableTrue(pageable);
    }

    // Price range queries
    public List<Vehicle> findVehiclesByPriceRange(double maxPrice) {
        return vehicleRepository.findByDailyRateLessThanEqual(maxPrice);
    }

    // Vehicle type and features
//    public List<Vehicle> findVehiclesByTypeAndSeats(String vehicleType, int minSeats) {
//        return vehicleRepository.findByVehicleTypeAndNumberOfSeatsGreaterThanEqual(vehicleType, minSeats);
//    }

    // Maintenance alerts
//    public List<Vehicle> findVehiclesNeedingMaintenance() {
//        return vehicleRepository.findByNextMaintenanceDateBefore(LocalDate.now().plusWeeks(1));
//    }

    // Insurance alerts
//    public List<Vehicle> findVehiclesWithExpiringInsurance() {
//        return vehicleRepository.findByInsuranceExpiryBefore(LocalDate.now().plusMonths(1));
//    }

    // Text search
//    public List<Vehicle> searchVehiclesByText(String searchText) {
//        return vehicleRepository.searchByText(searchText, Sort.by(Sort.Direction.DESC, "score"));
//    }

    // Toggle availability
//    public Vehicle toggleVehicleAvailability(String id, boolean isAvailable) {
//        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
//        if(vehicleOpt.isPresent()) {
//            Vehicle vehicle = vehicleOpt.get();
//            vehicle.setIsAvailable(isAvailable);
//            return vehicleRepository.save(vehicle);
//        }
//        return null;
//    }

    // Book vehicle (add to booked dates)
    public Vehicle bookVehicle(String id, LocalDate startDate, LocalDate endDate) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
        if(vehicleOpt.isPresent()) {
            Vehicle vehicle = vehicleOpt.get();
            // Add logic to check availability and add dates
            // This is simplified - you'd want more robust booking logic
            vehicle.getBookedDates().addAll(getDatesBetween(startDate, endDate));
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    // Helper method for date range
    private List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        // Implement date range generation
        return startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
    }
}