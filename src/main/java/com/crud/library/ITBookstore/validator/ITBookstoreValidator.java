package com.crud.library.ITBookstore.validator;

import com.crud.library.domain.ITBook;
import com.crud.library.dto.ITBookDto;
import com.crud.library.service.ITBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ITBookstoreValidator {

    private final ITBookService itBookService;

    public List<ITBookDto> validateITBooks(Long readerID, List<ITBookDto> itBooks){
        List<ITBook> userItBooks = itBookService.getAllBookstoreITBooksByReader(readerID);

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
