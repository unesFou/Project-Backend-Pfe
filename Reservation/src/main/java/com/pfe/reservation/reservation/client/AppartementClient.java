package com.pfe.reservation.reservation.client;

import com.pfe.reservation.reservation.dto.Appartement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "APPARTEMENT-SERVICE")
public interface AppartementClient {
    @GetMapping("/appartements/{id}")
    ResponseEntity<Appartement> getById(@PathVariable("id") String id);

    @PutMapping("/update-disponible/{id}")
    ResponseEntity<Appartement> updateStatus(@PathVariable("id") String id, @RequestParam("disponible") Boolean disponible);
}



