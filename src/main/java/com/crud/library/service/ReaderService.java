package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.domain.logs.ReaderLog;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.logs.ReaderLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository repository;
    private final ReaderLogRepository readerLogRepository;

    public void saveReader(final Reader reader){
        repository.save(reader);
    }

    public Reader getReaderByEmailAndPassword(String email, String password) throws ReaderNotFoundException {
        Reader reader = repository.findReaderByEmailAndPassword(email, password).orElseThrow(ReaderNotFoundException::new);
        readerLogRepository.save(new ReaderLog(reader.getName(), reader.getSurname(), reader.getEmail(), LocalDate.now()));
        return reader;
    }

    public List<Reader> getAllReaders() {
        return repository.findAll();
    }
}
