package com.marceloHsousa.LibraryManagementSystem.entities.author.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
