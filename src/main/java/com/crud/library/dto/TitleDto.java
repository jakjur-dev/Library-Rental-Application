package com.crud.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TitleDto {

    private Long id;
    private String author;
    private String title;
    private int publicationYear;
}
