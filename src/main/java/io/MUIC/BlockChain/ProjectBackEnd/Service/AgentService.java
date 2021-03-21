package io.MUIC.BlockChain.ProjectBackEnd.Service;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import io.MUIC.BlockChain.ProjectBackEnd.Model.AddPropertyRequest;
import io.MUIC.BlockChain.ProjectBackEnd.Model.RemovePropertyRequest;
import io.MUIC.BlockChain.ProjectBackEnd.Model.ValidateResponse;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.AgentRepository;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.PropertyRepository;
import io.MUIC.BlockChain.ProjectBackEnd.User.PropertyAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public PropertyAgent authenticate(String username, String password) {
        PropertyAgent agent = agentRepository.findByUsername(username);
        if (agent != null) {
            if (agent.getPassword().equals(password)) {
                return agent;
            }
        }
        return null;
    }

    public List<Property> getAgentPropertyList(String agentUsername) {
        return agentRepository.findByUsername(agentUsername).getPropertyList();
    }


    public ValidateResponse addNewProperty(AddPropertyRequest addPropertyRequest) {
        if (!propertyRepository.existsByName(addPropertyRequest.getName())) {
            Property property = new Property();
            property.setId("p" + propertyRepository.count());
            property.setName(addPropertyRequest.getName());
            property.setAddressNumber(addPropertyRequest.getAddressNumber());
            property.setDistrict(addPropertyRequest.getDistrict());
            property.setProvince(addPropertyRequest.getProvince());
            property.setCountry(addPropertyRequest.getCountry());
            property.setActiveStatus(true);
            property.setBuildingType(addPropertyRequest.getBuildingType());
            property.setRentPrice(addPropertyRequest.getRentPrice());
            property.setSalePrice(addPropertyRequest.getSalePrice());
            PropertyAgent agent = agentRepository.findByUsername(addPropertyRequest.getAgentName());
            //property.setPropertyAgent(agent);
            agent.addProperty(property);
            property.setSellPeriod(addPropertyRequest.getSellPeriod());
            // Add property to fabric
            propertyRepository.save(property);
            return new ValidateResponse("Success");
        }
        return new ValidateResponse("Fail");
    }

    public ValidateResponse removeProperty(RemovePropertyRequest removePropertyRequest) {
        Property target = propertyRepository.findByName(removePropertyRequest.getName());
        //PropertyAgent targetAgent = target.getPropertyAgent();
        //targetAgent.getPropertyList().remove(target);
        propertyRepository.delete(target);
        // Delete from fabric
        if (!propertyRepository.existsByName(removePropertyRequest.getName())) {// && !targetAgent.getPropertyList().contains(target)) {
            return new ValidateResponse("Success");
        }
        return new ValidateResponse("Fail");
    }
}
