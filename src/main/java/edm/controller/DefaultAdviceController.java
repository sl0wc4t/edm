package edm.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdviceController {

    @ExceptionHandler({ValidationException.class, EntityNotFoundException.class})
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.ok(e.getMessage());
    }

}
