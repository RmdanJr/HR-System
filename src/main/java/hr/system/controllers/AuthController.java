package hr.system.controllers;

import hr.system.dtos.Credentials;
import hr.system.dtos.UsernameAndPassword;
import hr.system.entities.Account;
import hr.system.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("login")
    public Credentials login(@RequestBody UsernameAndPassword usernameAndPassword) {
        try {
            Account user = (Account) userDetailsService.loadUserByUsername(usernameAndPassword.getUsername());
            System.out.println(user.getAuthorities());
            return new Credentials(encoder.matches(usernameAndPassword.getPassword(), user.getPassword()),
                                user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER")));
        } catch (Exception e) {
            return new Credentials(false, false);
        }
    }
}
