package com.marceloHsousa.LibraryManagementSystem.services;

import com.marceloHsousa.LibraryManagementSystem.entities.User;
import com.marceloHsousa.LibraryManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findUserById(Long id){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new RuntimeException("User Not Found"); //placeholder
        }
    }
}
