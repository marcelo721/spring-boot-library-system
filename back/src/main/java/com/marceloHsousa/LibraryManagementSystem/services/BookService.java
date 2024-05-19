package com.marceloHsousa.LibraryManagementSystem.services;

import com.marceloHsousa.LibraryManagementSystem.entities.book.Book;
import com.marceloHsousa.LibraryManagementSystem.entities.book.exceptions.BookNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.entities.user.exceptions.UserNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.repositories.BookRepository;
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
public class BookService {

    private BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findBookById(long id){

        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }else{
            throw new BookNotFoundException("Book Not Found");
        }
    }

    @Transactional
    public Book save(Book book){
       return bookRepository.save(book);
    }

    @Transactional
    public List<Book> saveAll(List<Book> books){
        return bookRepository.saveAll(books);
    }

    @Transactional
    public void delete(Long id){
        try {
            bookRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e){
            throw  new ResourcesNotFoundException(id);

        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public Book update(Long id, Book obj){

        try {
            Book entity = bookRepository.getReferenceById(id);
            updateData(entity, obj);
            return  bookRepository.save(entity);

        } catch (EntityNotFoundException e){
            e.printStackTrace();
            throw  new ResourcesNotFoundException(id);
        }
    }

    private void updateData(Book entity, Book obj) {
        entity.setAvailable(obj.getAvailable());
    }
}
