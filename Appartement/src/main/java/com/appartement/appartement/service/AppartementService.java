package com.appartement.appartement.service;

import com.appartement.appartement.model.Appartement;
import com.appartement.appartement.repository.AppartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppartementService {

    @Autowired
    private AppartementRepository appartementRepository;

    public void createApartment(Appartement appartement){
        appartementRepository.save(appartement);
    }

    public List<Appartement> getAll(){
      return appartementRepository.findAll();
    }

    public List<Appartement> getAppartementsDisponibles() {
        return appartementRepository.findByDisponibleTrue();
    }
}
