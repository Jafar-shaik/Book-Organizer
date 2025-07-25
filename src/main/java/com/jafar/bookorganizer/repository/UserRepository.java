package com.jafar.bookorganizer.repository;

import com.jafar.bookorganizer.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String > {
    Optional<User> findByName(String name);
}
