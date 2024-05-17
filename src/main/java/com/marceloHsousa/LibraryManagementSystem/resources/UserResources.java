package com.marceloHsousa.LibraryManagementSystem.resources;


import com.marceloHsousa.LibraryManagementSystem.services.UserService;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    private UserService service;


    @Autowired
    public UserResources(UserService service) {
        this.service = service;
    }
}
