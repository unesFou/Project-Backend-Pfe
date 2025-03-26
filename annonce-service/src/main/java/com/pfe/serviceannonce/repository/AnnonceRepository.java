package com.pfe.serviceannonce.repository;

import com.pfe.serviceannonce.model.Annonce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnonceRepository extends MongoRepository<Annonce, String> {
}
