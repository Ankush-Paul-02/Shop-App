package com.devmare.shop.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devmare.shop.payload.AppApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle ResourceNotFoundException
     * 
     * @param exception
     * @return ResponseEntity<AppApiResponse>
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppApiResponse> resourceNotFoundException(
            ResourceNotFoundException exception) {

        String message = exception.getMessage();
        AppApiResponse response = new AppApiResponse(message, false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle MethodArgumentNotValidException
     * 
     * @param exception
     * @return ResponseEntity<Map<String, String>>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception) {
        Map<String, String> response = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String errorDefaultMessage = objectError.getDefaultMessage();
            response.put(fieldName, errorDefaultMessage);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle AppException
     * 
     * @param exception
     * @return ResponseEntity<AppApiResponse>
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppApiResponse> handleApiException(AppException exception) {
        String message = exception.getMessage();
        AppApiResponse apiResponse = new AppApiResponse(message, true);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
