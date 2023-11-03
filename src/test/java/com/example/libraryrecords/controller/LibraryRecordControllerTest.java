package com.example.libraryrecords.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.service.LibraryRecordService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class LibraryRecordControllerTest {
  @InjectMocks
  private LibraryRecordController libraryRecordController;

  @Mock
  private LibraryRecordService libraryRecordService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testCreateLibraryRecord() {
    LibraryRecordDTO inputRecord = new LibraryRecordDTO();
    when(libraryRecordService.create(inputRecord)).thenReturn(inputRecord);

    ResponseEntity<LibraryRecordDTO> response =
        libraryRecordController.createLibraryRecord(inputRecord);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(inputRecord, response.getBody());
  }

  @Test
  void testGetLibraryRecordById() {
    Long recordId = 1L;
    LibraryRecordDTO record = new LibraryRecordDTO();
    when(libraryRecordService.getById(recordId)).thenReturn(record);

    ResponseEntity<LibraryRecordDTO> response =
        libraryRecordController.getLibraryRecordById(recordId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(record, response.getBody());
  }

  @Test
  void testGetAllLibraryRecords() {
    when(libraryRecordService.getAll()).thenReturn(new ArrayList<>());

    ResponseEntity<List<LibraryRecordDTO>> response =
        libraryRecordController.getAllLibraryRecords();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void testUpdateLibraryRecord() {
    Long recordId = 1L;
    LibraryRecordDTO updatedRecord = new LibraryRecordDTO();
    when(libraryRecordService.update(recordId, updatedRecord)).thenReturn(updatedRecord);

    ResponseEntity<LibraryRecordDTO> response =
        libraryRecordController.updateLibraryRecord(recordId,
            updatedRecord);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedRecord, response.getBody());
  }

  @Test
  void testDeleteLibraryRecord() {
    Long recordId = 1L;

    ResponseEntity<Void> response = libraryRecordController.deleteLibraryRecord(recordId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}