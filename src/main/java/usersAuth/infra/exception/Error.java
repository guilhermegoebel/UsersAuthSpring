package usersAuth.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class Error {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDTO>> error400(MethodArgumentNotValidException exception){
        var e = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(e.stream().map(ErrorDTO::new).toList());
    }

    public record ErrorDTO(String field, String message) {
        public ErrorDTO(FieldError e){
            this(e.getField(), e.getDefaultMessage());
        }
    }
}
