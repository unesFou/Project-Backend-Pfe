package com.pfe.users.clients;

import com.pfe.users.dto.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "VEHICLE-SERVICE")
public interface VehicleClient {

    @GetMapping("/vehicles/user/{userId}")
    List<VehicleDTO> getVehiclesByUser(@PathVariable String userId);

    @PostMapping("/vehicles")
    VehicleDTO addVehicle(@RequestBody VehicleDTO vehicle);

    @DeleteMapping("/vehicles/{vehicleId}")
    void deleteVehicle(@PathVariable String vehicleId);
}


