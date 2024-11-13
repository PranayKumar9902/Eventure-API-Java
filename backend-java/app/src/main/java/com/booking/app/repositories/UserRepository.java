package com.booking.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
