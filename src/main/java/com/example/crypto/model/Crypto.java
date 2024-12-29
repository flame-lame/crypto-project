package com.example.crypto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Crypto(
        @NotNull
        Integer id,
        @NotBlank
        String name,
        @NotBlank
        String symbol,
        @PositiveOrZero
        Double price,
        @Positive
        Double quantity
) {
}