package nz.net.example.wordart.gen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WAExceptionHandler {

    @ExceptionHandler({Exception.class, WAInternalServiceException.class})
    protected ResponseEntity<Object> handleInternalServiceErrorResponse(Exception ex, HttpStatus status) {
        return constructErrorResponse(status, new ExceptionResponse(status.value(), ex.getMessage()));
    }

    private ResponseEntity<Object> constructErrorResponse(HttpStatus status, Object bodyResponse) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(bodyResponse);
    }
}