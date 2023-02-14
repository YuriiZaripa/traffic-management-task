package com.mps.trafficmanagement.persistence;

import com.mps.trafficmanagement.model.Airplane;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends MongoRepository<Airplane, String> {
}
