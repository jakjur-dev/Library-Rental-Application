package com.crud.library.dto;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RentalDto {

    private Long id;
    private Long bookId;
    private Long readerId;
    private LocalDate rentDate;
    private LocalDate returnDate;

}
