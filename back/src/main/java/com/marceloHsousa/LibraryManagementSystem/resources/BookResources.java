package com.marceloHsousa.LibraryManagementSystem.resources;


import com.marceloHsousa.LibraryManagementSystem.entities.book.Book;
import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookResources {

    private BookService service;

    @Autowired
    public BookResources(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll(){
        List<Book> books = service.findAll();

        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id){
        Book book=service.findBookById(id);

        return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public ResponseEntity<Book> insert(@RequestBody Book book){
        book.setId(null);
        Book object = service.insert(book);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri();
        return ResponseEntity.created(uri).body(object);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
