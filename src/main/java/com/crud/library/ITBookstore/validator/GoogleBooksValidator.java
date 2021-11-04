package com.crud.library.ITBookstore.validator;

import com.crud.library.domain.ITBook;
import com.crud.library.dto.GoogleBookDto;
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

    public List<GoogleBookDto> validateGoogleBooks(Long readerID, List<GoogleBookDto> itBooks){
        List<ITBook> userItBooks = itBookService.getAllEbooksITBooksByReader(readerID);

        for (GoogleBookDto itBook : itBooks) {
            for (ITBook userItEBook : userItBooks) {
                if (itBook.getTitle().equals(userItEBook.getTitle())) {
                    itBook.setId(userItEBook.getId());
                }
            }
        }

        return itBooks;
    }
}
