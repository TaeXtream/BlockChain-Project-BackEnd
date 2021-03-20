package io.MUIC.BlockChain.ProjectBackEnd.Fabric;

import io.MUIC.BlockChain.ProjectBackEnd.Model.AddPropertyRequest;
import lombok.Getter;
import lombok.Setter;
import org.hyperledger.fabric.gateway.*;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.Properties;
import java.util.Set;

@Getter
@Setter
@Service
public class FabricNetwork {
    Path walletPath;
    Wallet wallet;
    Path networkConfigPath;
    String username = "admin";
    String orgName;
    String mspID;
    String orgUrl;
    String caFile;


    public FabricNetwork() {
        try {
            this.setOrganization(1);
            X509Identity adminIdentity = (X509Identity) wallet.get("admin");
            if (adminIdentity == null) {
                this.enrollAdmin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FabricNetwork(int orgNumber) {
        try {
            this.setOrganization(orgNumber);
            X509Identity adminIdentity = (X509Identity) wallet.get("admin");
            if (adminIdentity == null) {
                this.enrollAdmin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOrganization(int number) throws IOException {
        if (number == 1) {
            this.orgName = "org1.department1";
            this.walletPath = Paths.get("wallet");
            this.wallet = Wallets.newFileSystemWallet(walletPath);
            this.networkConfigPath = Paths.get("fabric-samples", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
            this.mspID = "Org1MSP";
            this.orgUrl = "https://localhost:7054";
            this.caFile = "fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/ca/ca.org1.example.com-cert.pem";
        } else if (number == 2) {
            this.orgName = "org2.department1";
            this.walletPath = Paths.get("wallet");
            this.wallet = Wallets.newFileSystemWallet(walletPath);
            this.networkConfigPath = Paths.get("fabric-samples", "test-network", "organizations", "peerOrganizations", "org2.example.com", "connection-org2.yaml");
            this.mspID = "Org2MSP";
            this.orgUrl = "https://localhost:8054";
            this.caFile = "fabric-samples/test-network/organizations/peerOrganizations/org2.example.com/ca/ca.org2.example.com-cert.pem";
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addProperty(String id, AddPropertyRequest addPropertyRequest) {
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            contract.submitTransaction("createProperty", id,
                    addPropertyRequest.getName(), addPropertyRequest.getSignature(),
                    addPropertyRequest.getAgentName(), addPropertyRequest.getDocumentPath(),
                    addPropertyRequest.getImagePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProperty(String id) {
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            contract.submitTransaction("deleteProperty", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPropertyDetail(String id) {
        byte[] result = {};
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            result = contract.evaluateTransaction("readProperty", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertyOwner(String id) {
        byte[] result = {};
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            result = contract.evaluateTransaction("getPropertyOwner", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertyDocumentPath(String id) {
        byte[] result = {};
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            result = contract.evaluateTransaction("getPropertyDocumentPath", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertyDocumentImagePath(String id) {
        byte[] result = {};
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            result = contract.evaluateTransaction("getPropertyImagePath", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertySignature(String id) {
        byte[] result = {};
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            result = contract.evaluateTransaction("getPropertySignature", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public String getPropertyHistory(String id) {
        byte[] result = {};
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            result = contract.evaluateTransaction("getPropertyHistory", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public void transferPropertyOwner(String id, String newOwner) {
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            contract.submitTransaction("transferProperty", id, newOwner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAllPropertiesDetail() {
        byte[] result = {};
        try {
            Gateway.Builder builder = Gateway.createBuilder();
            builder.identity(wallet, username).networkConfig(networkConfigPath).discovery(true);
            Gateway gateway = builder.connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("fabproperty");
            result = contract.evaluateTransaction("getAllProperties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    private void enrollAdmin() {
        try {
            // Create a CA client for interacting with the CA.
            String username = "admin";
            String password = "adminpw";
            Properties props = new Properties();
            props.put("pemFile", this.caFile);
            props.put("allowAllHostNames", "true");
            HFCAClient caClient = HFCAClient.createNewInstance(this.orgUrl, props);
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
            Identity user = Identities.newX509Identity(this.mspID, enrollment);
            wallet.put(username, user);
            System.out.printf("Successfully enrolled user %s and imported it into the wallet of %s\n", username, this.orgName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerUser(String username) {
        try {
            this.setUsername(username);
            // Create a CA client for interacting with the CA.
            Properties props = new Properties();
            props.put("pemFile", this.caFile);
            props.put("allowAllHostNames", "true");
            HFCAClient caClient = HFCAClient.createNewInstance(this.orgUrl, props);
            CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
            caClient.setCryptoSuite(cryptoSuite);

            // Create a wallet for managing identities
            Wallet wallet = Wallets.newFileSystemWallet(Paths.get("wallet"));

            // Check to see if we've already enrolled the user.
            if (wallet.get(username) != null) {
                System.out.printf("An identity for the user %s already exists in the wallet\n", username);
                return;
            }
            X509Identity adminIdentity = (X509Identity) wallet.get("admin");
            if (adminIdentity == null) {
                System.out.println("\"admin\" needs to be enrolled and added to the wallet first");
                return;
            }
            User user = new User() {

                @Override
                public String getName() {
                    return username;
                }

                @Override
                public Set<String> getRoles() {
                    return null;
                }

                @Override
                public String getAccount() {
                    return null;
                }

                @Override
                public String getAffiliation() {
                    return orgName;
                }

                @Override
                public Enrollment getEnrollment() {
                    return new Enrollment() {
                        @Override
                        public PrivateKey getKey() {
                            return adminIdentity.getPrivateKey();
                        }

                        @Override
                        public String getCert() {
                            return Identities.toPemString(adminIdentity.getCertificate());
                        }
                    };
                }

                @Override
                public String getMspId() {
                    return mspID;
                }

            };
            // Register the user, enroll the user, and import the new identity into the wallet.
            RegistrationRequest registrationRequest = new RegistrationRequest(username);
            registrationRequest.setAffiliation(this.orgName);
            registrationRequest.setEnrollmentID(username);
            String enrollmentSecret = caClient.register(registrationRequest, user);
            Enrollment enrollment = caClient.enroll(username, enrollmentSecret);
            Identity identity = Identities.newX509Identity(this.getMspID(), enrollment);
            wallet.put(username, identity);
            System.out.printf("Successfully enrolled user %s and imported it into the wallet of %s\n", username, this.orgName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
