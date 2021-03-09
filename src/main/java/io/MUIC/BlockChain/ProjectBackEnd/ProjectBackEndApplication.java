package io.MUIC.BlockChain.ProjectBackEnd;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import io.MUIC.BlockChain.ProjectBackEnd.User.Admin;
import io.MUIC.BlockChain.ProjectBackEnd.User.PropertyAgent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectBackEndApplication implements CommandLineRunner {

//	@Autowired
//	private PropertyRepository propertyRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) {

        //propertyRepository.deleteAll();
        Property property1 = new Property();
        property1.setName("Extra Condo");
        property1.setAddressNumber("250");
        property1.setDistrict("Rang Sit");
        property1.setProvince("Bangkok");
        property1.setCountry("Thailand");
        property1.setActiveStatus(true);
        property1.setBuildingType("Condo");
        property1.setSalePrice("5000000");
        property1.setRentPrice("10000");

        //propertyRepository.save(property1);

        //System.out.println(propertyRepository.findByName("Extra Condo").toString());

        Admin admin = new Admin("admin", "3443", "Jame", "K");
        System.out.println(admin.getRole());

        PropertyAgent agent = new PropertyAgent("agent", "3443", "Jack", "B", "Condo Corp.");

        agent.addProperty(property1);

        System.out.println(agent.getPropertyList().toString());
    }

}
