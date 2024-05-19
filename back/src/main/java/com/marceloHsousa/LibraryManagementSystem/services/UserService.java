package com.marceloHsousa.LibraryManagementSystem.services;

import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.entities.user.exceptions.UserNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.services.exceptions.ResourcesNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.repositories.UserRepository;
import com.marceloHsousa.LibraryManagementSystem.services.exceptions.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new UserNotFoundException("User Not Found");
        }
    }

    public User findByEmail(String email){
        Optional<List<User>> user=userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get().get(0);
        }else{
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }
    @Transactional
    public List<User> saveAll(List<User> users){
        return userRepository.saveAll(users);
    }

    @Transactional
    public void delete(Long id){
        try {
            userRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e){
            throw  new ResourcesNotFoundException(id);

        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public User update(Long id, User obj){

        try {
            User entity = userRepository.getReferenceById(id);
            updateData(entity, obj);
            return  userRepository.save(entity);

        } catch (EntityNotFoundException e){
            throw new ResourcesNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {

        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPassword(obj.getPassword());
    }
}
