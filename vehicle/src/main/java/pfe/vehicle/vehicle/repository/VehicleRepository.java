package pfe.vehicle.vehicle.repository;

import pfe.vehicle.vehicle.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    // Basic queries
    List<Vehicle> findByUserId(String userId);
    List<Vehicle> findByMake(String make);
    List<Vehicle> findByModel(String model);
    List<Vehicle> findByMakeAndModel(String make, String model);
    List<Vehicle> findByYearBetween(int startYear, int endYear);

    // Rental queries
    List<Vehicle> findByDailyRateLessThanEqual(double maxPrice);
    List<Vehicle> findByIsAvailableTrue();
    List<Vehicle> findByLocationAndIsAvailableTrue(String location);

    // Feature queries
    List<Vehicle> findByFuelType(String fuelType);
    List<Vehicle> findByTransmissionType(String transmissionType);
    List<Vehicle> findByVehicleType(String vehicleType);
    List<Vehicle> findByNumberOfSeatsGreaterThanEqual(int minSeats);

    // Availability queries
    @Query("{ 'bookedDates': { $not: { $elemMatch: { $eq: ?0 } }, 'isAvailable': true }")
    List<Vehicle> findAvailableVehiclesOnDate(LocalDate date);

    @Query("{ $and: [ "
            + "{ 'bookedDates': { $not: { $elemMatch: { $gte: ?0, $lte: ?1 } } }, "
            + "{ 'isAvailable': true } "
            + "] }")
    List<Vehicle> findAvailableVehiclesBetweenDates(LocalDate startDate, LocalDate endDate);

    // Text search
    @Query("{ $text: { $search: ?0 } }")
    List<Vehicle> searchByText(String text, Sort sort);

    // Complex queries
    @Query("{ 'make': ?0, 'dailyRate': { $lte: ?1 }, 'numberOfSeats': { $gte: ?2 } }")
    List<Vehicle> findByMakePriceAndSeats(String make, double maxPrice, int minSeats);

    @Query("{ 'features': { $all: ?0 } }")
    List<Vehicle> findByFeatures(List<String> features);

    // Pagination
    Page<Vehicle> findByMake(String make, Pageable pageable);
    Page<Vehicle> findByIsAvailableTrue(Pageable pageable);
}