package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "image")
    private String image;

    @NotNull
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @OneToMany(targetEntity = Rental.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Rental> rentalList;

    public Book(@NotNull Title title, @NotNull String status, @NotNull String image, @NotNull LocalDate releaseDate) {
        this.title = title;
        this.status = status;
        this.image = image;
        this.releaseDate = releaseDate;
    }

    public Book(@NotNull Long id, @NotNull Title title, @NotNull String status, @NotNull String image, @NotNull LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.image = image;
        this.releaseDate = releaseDate;
    }

}
