package io.MUIC.BlockChain.ProjectBackEnd;

import io.MUIC.BlockChain.ProjectBackEnd.Fabric.FabricNetwork;
import io.MUIC.BlockChain.ProjectBackEnd.Model.AddPropertyRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectBackEndApplication implements CommandLineRunner {

	//@Autowired
	//private PropertyRepository propertyRepository;

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) {

		FabricNetwork fabricNetwork1 = new FabricNetwork();
		AddPropertyRequest addPropertyRequest = new AddPropertyRequest();
		addPropertyRequest.setImagePath("asset/image/p1.jpg");
		addPropertyRequest.setDocumentPath("asset/doc/ti1.pdf");
		addPropertyRequest.setSignature("uosdbiu");
		addPropertyRequest.setAgentName("Johnson");
		addPropertyRequest.setName("O House");

		fabricNetwork1.addProperty("p1", addPropertyRequest);

		System.out.println(fabricNetwork1.getPropertyDetail("p1"));

		fabricNetwork1.transferPropertyOwner("p1", "Paul");

		System.out.println(fabricNetwork1.getPropertyHistory("p1"));


	}

}
