package io.MUIC.BlockChain.ProjectBackEnd.Repository;

import io.MUIC.BlockChain.ProjectBackEnd.User.PropertyAgent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AgentRepository extends UserRepository<PropertyAgent> {

    List<PropertyAgent> findAllByCompanyName(String companyName);


}
