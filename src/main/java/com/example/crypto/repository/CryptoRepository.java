package com.example.crypto.repository;

import com.example.crypto.model.Crypto;

import java.util.Collection;
import java.util.Optional;

public interface CryptoRepository
{
	Optional<Crypto> findById(Integer id);

	Crypto save(Crypto crypto) throws DuplicatedIdException;

	Collection<Crypto> findAll();
}
