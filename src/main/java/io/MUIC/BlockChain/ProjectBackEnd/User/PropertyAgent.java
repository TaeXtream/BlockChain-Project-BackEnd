package io.MUIC.BlockChain.ProjectBackEnd.User;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PropertyAgent extends AppUser {

    private String companyName;

    private List<Property> propertyList;

    private final String role = "Agent";

    public PropertyAgent(String username, String password, String firstname, String lastname, String title, String companyName) {
        super(username, password, firstname, lastname, title);
        this.companyName = companyName;
        propertyList = new ArrayList<>();

    }
}
