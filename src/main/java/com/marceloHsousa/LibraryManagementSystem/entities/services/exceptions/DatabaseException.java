package com.marceloHsousa.LibraryManagementSystem.entities.services.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg){
        super(msg);
    }
}
