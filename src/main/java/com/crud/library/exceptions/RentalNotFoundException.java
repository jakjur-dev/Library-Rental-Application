package com.crud.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find rental in the database")
public class RentalNotFoundException extends Exception {
    public RentalNotFoundException() {
        super("Could not find rental in the database");
    }
}
