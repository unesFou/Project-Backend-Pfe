package com.pfe.users.service;

import com.pfe.users.model.User;
import com.pfe.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pfe.users.clients.VehicleClient;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

   // public String getVehicleInfo(String vehicleId) {
//        return vehicleClient.getVehicleById(vehicleId);
//    }
}

