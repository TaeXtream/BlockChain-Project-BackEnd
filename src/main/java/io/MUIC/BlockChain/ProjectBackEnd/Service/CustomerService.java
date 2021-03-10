package io.MUIC.BlockChain.ProjectBackEnd.Service;

import io.MUIC.BlockChain.ProjectBackEnd.Model.AddCustomerOrAdminRequest;
import io.MUIC.BlockChain.ProjectBackEnd.Model.ValidateResponse;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.CustomerRepository;
import io.MUIC.BlockChain.ProjectBackEnd.User.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer authenticate(String username, String password) {
        Customer user = customerRepository.findByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public Customer getCustomer(String username) {
        return customerRepository.findByUsername(username);
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
}
