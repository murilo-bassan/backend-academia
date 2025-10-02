package com.acad.sys.Repository;

import com.acad.sys.Model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepositorio extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByEmail(String email);
}