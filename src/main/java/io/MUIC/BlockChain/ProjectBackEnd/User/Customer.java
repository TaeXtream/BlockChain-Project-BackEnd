package io.MUIC.BlockChain.ProjectBackEnd.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer extends User {

    private String firstname;

    private String lastname;

    private String title;

    private final String role = "Customer";
}
