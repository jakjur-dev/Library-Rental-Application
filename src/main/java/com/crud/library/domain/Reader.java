package com.crud.library.domain;

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
public class Reader {

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

    @OneToMany(targetEntity = Rental.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Rental> rentalsList = new ArrayList<>();

    public Reader(@NotNull String name, @NotNull String surname, @NotNull LocalDate accountCreationDate, String email) {
        this.name = name;
        this.surname = surname;
        this.accountCreationDate = accountCreationDate;
        this.email = email;
    }

    public Reader(@NotNull Long id, @NotNull String name, @NotNull String surname, @NotNull LocalDate accountCreationDate, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accountCreationDate = accountCreationDate;
        this.email = email;
    }
}
