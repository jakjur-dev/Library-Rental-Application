package com.crud.library.domain;

import com.crud.library.observer.Observer;
import com.crud.library.mail.Mail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "READERS")
public class Reader implements Observer {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "creation_date")
    private LocalDate accountCreationDate;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "admin")
    private boolean admin;

    @OneToMany(targetEntity = Rental.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private final List<Rental> rentalsList = new ArrayList<>();

    public Reader(@NotNull String name, @NotNull String surname, @NotNull LocalDate accountCreationDate, @NotNull String email, @NotNull String password, @NotNull boolean admin) {
        this.name = name;
        this.surname = surname;
        this.accountCreationDate = accountCreationDate;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    @Override
    public Mail update(Rental rental) {
        return new Mail(
                rental.getReader().getEmail(),
                "New rental on your account!",
                "You have rented a book: " + rental.getBook().getTitle().getTitle() + ", please return it before " + rental.getReturnDate());
    }
}
