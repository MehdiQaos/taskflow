package dev.mehdi.taskflow.exception.handler;

import dev.mehdi.taskflow.exception.response.ErrorValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

//@ControllerAdvice
//public class ExceptionHandlerAdvice {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//     ResponseEntity<ErrorValidationResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = ex.getBindingResult()
//            .getFieldErrors()
//            .stream()
//            .collect(Collectors.toMap(
//                    FieldError::getField,
//                    entry -> {
//                        String v = entry.getDefaultMessage();
//                        return v != null ? v : "";
//                    }
//            ));
//
//        ErrorValidationResponse errorResponse = new ErrorValidationResponse(ex.getMessage(), errors);
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
//    }
//
//}
