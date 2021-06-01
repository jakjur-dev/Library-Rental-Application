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
}
