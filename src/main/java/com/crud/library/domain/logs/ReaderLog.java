package com.crud.library.domain.logs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "READER_LOGIN")
public class ReaderLog {

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
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "login_date")
    private LocalDate loginDate;

    public ReaderLog(@NotNull String name, @NotNull String surname, @NotNull String email, @NotNull LocalDate loginDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.loginDate = loginDate;
    }
}
