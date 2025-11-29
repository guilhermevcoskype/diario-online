package com.gui.diarioOnline.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MediaAlreadyExistsException extends RuntimeException {
    public MediaAlreadyExistsException(String message) {
        super(message);
    }
}

