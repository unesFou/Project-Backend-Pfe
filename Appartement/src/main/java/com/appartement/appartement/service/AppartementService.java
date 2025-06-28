package com.appartement.appartement.service;

import com.appartement.appartement.model.Appartement;
import com.appartement.appartement.repository.AppartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Appartement> getAppartementById(String id) {
        return appartementRepository.findById(id);
    }

    public Appartement createAppartement(Appartement appartement) {
        return appartementRepository.save(appartement);
    }

    public Appartement updateAppartement(String id, Appartement updatedAppartement) {
        return appartementRepository.findById(id).map(appartement -> {
            appartement.setAdresse(updatedAppartement.getAdresse());
            appartement.setSurface(updatedAppartement.getSurface());
            appartement.setNumbers_chambre(updatedAppartement.getNumbers_chambre());
            appartement.setPrice(updatedAppartement.getPrice());
            appartement.setDisponible(updatedAppartement.isDisponible());
            appartement.setLatitude(updatedAppartement.getLatitude());
            appartement.setLongitude(updatedAppartement.getLongitude());
            appartement.setPhotos(updatedAppartement.getPhotos());
            return appartementRepository.save(appartement);
        }).orElse(null);
    }

    public void deleteAppartement(String id) {
        appartementRepository.deleteById(id);
    }

    public Optional<Appartement> findById(String id) {
        return appartementRepository.findById(id);
    }

    public Appartement updateAppartementDisponiblity(String id, Boolean disponible) {
        return appartementRepository.findById(id).map(app -> {
            app.setDisponible(disponible);
            return appartementRepository.save(app);
        }).orElse(null);
    }

}
