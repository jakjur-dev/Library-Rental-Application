package com.crud.library.domain.logs;

import com.crud.library.domain.Reader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "IT_BOOK_LOG")
public class ITBookLog {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "isbn13")
    private String isbn13;

    @NotNull
    @Column(name = "operation_date")
    private LocalDate operationDate;

    public ITBookLog(@NotNull String title, @NotNull String isbn13, @NotNull LocalDate operationDate) {
        this.title = title;
        this.isbn13 = isbn13;
        this.operationDate = operationDate;
    }
}
