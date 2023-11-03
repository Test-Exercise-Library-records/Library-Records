package com.example.libraryrecords.exception;

/**
 * Custom exception class for representing a "Library Record Not Found" scenario in the Library Records microservice.
 */
public class LibraryRecordNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code LibraryRecordNotFoundException} with the specified error message.
   *
   * @param message The error message that describes the exception.
   */
  public LibraryRecordNotFoundException(String message) {
    super(message);
  }
}
