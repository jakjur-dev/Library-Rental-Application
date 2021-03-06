package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name = "Title.retrieveTitleByString",
        query = "SELECT * FROM titles" +
                " WHERE title LIKE CONCAT('%', SUBSTRING(:KEYWORD FROM 1 FOR 3), '%')",
        resultClass = Title.class
)

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
    @Column(name = "title", unique = true)
    private String title;

    @NotNull
    @Column(name = "publication_year")
    private int publicationYear;

    @OneToMany(targetEntity = Book.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private final List<Book> bookList = new ArrayList<>();

    public Title(@NotNull String author, @NotNull String title, @NotNull int publicationYear) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
