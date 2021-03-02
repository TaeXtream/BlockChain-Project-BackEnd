package io.MUIC.BlockChain.ProjectBackEnd.Repository;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<Property, String> {

    Property findByName(String name);

}
