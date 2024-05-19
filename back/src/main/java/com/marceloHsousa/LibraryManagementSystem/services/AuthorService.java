package com.marceloHsousa.LibraryManagementSystem.services;


import com.marceloHsousa.LibraryManagementSystem.entities.author.Author;
import com.marceloHsousa.LibraryManagementSystem.entities.author.exceptions.AuthorNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.entities.book.Book;
import com.marceloHsousa.LibraryManagementSystem.entities.book.exceptions.BookNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.repositories.AuthorRepository;
import com.marceloHsousa.LibraryManagementSystem.services.exceptions.DatabaseException;
import com.marceloHsousa.LibraryManagementSystem.services.exceptions.ResourcesNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> findAll(){
        return authorRepository.findAll();
    }


    public Author FindAuthorById(Long id){
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent()){
            return author.get();
        }else{
            throw new AuthorNotFoundException("author Not Found");
        }
    }

    @Transactional
    public Author save(Author author){
        return authorRepository.save(author);
    }

    @Transactional
    public List<Author> saveAll(List<Author> authors){
        return authorRepository.saveAll(authors);
    }

    @Transactional
    public void delete(Long id){
        try {
            authorRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e){
            throw  new ResourcesNotFoundException(id);

        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public Author update(Long id, Author obj){

        try {
            Author entity = authorRepository.getReferenceById(id);
            updateData(entity, obj);
            return  authorRepository.save(entity);

        } catch (EntityNotFoundException e){
            e.printStackTrace();
            throw  new ResourcesNotFoundException(id);
        }
    }

    private void updateData(Author entity, Author obj) {

        entity.setName(obj.getName());
        entity.setNationality(obj.getNationality());
        entity.setBirthDate(obj.getBirthDate());
    }
}
