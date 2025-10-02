package com.acad.sys.Security;

import com.acad.sys.Model.AdminUser;
import com.acad.sys.Repository.AdminUserRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeedConfig {

    @Bean
    CommandLineRunner seedAdmin(AdminUserRepositorio repo, PasswordEncoder encoder) {
        return args -> {
            String email = "admin@academia.com"; // dev
            String senha = "123456"; // dev

            repo.findByEmail(email).orElseGet(() -> {
                AdminUser u = new AdminUser();
                u.setEmail(email);
                u.setPasswordHash(encoder.encode(senha)); // BCrypt
                return repo.save(u);
            });
        };
    }
}