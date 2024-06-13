package outBackend.cloudProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CloudProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudProjectApplication.class, args);
	}

}
