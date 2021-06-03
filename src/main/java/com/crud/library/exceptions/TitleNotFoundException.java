package com.crud.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find title in the database")
public class TitleNotFoundException extends Exception {
    public TitleNotFoundException() {
        super("Could not find title in the database");
    }
}
