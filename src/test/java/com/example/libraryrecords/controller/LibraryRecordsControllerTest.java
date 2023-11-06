package com.example.libraryrecords.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.exception.LibraryRecordNotFoundException;
import com.example.libraryrecords.service.LibraryRecordsService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class LibraryRecordsControllerTest {
  private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Library Records not found";

  @InjectMocks
  private LibraryRecordsController libraryRecordsController;

  @Mock
  private LibraryRecordsService libraryRecordsService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateLibraryRecord() {
    LibraryRecordDTO inputRecord = new LibraryRecordDTO();
    when(libraryRecordsService.create(inputRecord)).thenReturn(inputRecord);

    ResponseEntity<LibraryRecordDTO> response =
        libraryRecordsController.createLibraryRecord(inputRecord);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(inputRecord, response.getBody());
  }

  @Test
  void testGetLibraryRecordById() {
    Long recordId = 1L;
    LibraryRecordDTO record = new LibraryRecordDTO();
    when(libraryRecordsService.getById(recordId)).thenReturn(record);

    ResponseEntity<LibraryRecordDTO> response =
        libraryRecordsController.getLibraryRecordById(recordId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(record, response.getBody());
  }

  @Test
  void testGetLibraryRecordById_RecordNotFound() {
    long recordId = 1L;

    when(libraryRecordsService.getById(recordId)).thenReturn(null);

    assertThrows(LibraryRecordNotFoundException.class,
        () -> libraryRecordsController.getLibraryRecordById(recordId));
  }

  @Test
  void testGetAllLibraryRecords() {
    when(libraryRecordsService.getAll()).thenReturn(new ArrayList<>());

    assertThrows(LibraryRecordNotFoundException.class,
        () -> libraryRecordsController.getAllLibraryRecords(), NOT_FOUND_EXCEPTION_MESSAGE);
  }

  @Test
  void testGetAllLibraryRecords_NoContent() {
    when(libraryRecordsService.getAll()).thenReturn(Collections.emptyList());

    assertThrows(LibraryRecordNotFoundException.class,
        () -> libraryRecordsController.getAllLibraryRecords(), NOT_FOUND_EXCEPTION_MESSAGE);
  }

  @Test
  void testUpdateLibraryRecord() {
    Long recordId = 1L;
    LibraryRecordDTO updatedRecord = new LibraryRecordDTO();
    when(libraryRecordsService.update(recordId, updatedRecord)).thenReturn(updatedRecord);

    ResponseEntity<LibraryRecordDTO> response =
        libraryRecordsController.updateLibraryRecord(recordId, updatedRecord);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedRecord, response.getBody());
  }

  @Test
  void testUpdateLibraryRecord_RecordNotFound() {
    long recordId = 1L;
    LibraryRecordDTO inputDTO = new LibraryRecordDTO();

    when(libraryRecordsService.update(recordId, inputDTO)).thenReturn(null);

    assertThrows(LibraryRecordNotFoundException.class,
        () -> libraryRecordsController.updateLibraryRecord(recordId, inputDTO));
  }

  @Test
  void testDeleteLibraryRecord() {
    Long recordId = 1L;

    ResponseEntity<Void> response = libraryRecordsController.deleteLibraryRecord(recordId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
