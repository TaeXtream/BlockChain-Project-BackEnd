package io.MUIC.BlockChain.ProjectBackEnd.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class AppUser {

    @Id
    private String id;

    @NonNull
    private String username;

    @JsonIgnore
    @NonNull
    private String password;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

}
