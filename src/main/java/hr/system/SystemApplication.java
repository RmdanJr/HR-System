package hr.system;

import hr.system.entities.*;
import hr.system.repositories.DepartmentRepository;
import hr.system.repositories.EmployeeRepository;
import hr.system.repositories.TeamRepository;
import hr.system.utils.types.ExpertiseLevel;
import hr.system.utils.types.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(DepartmentRepository departmentRepository, TeamRepository teamRepository,
                                        EmployeeRepository employeeRepository, PasswordEncoder encoder) {
        return (args) -> {
            Employee devManager = new Employee("khosy", encoder.encode("khosy"), "KHOSY",
                    new Date(1970, 1, 1), Gender.MALE, new Date(1992, 7, 1),
                    new Salary(60000), new ArrayList<>());
            employeeRepository.saveAndFlush(devManager);
            Department dev = new Department("Development", devManager, new ArrayList<>(), new ArrayList<>());
            departmentRepository.saveAndFlush(dev);
            Employee ayman = new Employee("ayman", encoder.encode("ayman"), "Ayman Shebl",
                    new Date(1983, 1, 1), Gender.MALE, new Date(2005, 7, 1),
                    new Salary(5000), new ArrayList<>(List.of(new Expertise("Leadership", ExpertiseLevel.EXPERT))));
            ayman.setManager(devManager);
            employeeRepository.saveAndFlush(ayman);
            Team mrc = new Team("MRC", dev, new ArrayList<>(), ayman);
            teamRepository.saveAndFlush(mrc);
            Employee hassan = new Employee("RmdanJr", encoder.encode("RmdanJr"),
                    "Hassan Ramadan", new Date(2000, 10, 1), Gender.MALE,
                    new Date(2022, 7, 1), new Salary(3000),
                    new ArrayList<Expertise>(Arrays.asList(new Expertise("C++", ExpertiseLevel.EXPERT),
                            new Expertise("Java", ExpertiseLevel.NEWBIE))));
            hassan.setDepartment(dev);
            hassan.setTeam(mrc);
            hassan.setManager(ayman);
            employeeRepository.saveAndFlush(hassan);
        };
    }
}

