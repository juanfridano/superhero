package com.superheroes.hero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestArgumentException extends RuntimeException {
    public InvalidRequestArgumentException(String message){
        super(message);
    }
}
