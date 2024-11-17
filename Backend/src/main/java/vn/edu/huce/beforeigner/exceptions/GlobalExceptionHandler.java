package vn.edu.huce.beforeigner.exceptions;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({ Exception.class })
    ResponseEntity<ApiResponse<List<String>>> handleAppException(Exception ex) {
        
        log.error("Throwed a {} with message : '{}'", ex.getClass().getSimpleName(), ex.getMessage());
        
        ApiResponse<List<String>> response = new ApiResponse<>();
        
        // Custom Exception đã định nghĩa
        if (ex instanceof AppException appEx) {
            response.setCode(appEx.getResponseCode().getCode());
            response.setData(List.of(appEx.getResponseCode().getMessage()));
            return ResponseEntity.ok(response);
        }
        
        // @Valid
        if (ex instanceof MethodArgumentNotValidException invalidException) {
            try {
                response.setCode(ResponseCode.ERROR_WHEN_VALIDATE.getCode());
                response.setData(invalidException.getBindingResult().getAllErrors()
                    .stream()
                    .map(e -> ResponseCode.valueOf(e.getDefaultMessage()).getMessage())
                    .toList());
                return ResponseEntity.ok(response);
            } catch (IllegalArgumentException e) {
                log.error("Invalid key : {}", e.getMessage());
                response.setCode(ResponseCode.UNEXPECTED_ERROR.getCode());
                response.setData(List.of(ResponseCode.UNEXPECTED_ERROR.getMessage()));
                return ResponseEntity.ok(response);
            }
        }

        // Url invalid
        if (ex instanceof FileNotFoundException || ex instanceof NoResourceFoundException) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }
}
