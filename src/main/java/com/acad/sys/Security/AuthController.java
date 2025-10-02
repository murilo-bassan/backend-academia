package com.acad.sys.Security;

import com.acad.sys.Repository.AdminUserRepositorio;
import com.acad.sys.Security.dto.LoginRequest;
import com.acad.sys.Security.dto.TokenResponse;
import com.acad.sys.Security.jwt.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AdminUserRepositorio repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthController(AdminUserRepositorio repo, PasswordEncoder encoder, JwtService jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest body) {
        var admin = repo.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!encoder.matches(body.senha(), admin.getPasswordHash())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwt.generate(admin.getId(), admin.getEmail(), admin.getRole());
        return ResponseEntity.ok(new TokenResponse(token, jwt.getExpirationSeconds()));
    }
}
