package br.com.wbs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudioFoundException extends RuntimeException {
    public StudioFoundException() {
        super("The studio already exists");
    }
}
