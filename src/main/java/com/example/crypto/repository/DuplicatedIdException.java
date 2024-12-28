package com.example.crypto.service;

public class DuplicatedIdException extends Exception {
    public DuplicatedIdException(String message) {
        super(message);
    }
}
