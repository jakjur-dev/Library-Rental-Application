package com.crud.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Title already in the database")
public class TitleNotUniqueException extends Exception {
    public TitleNotUniqueException() {
        super("Title already in the database");
    }
}
