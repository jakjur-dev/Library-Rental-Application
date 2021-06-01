package com.crud.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "The book is already rented!")
public class BookRentedException extends Exception {
    public BookRentedException() {
        super("The book is already rented!");
    }
}
