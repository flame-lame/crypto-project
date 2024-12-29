package com.example.crypto.repository;

public class DuplicatedIdException extends Exception {
    public DuplicatedIdException(String message) {
        super(message);
    }
}
