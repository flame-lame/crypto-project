package com.example.crypto.controller;

import com.example.crypto.model.Crypto;
import com.example.crypto.service.CryptoService;
import com.example.crypto.repository.DuplicatedIdException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
public final class CryptoController {
    private final CryptoService cryptoService;

    @PostMapping("/cryptos")
    public ResponseEntity<Void> addCryptoInPortfolio(
            @RequestBody @Valid Crypto crypto) {
        try {
            cryptoService.add(crypto);
        } catch (DuplicatedIdException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Record with this id already exists", e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/cryptos")
    public ResponseEntity<List<Crypto>> findAllCryptos(
            @RequestParam(required = false) String sort) {
        try {
            return ResponseEntity.ok(cryptoService.findAll(sort));
        } catch (FieldNotExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort field " + sort, e);
        }
    }

    @GetMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> getCryptoById(
            @PathVariable Integer id) {
        return cryptoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
