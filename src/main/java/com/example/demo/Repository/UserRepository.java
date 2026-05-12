package com.example.demo.Repository;

import com.example.demo.Entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, String> {

    Optional<Users> findByUsername(String username);

}
