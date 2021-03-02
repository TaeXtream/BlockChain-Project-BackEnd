package io.MUIC.BlockChain.ProjectBackEnd.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Land {

    @Id
    private Long id;

    private String name;
    private Address address;
}
