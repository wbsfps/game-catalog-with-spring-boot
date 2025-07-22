package br.com.wbs.exceptions.handler;

import br.com.wbs.exceptions.ExceptionResponse;
import br.com.wbs.exceptions.StudioFoundException;
import br.com.wbs.exceptions.StudioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse
                (new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudioFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleFoundException(StudioFoundException ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StudioNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleFoundException(StudioNotFoundException ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ExceptionResponse> buildResponse
            (Exception exception, WebRequest webRequest, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse
                        (new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(response, status);
    }
}
