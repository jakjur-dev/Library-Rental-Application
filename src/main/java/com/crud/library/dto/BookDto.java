package com.crud.library.dto;

import com.crud.library.domain.Rental;
import com.crud.library.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String status;
}
