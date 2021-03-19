package io.MUIC.BlockChain.ProjectBackEnd.Fabric;

import io.MUIC.BlockChain.ProjectBackEnd.Model.AddPropertyRequest;
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
        this.networkConfigPath = Paths.get("fabric-samples", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);
        try (Gateway gateway = builder.connect()) {
            this.network = gateway.getNetwork("mychannel");
            this.contract = network.getContract("fabproperty");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(String id, AddPropertyRequest addPropertyRequest) {
        try {
            contract.submitTransaction("createProperty", id,
                    addPropertyRequest.getName(), addPropertyRequest.getSignature(),
                    addPropertyRequest.getAgentName(), addPropertyRequest.getDocumentPath(), addPropertyRequest.getImagePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPropertyDetail(String id) {
        byte[] result = {};
        try {
            result = contract.evaluateTransaction("readProperty", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertyOwner(String id) {
        byte[] result = {};
        try {
            result = contract.evaluateTransaction("getPropertyOwner", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertyDocumentPath(String id) {
        byte[] result = {};
        try {
            result = contract.evaluateTransaction("getPropertyDocumentPath", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertyDocumentImagePath(String id) {
        byte[] result = {};
        try {
            result = contract.evaluateTransaction("getPropertyImagePath", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertySignature(String id) {
        byte[] result = {};
        try {
            result = contract.evaluateTransaction("getPropertySignature", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertyHistory(String id) {
        byte[] result = {};
        try {
            result = contract.evaluateTransaction("getPropertyHistory", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public void transferPropertyOwner(String id, String newOwner) {
        try {
            contract.submitTransaction("transferProperty", id, newOwner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
