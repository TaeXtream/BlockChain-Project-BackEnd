package io.MUIC.BlockChain.ProjectBackEnd.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.PropertyRepository;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> findAllProperties() {
        List<Property> properties = new ArrayList<Property>();
        this.propertyRepository.findAll().forEach(properties::add);
        return properties;
    }
}
