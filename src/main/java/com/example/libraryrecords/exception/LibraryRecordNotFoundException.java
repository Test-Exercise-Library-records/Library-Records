package com.example.libraryrecords.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom exception class for representing a "Library Record Not Found" scenario in the Library
 * Records microservice.
 */
@Slf4j
public class LibraryRecordNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code LibraryRecordNotFoundException} with the specified error message.
   *
   * @param message The error message that describes the exception.
   */
  public LibraryRecordNotFoundException(String message) {
    super(message);
    log.debug("Record is not found exception: {}", message);
  }
}
