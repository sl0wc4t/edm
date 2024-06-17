package edm.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler({ValidationException.class, EntityNotFoundException.class})
    public ResponseEntity<?> handleException(ValidationException e) {
        return ResponseEntity.ok(e.getMessage());
    }

}
