package com.crud.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find reader in the database")
public class ReaderNotFoundException extends Exception {
    public ReaderNotFoundException() {
        super("Could not find reader in the database");
    }
}
