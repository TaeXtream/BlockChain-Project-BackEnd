package io.MUIC.BlockChain.ProjectBackEnd.Service;

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

    public Customer addCustomer(String username, String password, String firstname, String lastname) {
        Customer customer = new Customer();

        customer.setUsername(username);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        customer.setPassword(hashedPassword);
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customerRepository.save(customer);

        return getCustomer(username);
    }
}
