package com.pfe.reservation.reservation.repository;

import com.pfe.reservation.reservation.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,String> {
}
