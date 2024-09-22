package vn.edu.huce.beforeigner.exceptions;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({ Exception.class })
    ResponseEntity<ErrorResponse> handleAppException(Exception ex) {
        
        log.error("Throwed a {} with message : '{}'", ex.getClass().getSimpleName(), ex.getMessage());
        
        ErrorResponse response = new ErrorResponse();
        ResponseCode responseCode = ResponseCode.UNEXPECTED_ERROR;

        // Custom Exception đã định nghĩa
        if (ex instanceof AppException appEx) {
            responseCode = appEx.getResponseCode();
        }
        
        // Security
        if (ex instanceof AccessDeniedException) {
            responseCode = ResponseCode.FORBIDDEN;
        }

        // @Valid
        if (ex instanceof MethodArgumentNotValidException invalidException) {
            try {
                response.setMessages(invalidException.getBindingResult().getAllErrors()
                    .stream()
                    .map(e -> ResponseCode.valueOf(e.getDefaultMessage()).getMessage())
                    .toArray(String[]::new));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
            } catch (IllegalArgumentException e) {
                log.error("Invalid key : {}", e.getMessage());
                responseCode = ResponseCode.UNEXPECTED_ERROR;
            }
        }

        // Url invalid
        if (ex instanceof FileNotFoundException || ex instanceof NoResourceFoundException) {
            response.setMessages(ex.getMessage());
        }

        response.setErrorCode(responseCode.getCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(response);
    }
}
