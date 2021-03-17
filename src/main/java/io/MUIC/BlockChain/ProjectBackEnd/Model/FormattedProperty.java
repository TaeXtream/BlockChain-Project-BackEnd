package io.MUIC.BlockChain.ProjectBackEnd.Model;

import io.MUIC.BlockChain.ProjectBackEnd.User.PropertyAgent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormattedProperty {

    private String id;

    private String name;

    private String addressNumber;

    private String district;

    private String province;

    private String country;

    private boolean activeStatus;

    private String buildingType;

    private String salePrice;

    private String rentPrice;

    private PropertyAgent propertyAgent;

    private String sellPeriod;

}
