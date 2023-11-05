package com.example.libraryrecords.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the Library Records microservice.
 */
@RestControllerAdvice
public class LibraryRecordsExceptionHandler {

  /**
   * Exception handler method for handling {@link LibraryRecordNotFoundException}.
   *
   * @param exception The exception of type {@link LibraryRecordNotFoundException}.
   *
   * @return A {@link ResponseEntity} with a custom error response and a 404 (Not Found) status.
   */
  @ExceptionHandler({LibraryRecordNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleStudentNotFoundException(
      LibraryRecordNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }
}
