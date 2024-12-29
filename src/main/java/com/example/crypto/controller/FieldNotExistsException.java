package com.example.crypto.controller;

public final class FieldNotExistsException extends Exception {
    public FieldNotExistsException(String message) {
        super(message);
    }
}
