package io.MUIC.BlockChain.ProjectBackEnd.Service;

import io.MUIC.BlockChain.ProjectBackEnd.Model.AuthenticationRequest;
import io.MUIC.BlockChain.ProjectBackEnd.Model.AuthenticationResponse;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.AdminRepository;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.AgentRepository;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.CustomerRepository;
import io.MUIC.BlockChain.ProjectBackEnd.User.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AgentRepository agentRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        AppUser user = searchForUserByUsername(username);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setStatus("success");
            response.setUsername(username);
            response.setFirstname(user.getFirstname());
            response.setLastname(user.getLastname());
            return response;
        }
        return new AuthenticationResponse("failed", null, null, null);
    }

    public AppUser searchForUserByUsername(String username) {
        if (adminRepository.existsByUsername(username))
            return adminRepository.findByUsername(username);
        else if (customerRepository.existsByUsername(username))
            return customerRepository.findByUsername(username);
        else
            return agentRepository.findByUsername(username);
    }
}
