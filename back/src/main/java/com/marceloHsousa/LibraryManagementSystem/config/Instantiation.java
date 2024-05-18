package com.marceloHsousa.LibraryManagementSystem.config;

import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public Instantiation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        List<User> users = new ArrayList<>();

        users.add(new User("Gabriel Meireles", "gabriel@gmail.com", passwordEncoder.encode("gabe123"), "ROLE_ADMIN"));
        users.add(new User("Ana Silva", "ana@gmail.com", passwordEncoder.encode("ana456"), "ROLE_USER"));
        users.add(new User("João Santos", "joao@gmail.com", passwordEncoder.encode("joao789"), "ROLE_USER"));
        users.add(new User("Maria Oliveira", "maria@gmail.com", passwordEncoder.encode("maria321"), "ROLE_ADMIN"));

        userRepository.saveAll(users);
    }
}
