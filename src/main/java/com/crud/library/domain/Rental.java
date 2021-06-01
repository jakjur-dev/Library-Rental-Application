package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @NotNull
    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public Rental(@NotNull Book book, @NotNull Reader reader, @NotNull LocalDate rentDate, LocalDate returnDate) {
        this.book = book;
        this.reader = reader;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public Rental(@NotNull Book book, @NotNull Reader reader, @NotNull LocalDate rentDate) {
        this.book = book;
        this.reader = reader;
        this.rentDate = rentDate;
    }
}
