package com.crud.library.domain.logs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "TITLE_ADD_LOG")
public class TitleLog {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "title", unique = true)
    private String title;

    @NotNull
    @Column(name = "publication_year")
    private int publicationYear;

    @NotNull
    @Column(name = "operation_date")
    private LocalDate operationDate;

    public TitleLog(@NotNull String author, @NotNull String title, @NotNull int publicationYear, @NotNull LocalDate operationDate) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
        this.operationDate = operationDate;
    }
}
