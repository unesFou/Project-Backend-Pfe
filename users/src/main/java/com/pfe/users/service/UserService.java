package com.pfe.users.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    VehicleClient vehicleClient; // Injection du Feign Client

//    @Autowired
//    private VehicleClient vehicleClient;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(String id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setAge(userDetails.getAge());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(String id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

//    public UserService(UserRepository userRepository, VehicleClient vehicleClient) {
//        this.userRepository = userRepository;
//        this.vehicleClient = vehicleClient;
//    }
//
//    public List<VehicleDTO> getUserVehicles(String userId) {
//        return vehicleClient.getVehiclesByUser(userId);
//    }
//
//    public VehicleDTO addVehicleForUser(String userId, VehicleDTO vehicleDTO) {
//        vehicleDTO.setUserId(userId);
//        return vehicleClient.addVehicle(vehicleDTO);
//    }

//    public void deleteVehicle(String vehicleId) {
//        vehicleClient.deleteVehicle(vehicleId);
//    }

}

