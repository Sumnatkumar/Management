package com.management.repository;

import com.management.model.User;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface UserRepository extends JpaRepository<User, Long> {
    Page findByUsername(String username, Pageable pageable);
    Page findByEmail(String email, Pageable pageable);
}
