package com.marceloHsousa.LibraryManagementSystem.config;

import com.marceloHsousa.LibraryManagementSystem.entities.User;
import com.marceloHsousa.LibraryManagementSystem.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

    private UserRepository userRepository;

    public Instantiation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        User gabe = new User("Gabriel meireles", "gabriel@gmail.com", "gabriel321", "");
        userRepository.save(gabe);
    }
}
