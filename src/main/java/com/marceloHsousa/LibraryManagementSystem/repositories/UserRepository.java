package com.marceloHsousa.LibraryManagementSystem.repositories;

import com.marceloHsousa.LibraryManagementSystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
