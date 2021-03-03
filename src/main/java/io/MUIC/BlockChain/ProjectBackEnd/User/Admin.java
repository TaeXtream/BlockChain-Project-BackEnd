package io.MUIC.BlockChain.ProjectBackEnd.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Admin extends AppUser {

    private final String role = "Admin";

    public Admin(String username, String password, String firstname, String lastname, String title) {
        super(username, password, firstname, lastname, title);

    }
}
