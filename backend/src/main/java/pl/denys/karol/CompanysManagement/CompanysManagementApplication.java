package pl.denys.karol.CompanysManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "pl.denys.karol.CompanysManagement")
public class CompanysManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanysManagementApplication.class, args);
	}

}
