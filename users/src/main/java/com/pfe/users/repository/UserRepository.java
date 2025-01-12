package com.pfe.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.pfe.users.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

