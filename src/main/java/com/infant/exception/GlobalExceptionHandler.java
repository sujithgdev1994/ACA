package com.infant.exception;

import com.infant.response.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by sujith on 24-12-2022
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UsernameExistException.class)
  public ResponseEntity<ErrorMessage> userNotFoundException(Exception e) {
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.setError(e.getMessage());
    return ResponseEntity.badRequest().body(errorMessage);
  }
  
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorMessage> badRequestException(Exception e) {
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.setError(e.getMessage());
    return ResponseEntity.badRequest().body(errorMessage);
  }

}
