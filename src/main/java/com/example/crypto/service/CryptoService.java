package com.example.crypto.service;

import com.example.crypto.controller.FieldNotExistsException;
import com.example.crypto.model.Crypto;
import com.example.crypto.repository.CryptoRepository;
import com.example.crypto.repository.DuplicatedIdException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public final class CryptoService {
    private static final Map<String, Comparator<Crypto>> FIELD_COMPARATORS = Map.of(
            "price", Comparator.comparing(Crypto::price),
            "name", Comparator.comparing(Crypto::name),
            "quantity", Comparator.comparing(Crypto::quantity)
    );

    private final CryptoRepository cryptoRepository;

    public List<Crypto> findAll(String sortField) throws FieldNotExistsException
    {
        Collection<Crypto> allCrypto = cryptoRepository.findAll();
        List<Crypto> listCopy = new ArrayList<>(allCrypto);

        if (!Strings.isBlank(sortField)) {
            Comparator<Crypto> comparator = FIELD_COMPARATORS.get(sortField);

            if (comparator == null) {
                throw new FieldNotExistsException("Field " + sortField + " does not support sorting");
            }

            listCopy.sort(comparator);
        }

        return listCopy;
    }

    public Optional<Crypto> findById(Integer id) {
        return cryptoRepository.findById(id);
    }

    public synchronized void add(Crypto crypto) throws DuplicatedIdException
    {
        cryptoRepository.save(crypto);
    }
}
