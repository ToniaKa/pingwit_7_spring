package pl.pingwit.springdemo.config.exceptionhandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pingwit.springdemo.exception.PingwitNotFoundException;
import pl.pingwit.springdemo.exception.PingwitValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class PingwitRestExceptionHandler {
    @ExceptionHandler(PingwitNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(PingwitNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(PingwitValidationException.class)
    public ResponseEntity<String> handleValidationException(PingwitValidationException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.toString());
    }

}
