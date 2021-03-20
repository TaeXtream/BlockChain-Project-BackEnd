package io.MUIC.BlockChain.ProjectBackEnd.Fabric;

import io.MUIC.BlockChain.ProjectBackEnd.Model.AddPropertyRequest;
import lombok.Getter;
import lombok.Setter;
import org.hyperledger.fabric.gateway.*;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

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
        builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);
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

    public static void EnrollAdmin(String username, String password) {
        try {
            // Create a CA client for interacting with the CA.
            Properties props = new Properties();
            props.put("pemFile",
                    "fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/ca/ca.org1.example.com-cert.pem");
            props.put("allowAllHostNames", "true");
            HFCAClient caClient = HFCAClient.createNewInstance("https://localhost:7054", props);
            CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
            caClient.setCryptoSuite(cryptoSuite);
            // Create a wallet for managing identities
            Wallet wallet = Wallets.newFileSystemWallet(Paths.get("wallet"));

            // Check to see if we've already enrolled the admin user.
            if (wallet.get(username) != null) {
                System.out.printf("An identity for the admin user %s already exists in the wallet\n", username);
                return;
            }

            // Enroll the admin user, and import the new identity into the wallet.
            final EnrollmentRequest enrollmentRequestTLS = new EnrollmentRequest();
            enrollmentRequestTLS.addHost("localhost");
            enrollmentRequestTLS.setProfile("tls");
            Enrollment enrollment = caClient.enroll(username, password, enrollmentRequestTLS);
            Identity user = Identities.newX509Identity("Org1MSP", enrollment);
            wallet.put(username, user);
            System.out.printf("Successfully enrolled user %s and imported it into the wallet\n", username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
