package pfe.vehicle.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import pfe.vehicle.vehicle.model.Vehicle;
import pfe.vehicle.vehicle.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

//    public VehicleController(VehicleService vehicleService) {
//        this.vehicleService = vehicleService;
//    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
//        // Sauvegarde du véhicule via le service
//        return  vehicleService.saveVehicule(vehicle);
//        //return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
//    }

    @PostMapping("/user/{userId}")
    public Vehicle createVehicle(@PathVariable String userId, @RequestBody Vehicle vehicle) {
        vehicle.setUserId(userId); // Associer le véhicule à l'utilisateur
        return vehicleService.saveVehicule(vehicle);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<Vehicle> getVehiclesByUser(@PathVariable String userId) {
        return vehicleService.findByUserId(userId);
    }

    @GetMapping("/user/{userId}/details")
    public ResponseEntity<Map<String, Object>> getUserWithVehicles(@PathVariable String userId) {
        return ResponseEntity.ok(vehicleService.getUserWithVehicles(userId));
    }

}
