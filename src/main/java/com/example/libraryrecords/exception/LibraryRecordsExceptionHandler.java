package com.example.libraryrecords.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the Library Records microservice.
 */
@ControllerAdvice
public class LibraryRecordsExceptionHandler {

  /**
   * Exception handler method for handling {@link LibraryRecordNotFoundException}.
   *
   * @param exception The exception of type {@link LibraryRecordNotFoundException}.
   * @return A {@link ResponseEntity} with a custom error response and a 404 (Not Found) status.
   */
  @ExceptionHandler({LibraryRecordNotFoundException.class})
  public ResponseEntity<Object> handleStudentNotFoundException(
      LibraryRecordNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }
}
