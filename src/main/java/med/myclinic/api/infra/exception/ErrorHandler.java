package med.myclinic.api.infra.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EntityNotFoundException;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationData>> handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ErrorValidationData::new).toList());
    }

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<String> handleErrorBusinessLogic(ValidException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record ErrorValidationData(
            @JsonProperty("campo") String field, @JsonProperty("mensagem") String message) {
        public ErrorValidationData(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
