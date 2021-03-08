package io.MUIC.BlockChain.ProjectBackEnd.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPropertyRequest {

    private String name;

    private String addressNumber;

    private String district;

    private String province;

    private String country;

    private boolean status;

    private String buildingType;

    private String salePrice;

    private String rentPrice;

    private String sellPeriod;
}
