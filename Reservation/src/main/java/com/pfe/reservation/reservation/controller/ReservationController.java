package com.pfe.reservation.reservation.controller;

import com.pfe.reservation.reservation.model.Reservation;
import com.pfe.reservation.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // GET all reservations
    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.getAllReservation();
    }

    // POST create reservation with validation
    @PostMapping("/add")
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        // Vérifie si l'annonce existe dans le microservice correspondant
        if (!reservationService.isAnnonceValid(reservation.getAnnonceId(), reservation.getType())) {
            return ResponseEntity.badRequest().body("Annonce non trouvée ou type invalide.");
        }

        // Vérifie la disponibilité pour les dates données
        boolean disponible = reservationService.isAvailable(
                reservation.getAnnonceId(),
                reservation.getDateDebut(),
                reservation.getDateFin(),
                reservation.getType()
        );

        if (!disponible) {
            return ResponseEntity.status(409).body("Déjà réservé pour cette période.");
        }
        Reservation saved = reservationService.createReservation(reservation);
        reservationService.updateStatus(saved.getAnnonceId(), false);
        System.out.println(saved);
        return ResponseEntity.ok(saved);
    }

    // GET reservation by id
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable String id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT update reservation
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable String id, @RequestBody Reservation reservationDetails) {
        return reservationService.updateReservation(id, reservationDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String id) {
        if (reservationService.deleteReservation(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/reserved-ids")
    public ResponseEntity<List<String>> getReservedAnnonceIds() {
        List<String> reservedIds = reservationService.getReservedAnnonceIds();
        return ResponseEntity.ok(reservedIds);
    }

}
