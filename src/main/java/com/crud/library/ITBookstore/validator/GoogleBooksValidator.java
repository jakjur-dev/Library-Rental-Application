package com.crud.library.ITBookstore.validator;

import com.crud.library.domain.ITBook;
import com.crud.library.dto.GoogleItemDto;
import com.crud.library.dto.ITBookDto;
import com.crud.library.service.ITBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleBooksValidator {

    private final ITBookService itBookService;

    public List<GoogleItemDto> validateGoogleBooks(Long readerID, List<GoogleItemDto> itBooks){
        List<ITBook> userItBooks = itBookService.getAllEbooksITBooksByReader(readerID);

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
