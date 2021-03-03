package io.MUIC.BlockChain.ProjectBackEnd.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class User {

    @Id
    private String id;

    private String username;

    @JsonIgnore
    private String password;


}
