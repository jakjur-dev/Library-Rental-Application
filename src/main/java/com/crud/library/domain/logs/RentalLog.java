package com.crud.library.domain.logs;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "RETURN_LOG")
public class RentalLog {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "book_id")
    private Long book;

    @NotNull
    @Column(name = "reader_id")
    private Long reader;

    @NotNull
    @Column(name = "return_date")
    private LocalDate returnDate;

    public RentalLog(@NotNull Long book, @NotNull Long reader, @NotNull LocalDate returnDate) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.returnDate = returnDate;
    }


}
