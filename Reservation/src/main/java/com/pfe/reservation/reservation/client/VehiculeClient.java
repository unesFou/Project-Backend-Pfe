package com.pfe.reservation.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VEHICLE-SERVICE")
public interface VehiculeClient {
    @GetMapping("/vehicles/{id}/")
    boolean getVehicleById(@PathVariable("id") String id);
}
