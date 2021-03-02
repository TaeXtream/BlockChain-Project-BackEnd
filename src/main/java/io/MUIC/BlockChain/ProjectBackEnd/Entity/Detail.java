package io.MUIC.BlockChain.ProjectBackEnd.Entity;

import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean status;

    private String buildingType;

    private String salePrice;

    private String rentPrice;
}
