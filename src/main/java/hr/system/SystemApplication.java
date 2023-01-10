package hr.system;

import hr.system.entities.Employee;
import hr.system.entities.TeamLead;
import hr.system.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository users, PasswordEncoder encoder) {
		return args -> {

		};
	}
}
