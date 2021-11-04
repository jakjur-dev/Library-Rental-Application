package com.crud.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReaderDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDate accountCreationDate;
    private String email;
    private String password;
    private boolean admin;

    public ReaderDto(String name, String surname, LocalDate accountCreationDate, String email, String password, boolean admin) {
        this.name = name;
        this.surname = surname;
        this.accountCreationDate = accountCreationDate;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }
}
