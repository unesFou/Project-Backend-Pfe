package com.appartement.appartement.repository;

import com.appartement.appartement.model.Appartement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppartementRepository extends MongoRepository<Appartement, String> {
    List<Appartement> findByDisponibleTrue();
}

