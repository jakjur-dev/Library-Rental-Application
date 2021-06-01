package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "TITLES")
public class Title {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "publication_year")
    private int publicationYear;

    @OneToMany(targetEntity = Book.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Book> bookList = new ArrayList<>();

    public Title(@NotNull String author, @NotNull String title, @NotNull int publicationYear) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public Title(@NotNull Long id, @NotNull String author, @NotNull String title, @NotNull int publicationYear) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
    }
}
