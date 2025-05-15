package com.appartement.appartement.controller;

import com.appartement.appartement.model.Appartement;
import com.appartement.appartement.service.AppartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appartements")
public class AppartementController {

    @Autowired
    private AppartementService appartementService;

    private final String uploadDir = "uploads/";

    @GetMapping("/disponibles")
    public List<Appartement> getAppartementsDisponibles() {
        return appartementService.getAppartementsDisponibles();
    }

    @GetMapping("/all")
    public List<Appartement> getAllAppartements() {
        return appartementService.getAll();
    }

//    @PostMapping
//    public void createAppartement(@RequestBody Appartement appartement) {
//        appartementService.createApartment(appartement);
//    }
    @PostMapping(
            value = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Appartement> createAppartement(
            @RequestParam("adresse") String adresse,
            @RequestParam("surface") int surface,
            @RequestParam("Numbers_chambre") int Numbers_chambre,
            @RequestParam("price") double price,
            @RequestParam("disponible") boolean disponible,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("photos") List<MultipartFile> photos
    ) {
        List<byte[]> photoDataList = new ArrayList<>();

        try {
            for (MultipartFile photo : photos) {
                photoDataList.add(photo.getBytes());
            }

            Appartement app = new Appartement();
            app.setAdresse(adresse);
            app.setSurface(surface);
            app.setNumbers_chambre(Numbers_chambre);
            app.setPrice(price);
            app.setDisponible(disponible);
            app.setLatitude(latitude);
            app.setLongitude(longitude);
            app.setPhotos(photoDataList); // stocker les fichiers dans MongoDB

            Appartement saved = appartementService.createAppartement(app);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/photo/{index}")
    public ResponseEntity<?> getPhoto(@PathVariable String id, @PathVariable int index) {
        return (ResponseEntity<byte[]>) appartementService.getAppartementById(id)
                .map(appartement -> {
                    List<byte[]> photos = appartement.getPhotos();
                    if (photos != null && index < photos.size()) {
                        byte[] image = photos.get(index);
                        return ResponseEntity.ok()
                                .contentType(MediaType.IMAGE_JPEG)
                                .body(image);
                    }
                    return ResponseEntity.notFound().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Appartement> getById(@PathVariable String id) {
        return appartementService.getAppartementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appartement> update(@PathVariable String id, @RequestBody Appartement updatedAppartement) {
        Appartement updated = appartementService.updateAppartement(id, updatedAppartement);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        appartementService.deleteAppartement(id);
        return ResponseEntity.noContent().build();
    }
}
