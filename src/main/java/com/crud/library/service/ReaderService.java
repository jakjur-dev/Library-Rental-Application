package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import com.crud.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository repository;

    public Reader saveReader(final Reader reader){
        return repository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return repository.findAll();
    }
}
