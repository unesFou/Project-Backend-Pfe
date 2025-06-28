package com.pfe.reservation.reservation.service;

import com.pfe.reservation.reservation.dto.Appartement;
import com.pfe.reservation.reservation.model.Reservation;
import com.pfe.reservation.reservation.repository.ReservationRepository;
import com.pfe.reservation.reservation.client.VehiculeClient;
import com.pfe.reservation.reservation.client.AppartementClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VehiculeClient vehiculeClient;

    @Autowired
    private AppartementClient appartementClient;

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(String id) {
        return reservationRepository.findById(id);
    }

    public Optional<Reservation> updateReservation(String id, Reservation reservationDetails) {
        return reservationRepository.findById(id).map(existing -> {
            reservationDetails.setId(id); // Important
            return reservationRepository.save(reservationDetails);
        });
    }

    public boolean deleteReservation(String id) {
        return reservationRepository.findById(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
    }

    public boolean isAvailable(String annonceId, Date dateDebut, Date dateFin, String type) {
        List<Reservation> overlapping = reservationRepository.findOverlappingReservations(annonceId, dateDebut, dateFin, type);
        return overlapping.isEmpty();
    }

    public Reservation createReservation(Reservation reservation) {
        if (!isAvailable(reservation.getAnnonceId(), reservation.getDateDebut(), reservation.getDateFin(), reservation.getType())) {
            throw new IllegalStateException("Déjà réservé pour cette période.");
        }
        reservation.setStatut("CONFIRMED");

        return reservationRepository.save(reservation);
    }

    public boolean isAnnonceValid(String annonceId, String type) {
        if ("appartement".equalsIgnoreCase(type)) {
            try {
                ResponseEntity<Appartement> appartement = appartementClient.getById(annonceId);
                return appartement != null;
            } catch (Exception e) {
                return false; // si 404 ou erreur
            }
        }
        return false;
    }

    public void updateStatus(String annonceId, boolean disponible) {
        appartementClient.updateStatus(annonceId,disponible );
    }

    public List<String> getReservedAnnonceIds() {
        List<Reservation> reservations = reservationRepository.findByDisponibleFalseAndDateFinAfter(new Date());

        return reservations.stream()
                .map(Reservation::getAnnonceId)
                .distinct()
                .collect(Collectors.toList());
    }

}
