package com.crud.library.domain.logs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "BOOK_LOG")
public class BookLog {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "keyword")
    private String kayword;

    @NotNull
    @Column(name = "operation_date")
    private LocalDate operationDate;

    @NotNull
    @Column(name = "list_length")
    private int bookListLength;

    public BookLog(@NotNull String kayword, @NotNull LocalDate operationDate, @NotNull int bookListLength) {
        this.kayword = kayword;
        this.operationDate = operationDate;
        this.bookListLength = bookListLength;
    }
}
