package io.MUIC.BlockChain.ProjectBackEnd.Fabric;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import lombok.Getter;
import lombok.Setter;
import org.hyperledger.fabric.gateway.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Setter
public class FabricNetwork {
    Path walletPath;
    Wallet wallet;
    Path networkConfigPath;
    Network network;
    Contract contract;

    public FabricNetwork() throws IOException {
        this.walletPath = Paths.get("wallet");
        this.wallet = Wallets.newFileSystemWallet(walletPath);
        this.networkConfigPath = Paths.get("..", "..", "fabric-samples", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);
        try (Gateway gateway = builder.connect()) {
            this.network = gateway.getNetwork("mychannel");
            this.contract = network.getContract("fabproperty");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(Property property) {
        try {
            contract.submitTransaction("createProperty", property.getId(), property.getName(), property.getSignature());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
