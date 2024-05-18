package com.marceloHsousa.LibraryManagementSystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name="book_table")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="isbn")
    private String isbn;

    @Column(name="available")
    private Boolean available;

    public Book(){

    }

    public Book(String title, String isbn, Boolean available) {
        this.title = title;
        this.isbn = isbn;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
