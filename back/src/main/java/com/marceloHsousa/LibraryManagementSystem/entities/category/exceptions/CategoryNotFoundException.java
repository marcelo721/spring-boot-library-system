package com.marceloHsousa.LibraryManagementSystem.entities.category.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
