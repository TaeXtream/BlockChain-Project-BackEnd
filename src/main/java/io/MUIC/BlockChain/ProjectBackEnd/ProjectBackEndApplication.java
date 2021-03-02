package io.MUIC.BlockChain.ProjectBackEnd;

import io.MUIC.BlockChain.ProjectBackEnd.Entity.Property;
import io.MUIC.BlockChain.ProjectBackEnd.Repository.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectBackEndApplication implements CommandLineRunner {

	@Autowired
	private PropertyRepository propertyRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) {

		propertyRepository.deleteAll();
		Property property1 = new Property();
		property1.setName("Extra Condo");
		property1.setAddressNumber("250");
		property1.setDistrict("Rang Sit");
		property1.setProvince("Bangkok");
		property1.setCountry("Thailand");
		property1.setStatus(true);
		property1.setBuildingType("Condo");
		property1.setSalePrice("5000000");
		property1.setRentPrice("10000");

		propertyRepository.save(property1);

		System.out.println(propertyRepository.findByName("Extra Condo").toString());


	}

	}
