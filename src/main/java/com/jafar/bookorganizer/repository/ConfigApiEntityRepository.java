package com.jafar.bookorganizer.repository;

import com.jafar.bookorganizer.entity.ConfigApiEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigApiEntityRepository extends MongoRepository<ConfigApiEntity, String > {
}
