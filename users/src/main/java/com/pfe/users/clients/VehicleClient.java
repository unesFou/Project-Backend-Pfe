package com.pfe.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VEHICLE-SERVICE")
public interface VehicleClient {
    @GetMapping("/api/vehicles/{id}")
    String getVehicleById(@PathVariable("id") String id);
}

