package com.pfe.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VEHICLE-SERVICE") // Nom tel qu'enregistré dans Eureka
public interface VehicleClient {
    @GetMapping("/api/vehicles/{id}") // Endpoint exposé par le microservice cible
    String getVehicleById(@PathVariable("id") String id);
}

