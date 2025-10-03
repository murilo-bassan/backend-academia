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
            String email = System.getenv().getOrDefault("ADMIN_EMAIL", "admin@academia.com");
            String senha = System.getenv().getOrDefault("ADMIN_PASS", "123456");

            repo.findByEmail(email).orElseGet(() -> {
                AdminUser u = new AdminUser();
                u.setEmail(email);
                u.setPasswordHash(encoder.encode(senha)); // BCrypt
                return repo.save(u);
            });
        };
    }
}