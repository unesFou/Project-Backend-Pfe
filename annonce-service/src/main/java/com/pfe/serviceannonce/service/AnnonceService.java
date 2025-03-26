package com.pfe.serviceannonce.service;


import com.pfe.serviceannonce.model.Annonce;
import com.pfe.serviceannonce.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    public List<Annonce> getAllAnnonce(){return annonceRepository.findAll();}

    public Optional<Annonce> getAnnonceById(String id) {
        return annonceRepository.findById(id);
    }

    public Annonce createAnnonce(Annonce annonce){return annonceRepository.save(annonce);}

    public Optional<Annonce> updateAnnonce(String id, Annonce annonceDetails) {
        return annonceRepository.findById(id).map(annonceUpdated -> {
            annonceDetails.setDescription(annonceDetails.getDescription());
            annonceDetails.setPrix(annonceDetails.getPrix());
            annonceDetails.setTitre(annonceDetails.getTitre());
            annonceDetails.setDisponible(annonceDetails.isDisponible());
            annonceDetails.setType(annonceDetails.getType());
            return annonceRepository.save(annonceDetails);
        });
    }

    public boolean deleteAnnonce(String id) {
        return annonceRepository.findById(id).map(annonce -> {
            annonceRepository.delete(annonce);
            return true;
        }).orElse(false);
    }


}
