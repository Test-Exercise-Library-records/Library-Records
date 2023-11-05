package com.example.libraryrecords.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class LibraryRecordNotFoundExceptionTest {

  @Test
  void testLibraryRecordNotFoundException() {
    String errorMessage = "Library Record not found";

    LibraryRecordNotFoundException exception = new LibraryRecordNotFoundException(errorMessage);

    assertNotNull(exception);
    assertEquals(errorMessage, exception.getMessage());
  }
}
