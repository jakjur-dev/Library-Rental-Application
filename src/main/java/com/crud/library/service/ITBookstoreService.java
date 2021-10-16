package com.crud.library.service;

import com.crud.library.ITBookstore.client.ITBookstoreClient;
import com.crud.library.domain.ITBook;
import com.crud.library.dto.ITBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ITBookstoreService {

    private final ITBookstoreClient itBookstoreClient;
    private final ITBookService itBookService;

    public List<ITBookDto> fetchBooks(String keyword, Long readerID) {
        List<ITBook> userItBooks = itBookService.getAllBookstoreITBooksByReader(readerID);
        List<ITBookDto> itBooks = itBookstoreClient.getBooks(keyword);

        for (ITBookDto itBook : itBooks) {
            for (ITBook userItBook : userItBooks) {
                if (itBook.getTitle().equals(userItBook.getTitle())) {
                    itBook.setId(userItBook.getId());
                }
            }
        }

        return itBooks;
    }
}
