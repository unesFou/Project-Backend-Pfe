package pfe.vehicle.vehicle.repository;

import pfe.vehicle.vehicle.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle , String> {
    List<Vehicle> findByUserId(String userId);
}
