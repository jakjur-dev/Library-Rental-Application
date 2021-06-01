package com.crud.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find book in the database")
public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("Could not find book in the database");
    }
}
