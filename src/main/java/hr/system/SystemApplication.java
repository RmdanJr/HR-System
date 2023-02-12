package hr.system;

import hr.system.entities.Employee;
import hr.system.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*");
            }
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository, PasswordEncoder encoder) {
        return (args) -> {
            Employee admin = new Employee("admin", encoder.encode("admin"), "admin");
            admin.addRole("ROLE_MANAGER");
            employeeRepository.saveAndFlush(admin);
            Employee user = new Employee("user", encoder.encode("user"), "user");
            employeeRepository.saveAndFlush(user);
        };
    }
}

