package com.crud.library.service;

import com.crud.library.domain.ITBook;
import com.crud.library.domain.logs.ITBookLog;
import com.crud.library.repository.ITBookRepository;
import com.crud.library.repository.logs.ITBookLogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ITBookService {

    private final ITBookRepository repository;
    private final ITBookLogRepository itBookLogRepository;


    public void saveITBook(final ITBook iTBook){
        repository.save(iTBook);
        itBookLogRepository.save(new ITBookLog(iTBook.getTitle(), iTBook.getIsbn13(), LocalDate.now()));
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
