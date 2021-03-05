package io.MUIC.BlockChain.ProjectBackEnd.Repository;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import io.MUIC.BlockChain.ProjectBackEnd.User.PropertyAgent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {

    Property findByName(String name);

    List<Property> findPropertiesByDistrict(String district);

    List<Property> findPropertiesByProvince(String province);

    List<Property> findPropertiesByCountry(String country);

    List<Property> findPropertiesByDistrictAndProvince(String district, String province);

    List<Property> findPropertiesByPropertyAgent(PropertyAgent propertyAgent);

}
