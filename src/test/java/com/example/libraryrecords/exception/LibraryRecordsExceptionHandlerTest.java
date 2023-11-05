package com.example.libraryrecords.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class LibraryRecordsExceptionHandlerTest {

  @Test
  void testHandleLibraryRecordNotFoundException() {
    LibraryRecordNotFoundException exception =
        new LibraryRecordNotFoundException("Record not found");
    LibraryRecordsExceptionHandler handler = new LibraryRecordsExceptionHandler();

    ResponseEntity<String> responseEntity = handler.handleStudentNotFoundException(exception);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals("Record not found", responseEntity.getBody());
  }
}
