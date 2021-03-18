package io.MUIC.BlockChain.ProjectBackEnd.Model;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DashboardResponse {
    private List<Property> propertyList;
}
