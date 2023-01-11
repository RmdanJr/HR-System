package hr.system;

import hr.system.repositories.DepartmentRepository;
import hr.system.repositories.EmployeeRepository;
import hr.system.repositories.TeamRepository;
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
    CommandLineRunner commandLineRunner(DepartmentRepository departmentRepository, TeamRepository teamRepository,
                                        EmployeeRepository employeeRepository, PasswordEncoder encoder) {
        return args -> {
//			DepartmentManager devManager = new DepartmentManager("khosy", encoder.encode("khosyPass"), "KHOSY",
//					new Date(1970, 1, 1), Gender.MALE, new Date(1992, 7, 1),
//					new Salary(60000), null);
//            employeeRepository.save(devManager);
//			Department dev = new Department("Development", devManager, new ArrayList<>(), new ArrayList<>());
//            departmentRepository.save(dev);
//            TeamLead ayman = new TeamLead("ayman.shebl", encoder.encode("aymanPass"), "Ayman Shebl",
//                    new Date(1983, 1, 1), Gender.MALE, new Date(2005, 7, 1),
//                    new Salary(5000), new ArrayList<>(Arrays.asList(new Expertise("Leadership", ExpertiseLevel.EXPERT))));
//            employeeRepository.save(ayman);
//            Team mrc = new Team("MRC", dev, new ArrayList<>(), ayman);
//            teamRepository.save(mrc);
//            dev.setTeams(new ArrayList<>(Arrays.asList(mrc)));
//            departmentRepository.save(dev);
//            Employee hassan = new Employee("RmdanJr", encoder.encode("RmdanJrPass"),
//                    "Hassan Ramadan", new Date(2000, 10, 1), Gender.MALE,
//                    new Date(2022, 7, 1), new Salary(3000),
//                    new ArrayList<Expertise>(Arrays.asList(new Expertise("C++", ExpertiseLevel.EXPERT),
//                            new Expertise("Java", ExpertiseLevel.NEWBIE))));
//            hassan.setDepartment(dev);
//            hassan.setTeam(mrc);
//            employeeRepository.save(hassan);
        };
    }
}
