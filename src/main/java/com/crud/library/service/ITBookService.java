package com.crud.library.service;

import com.crud.library.domain.ITBook;
import com.crud.library.repository.ITBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ITBookService {

    private final ITBookRepository repository;


    public void saveITBook(final ITBook iTBook){
        repository.save(iTBook);
    }

    public List<ITBook> getAllBookstoreITBooksByReader(Long readerId) {
        return repository.findByReaderIdAndEbook(readerId, false);
    }

    public List<ITBook> getAllEbooksITBooksByReader(Long readerId) {
        return repository.findByReaderIdAndEbook(readerId, true);
    }

    public void deleteITBook(Long bookId) {
        repository.deleteById(bookId);
    }
}
