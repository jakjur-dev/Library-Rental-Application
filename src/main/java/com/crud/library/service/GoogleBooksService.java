package com.crud.library.service;

import com.crud.library.ITBookstore.client.GoogleBooksClient;
import com.crud.library.domain.ITBook;
import com.crud.library.dto.GoogleItemDto;
import com.crud.library.dto.ITBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleBooksService {

    private final GoogleBooksClient googleBooksClient;
    private final ITBookService itBookService;

    public List<GoogleItemDto> fetchBooks(String keyword, Long readerId) {
        List<ITBook> userItBooks = itBookService.getAllEbooksITBooksByReader(readerId);
        List<GoogleItemDto> itBooks = googleBooksClient.getBooks(keyword);

        for (GoogleItemDto itBook : itBooks) {
            for (ITBook userItEBook : userItBooks) {
                if (itBook.getBook().getTitle().equals(userItEBook.getTitle())) {
                    itBook.getBook().setId(userItEBook.getId());
                }
            }
        }

        return itBooks;
    }
}
