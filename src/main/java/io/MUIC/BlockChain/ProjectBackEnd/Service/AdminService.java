package io.MUIC.BlockChain.ProjectBackEnd.Service;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import io.MUIC.BlockChain.ProjectBackEnd.Model.*;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.AdminRepository;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.AgentRepository;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.CustomerRepository;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.PropertyRepository;
import io.MUIC.BlockChain.ProjectBackEnd.User.Admin;
import io.MUIC.BlockChain.ProjectBackEnd.User.Customer;
import io.MUIC.BlockChain.ProjectBackEnd.User.PropertyAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public List<PropertyAgent> getAllAgent() {
        return agentRepository.findAll();
    }

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public List<Property> getAllProperty() {
        return propertyRepository.findAll();
    }

    public ValidateResponse addCustomer(AddCustomerOrAdminRequest addCustomerOrAdminRequest) {
        if (!customerRepository.existsByUsername(addCustomerOrAdminRequest.getUsername())) {
            Customer customer = new Customer();
            customer.setUsername(addCustomerOrAdminRequest.getUsername());
            String hashedPassword = BCrypt.hashpw(addCustomerOrAdminRequest.getPassword(), BCrypt.gensalt());
            customer.setPassword(hashedPassword);
            // Add Blockchain Identity Function also
            customerRepository.save(customer);
            return new ValidateResponse("Success");
        }
        return new ValidateResponse("Fail");
    }

    public ValidateResponse addAdmin(AddCustomerOrAdminRequest addCustomerOrAdminRequest) {
        if (!adminRepository.existsByUsername(addCustomerOrAdminRequest.getUsername())) {
            Admin admin = new Admin();
            admin.setUsername(addCustomerOrAdminRequest.getUsername());
            String hashedPassword = BCrypt.hashpw(addCustomerOrAdminRequest.getPassword(), BCrypt.gensalt());
            admin.setPassword(hashedPassword);
            // Add Blockchain Identity Function also
            adminRepository.save(admin);
            return new ValidateResponse("Success");
        }
        return new ValidateResponse("Fail");
    }

    public ValidateResponse addAgent(AddAgentRequest addAgentRequest) {
        if (!agentRepository.existsByUsername(addAgentRequest.getUsername())) {
            PropertyAgent agent = new PropertyAgent();
            agent.setUsername(addAgentRequest.getUsername());
            String hashedPassword = BCrypt.hashpw(addAgentRequest.getPassword(), BCrypt.gensalt());
            agent.setPassword(hashedPassword);
            agent.setCompanyName(addAgentRequest.getCompanyName());
            agent.setPropertyList(new ArrayList<>());
            // Add Blockchain Identity Function also
            agentRepository.save(agent);
            return new ValidateResponse("Success");
        }
        return new ValidateResponse("Fail");
    }

    public ValidateResponse addNewProperty(AddPropertyRequest addPropertyRequest) {
        if (!propertyRepository.existsByName(addPropertyRequest.getName())) {
            Property property = new Property();
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
            property.setPropertyAgent(agent);
            agent.addProperty(property);
            property.setSellPeriod(addPropertyRequest.getSellPeriod());
            propertyRepository.save(property);
            return new ValidateResponse("Success");
        }
        return new ValidateResponse("Fail");
    }

    public ValidateResponse removeCustomer(RemoveAppUserRequest removeCustomerRequest) {
        Customer target = customerRepository.findByUsername(removeCustomerRequest.getUsername());
        customerRepository.delete(target);
        if (!customerRepository.existsByUsername(removeCustomerRequest.getUsername()))
            return new ValidateResponse("Success");
        return new ValidateResponse("Fail");
    }

    public ValidateResponse removeAdmin(RemoveAppUserRequest removeAdminRequest) {
        Admin target = adminRepository.findByUsername(removeAdminRequest.getUsername());
        adminRepository.delete(target);
        if (!adminRepository.existsByUsername(removeAdminRequest.getUsername()))
            return new ValidateResponse("Success");
        return new ValidateResponse("Fail");
    }

    public ValidateResponse removeAgent(RemoveAppUserRequest removeAgentRequest) {
        PropertyAgent target = agentRepository.findByUsername(removeAgentRequest.getUsername());
        for (Property property : target.getPropertyList()) {
            propertyRepository.delete(property);
        }
        agentRepository.delete(target);
        if (!agentRepository.existsByUsername(removeAgentRequest.getUsername()))
            return new ValidateResponse("Success");
        return new ValidateResponse("Fail");
    }

    public ValidateResponse removeProperty(RemovePropertyRequest removePropertyRequest) {
        Property target = propertyRepository.findByName(removePropertyRequest.getName());
        PropertyAgent targetAgent = target.getPropertyAgent();
        targetAgent.getPropertyList().remove(target);
        propertyRepository.delete(target);
        if (!propertyRepository.existsByName(removePropertyRequest.getName()) && !targetAgent.getPropertyList().contains(target)) {
            return new ValidateResponse("Success");
        }
        return new ValidateResponse("Fail");
    }


}
