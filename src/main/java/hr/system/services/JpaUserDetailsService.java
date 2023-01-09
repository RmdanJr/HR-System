package hr.system.services;

import hr.system.entities.Account;
import hr.system.repositories.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final EmployeeRepository repository;

    public JpaUserDetailsService(EmployeeRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository
                .findByUsername(username)
                .map(Account::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}