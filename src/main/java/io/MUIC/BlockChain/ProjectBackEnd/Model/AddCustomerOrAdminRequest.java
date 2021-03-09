package io.MUIC.BlockChain.ProjectBackEnd.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddCustomerOrAdminRequest {

    private String username;

    private String password;

    private String firstname;

    private String lastname;

}
