package pfe.vehicle.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import pfe.vehicle.vehicle.model.Vehicle;
import pfe.vehicle.vehicle.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        // Sauvegarde du véhicule via le service
        return  vehicleService.saveVehicule(vehicle);
        //return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
