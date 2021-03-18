package io.MUIC.BlockChain.ProjectBackEnd.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.PropertyRepository;
import io.MUIC.BlockChain.ProjectBackEnd.User.PropertyAgent;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> findAllProperties() {
        List<Property> properties = new ArrayList<Property>();
        this.propertyRepository.findAll().forEach(properties::add);
        return properties;
    }

    public Property findPropertyById(String id) {
        return this.propertyRepository.findById(id).get();
    }

    public List<Property> findPropertiesByName(String name) {
        List<Property> properties = new ArrayList<Property>();
        this.propertyRepository.findPropertiesByName(name).forEach(properties::add);
        return properties;
    }

    public List<Property> findPropertiesByDistrict(String district) {
        List<Property> properties = new ArrayList<Property>();
        this.propertyRepository.findPropertiesByDistrict(district).forEach(properties::add);
        return properties;
    }

    public List<Property> findPropertiesByProvince(String province) {
        List<Property> properties = new ArrayList<Property>();
        this.propertyRepository.findPropertiesByProvince(province).forEach(properties::add);
        return properties;
    }

    public List<Property> findPropertiesByCountry(String country) {
        List<Property> properties = new ArrayList<Property>();
        this.propertyRepository.findPropertiesByCountry(country).forEach(properties::add);
        return properties;
    }

    public List<Property> findPropertiesByPropertyAgent(PropertyAgent propertyAgent) {
        List<Property> properties = new ArrayList<Property>();
        this.propertyRepository.findPropertiesByPropertyAgent(propertyAgent).forEach(properties::add);
        return properties;
    }

    public List<Property> findPropertiesByBuildingType(String buildingType) {
        List<Property> properties = new ArrayList<Property>();
        this.propertyRepository.findPropertiesByBuildingType(buildingType).forEach(properties::add);
        return properties;
    }
}
