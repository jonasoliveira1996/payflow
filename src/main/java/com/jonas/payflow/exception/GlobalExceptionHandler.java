package com.jonas.payflow.exception;

import com.jonas.payflow.application.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex,
                                                        HttpServletRequest request
    ){
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(),
                                                HttpStatus.BAD_REQUEST.value(),
                                            "Business error",
                                                ex.getMessage(),
                                                request.getRequestURI()
                                            );
        return ResponseEntity.badRequest().body(error);
    };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult()
                                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " +
                        error.getDefaultMessage())
                .orElse("Erro de validação");

        ErrorResponse error = new ErrorResponse(LocalDateTime.now(),
                                                HttpStatus.BAD_REQUEST.value(),
                                                "Validation error",
                                                message,
                                                request.getRequestURI()
                                            );
        return ResponseEntity.badRequest().body(error);
    }
}
