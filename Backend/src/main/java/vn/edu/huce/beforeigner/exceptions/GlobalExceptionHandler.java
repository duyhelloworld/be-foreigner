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
        response.setErrorCode(ResponseCode.UNEXPECTED_ERROR.getCode());
        
        // Custom Exception đã định nghĩa
        if (ex instanceof AppException appEx) {
            response.setErrorCode(appEx.getResponseCode().getCode());
            response.setMessages(appEx.getResponseCode().getMessage());
        }
        
        // Security
        if (ex instanceof AccessDeniedException accessDeniedException) {
            response.setErrorCode(ResponseCode.FORBIDDEN.getCode());
            response.setMessages(accessDeniedException.getMessage());
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
                response.setErrorCode(ResponseCode.UNEXPECTED_ERROR.getCode());
            }
        }

        // Url invalid
        if (ex instanceof FileNotFoundException || ex instanceof NoResourceFoundException) {
            response.setMessages(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(response);
    }
}
