package com.crud.library.facade;

import com.crud.library.ITBookstore.client.ITBookstoreClient;
import com.crud.library.ITBookstore.validator.GoogleBooksValidator;
import com.crud.library.ITBookstore.validator.ITBookstoreValidator;
import com.crud.library.domain.ITBook;
import com.crud.library.dto.GoogleBookDto;
import com.crud.library.dto.GoogleItemDto;
import com.crud.library.dto.ITBookDto;
import com.crud.library.service.GoogleBooksService;
import com.crud.library.service.ITBookstoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookstoreFacade {

    private final ITBookstoreService itBookstoreService;
    private final GoogleBooksService googleBooksService;
    private final ITBookstoreValidator itBookstoreValidator;
    private final GoogleBooksValidator googleBooksValidator;

    public List<ITBookDto> fetchITBookstoreBooks(String keyword, Long readerID) {
        return itBookstoreValidator.validateITBooks(readerID, itBookstoreService.fetchBooks(keyword));
    }

    public List<GoogleBookDto> fetchGoogleBooksBooks(String keyword, Long readerID) {
        return googleBooksValidator.validateGoogleBooks(readerID, googleBooksService.fetchBooks(keyword));
    }
}
