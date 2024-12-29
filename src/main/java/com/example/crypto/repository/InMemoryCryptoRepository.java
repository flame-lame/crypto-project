package com.example.crypto.repository;

import com.example.crypto.model.Crypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCryptoRepository implements CryptoRepository
{
	@Value("${app.repository.inmemory.replace-if-exists:false}")
	private boolean replaceIfExists;

	private final Map<Integer, Crypto> cryptoList = new ConcurrentHashMap<>();

	@Override
	public Optional<Crypto> findById(Integer id)
	{
		return Optional.ofNullable(
				cryptoList.get(id));
	}

	@Override
	public Crypto save(Crypto crypto) throws DuplicatedIdException
	{
		if (replaceIfExists) {
			cryptoList.put(crypto.id(), crypto);
		} else {
			Crypto originalCrypto = cryptoList.putIfAbsent(crypto.id(), crypto);
			if (originalCrypto != null) {
				// Existing value on the index, not saved
				throw new DuplicatedIdException("Record with id " + crypto.id() + " already exists");
			}
		}

		return crypto;
	}

	@Override
	public Collection<Crypto> findAll()
	{
		return cryptoList.values();
	}
}
