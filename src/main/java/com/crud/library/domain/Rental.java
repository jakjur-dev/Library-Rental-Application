package com.crud.library.domain;

import com.crud.library.observer.Observable;
import com.crud.library.mail.Mail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedNativeQuery(
        name = "Rental.retrieveDueRentalsOfReader",
        query = "SELECT * FROM rentals" +
                " WHERE DATE_PART('day', NOW() - return_date) >= 1" +
                " AND status = 'active'"+
                " AND reader_id = :READER_ID",
        resultClass = Rental.class
)

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RENTALS")
public class Rental implements Observable {

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

    @NotNull
    @Column(name = "return_date")
    private LocalDate returnDate;

    @NotNull
    @Column(name = "status")
    private String status;

    public Rental(@NotNull Book book, @NotNull Reader reader, @NotNull LocalDate rentDate, @NotNull LocalDate returnDate, @NotNull String status) {
        this.book = book;
        this.reader = reader;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    @Override
    public Mail getNotificationMail() {
        return reader.update(this);
    }

}
