package com.appartement.appartement.controller;

import com.appartement.appartement.model.Appartement;
import com.appartement.appartement.service.AppartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appartements")
public class AppartementController {

    @Autowired
    private AppartementService appartementService;

    @GetMapping("/disponibles")
    public List<Appartement> getAppartementsDisponibles() {
        return appartementService.getAppartementsDisponibles();
    }

    @GetMapping("/all")
    public List<Appartement> getAllAppartements() {
        return appartementService.getAll();
    }
    @PostMapping
    public void createAppartement(@RequestBody Appartement appartement) {
        appartementService.createApartment(appartement);
    }
}
