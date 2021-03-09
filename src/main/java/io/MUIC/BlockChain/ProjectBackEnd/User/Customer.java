package io.MUIC.BlockChain.ProjectBackEnd.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer extends AppUser {

    private final String role = "User";

    public Customer(String username, String password, String firstname, String lastname) {
        super(username, password, firstname, lastname);
    }

}
