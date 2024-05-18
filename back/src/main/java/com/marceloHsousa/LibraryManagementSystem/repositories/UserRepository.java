package com.marceloHsousa.LibraryManagementSystem.repositories;

import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);
}
