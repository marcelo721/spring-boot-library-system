package com.marceloHsousa.LibraryManagementSystem.resources;


import com.marceloHsousa.LibraryManagementSystem.entities.author.Author;
import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorResources {

    private AuthorService service;

    public AuthorResources(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAll(){
        List<Author> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Long id){
        Author author =service.FindAuthorById(id);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping
    public ResponseEntity<Author> insert(@RequestBody Author author){

        author.setId(null);
        Author object = service.save(author);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri();
        return ResponseEntity.created(uri).body(object);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
