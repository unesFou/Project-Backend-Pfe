package com.pfe.reservation.reservation.service;

import com.pfe.reservation.reservation.model.Reservation;
import com.pfe.reservation.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservation(){return reservationRepository.findAll();}

    public Optional<Reservation> getReservationById(String id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation){return reservationRepository.save(reservation);}

    public Optional<Reservation> updateReservation(String id, Reservation reservationDetails) {
        return reservationRepository.findById(id).map(annonceUpdated -> {
            reservationDetails.setAnnonceId(reservationDetails.getAnnonceId());
            reservationDetails.setDateDebut(reservationDetails.getDateDebut());
            reservationDetails.setDateFin(reservationDetails.getDateFin());
            reservationDetails.setStatut(reservationDetails.getStatut());
            reservationDetails.setUtilisateurId(reservationDetails.getUtilisateurId());
            return reservationRepository.save(reservationDetails);
        });
    }

    public boolean deleteReservation(String id) {
        return reservationRepository.findById(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
    }



}
