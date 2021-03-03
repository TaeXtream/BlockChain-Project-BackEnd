package io.MUIC.BlockChain.ProjectBackEnd.User;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PropertyAgent extends User {

    private String companyName;

    private List<Property> propertyList;

    private final String role = "Agent";
}
