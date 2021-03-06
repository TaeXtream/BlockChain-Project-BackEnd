package io.MUIC.BlockChain.ProjectBackEnd.Repository;

import io.MUIC.BlockChain.ProjectBackEnd.User.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface UserRepository<T extends AppUser> extends MongoRepository<T, String> {

    T findByUsername(String username);

    List<T> findAllByRole(String role);

}
