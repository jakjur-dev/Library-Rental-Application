package com.crud.library.dto;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

    private Long id;
    private Long bookId;
    private String bookTitle;
    private Long readerId;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private String status;

    public RentalDto(Long bookId, Long readerId, LocalDate rentDate, LocalDate returnDate, String status) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.status = status;
    }
}
