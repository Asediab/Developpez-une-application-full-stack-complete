package com.openclassrooms.mddapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception if the requested object does not existe
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ObjectExisteException extends RuntimeException {
}
