package de.goodyear.webappwithonion.adapter.errorhandler;

import de.goodyear.service.PersoneServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDateTime.now());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ":" +x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("Errors", errors);
        logger.warn("Es ist ein Fehler aufgetreten!");
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(PersoneServiceException.class)
    public ResponseEntity<Object> handlePersonenServiceException(PersoneServiceException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("Message", ex.getMessage());

        logger.error("Personenservice hat einen Fehler", ex);// Super wichtig

        if (ex.getMessage().equals("Unerwuenschte Person"))
            return ResponseEntity.badRequest().body(body);
        return ResponseEntity.internalServerError().body(body);
    }
}
