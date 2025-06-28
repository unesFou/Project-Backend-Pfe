package com.pfe.reservation.reservation.repository;

import com.pfe.reservation.reservation.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    @Query("{ 'annonceId': ?0, 'dateDebut': { $lt: ?2 }, 'dateFin': { $gt: ?1 }, 'type': ?3 }")
    List<Reservation> findOverlappingReservations(String annonceId, Date dateDebut, Date dateFin, String type);

    List<Reservation> findByDisponibleFalseAndDateFinAfter(Date currentDate);
}

