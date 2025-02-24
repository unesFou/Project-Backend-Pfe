package pfe.vehicle.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.vehicle.vehicle.client.UserClient;
import pfe.vehicle.vehicle.model.User;
import pfe.vehicle.vehicle.model.Vehicle;
import pfe.vehicle.vehicle.repository.VehicleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    private UserClient userClient;

    //public VehicleService(VehicleRepository vehicleRepository) {
    //    this.vehicleRepository = vehicleRepository;
    //}

    // Récupérer tous les véhicules
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Récupérer un véhicule par ID
    public Optional<Vehicle> getVehicleById(String id) {
        return vehicleRepository.findById(id.toString());
    }

    // Sauvegarder un véhicule
    public Vehicle saveVehicule(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Supprimer un véhicule
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id.toString());
    }

    public List<Vehicle> findByUserId(String userId) {return vehicleRepository.findByUserId(userId); }

    public Map<String, Object> getUserWithVehicles(String userId) {
        User user = userClient.getUserById(userId);  // Récupérer l'utilisateur via user-service
        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);  // Récupérer ses véhicules

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("vehicles", vehicles);

        return response;
    }
}
