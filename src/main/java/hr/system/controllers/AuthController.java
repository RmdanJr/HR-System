package hr.system.controllers;

import hr.system.dtos.UsernameAndPassword;
import hr.system.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
    public boolean login(@RequestBody UsernameAndPassword usernameAndPassword) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(usernameAndPassword.getUsername());
            return encoder.matches(usernameAndPassword.getPassword(), user.getPassword());
        } catch (Exception e) {
            return false;
        }
    }
}
