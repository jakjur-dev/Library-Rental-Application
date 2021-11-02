package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ITBOOKS_WATCHLIST")
public class ITBook {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @NotNull
    @Column(name = "ebook")
    private boolean ebook;

    @NotNull
    @Column(name = "isbn13")
    private String isbn13;

    @NotNull
    @Column(name = "image")
    private String image;

    @NotNull
    @Column(name = "url")
    private String url;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public ITBook(@NotNull String title, String subtitle, @NotNull boolean ebook,  @NotNull String isbn13,  @NotNull String image, @NotNull String url, @NotNull Reader reader) {
        this.title = title;
        this.subtitle = subtitle;
        this.ebook = ebook;
        this.isbn13 = isbn13;
        this.image = image;
        this.url = url;
        this.reader = reader;
    }
}
