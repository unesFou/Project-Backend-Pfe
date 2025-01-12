package pfe.vehicle.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import pfe.vehicle.vehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

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
}
