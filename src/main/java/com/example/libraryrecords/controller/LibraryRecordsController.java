package com.example.libraryrecords.controller;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.exception.LibraryRecordNotFoundException;
import com.example.libraryrecords.service.LibraryRecordsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * For managing library records.
 */
@RestController
@RequestMapping("/library-records")
@RequiredArgsConstructor
public class LibraryRecordsController implements LibraryRecordsAPI {

  private final LibraryRecordsService libraryRecordsService;

  @Override
  @PostMapping
  public ResponseEntity<LibraryRecordDTO> createLibraryRecord(
      @RequestBody @Valid LibraryRecordDTO libraryRecord) {
    LibraryRecordDTO createdRecord = libraryRecordsService.create(libraryRecord);
    return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<LibraryRecordDTO> getLibraryRecordById(@PathVariable @Min(1) Long id) {
    LibraryRecordDTO libraryRecord = libraryRecordsService.getById(id);
    if (libraryRecord != null) {
      return new ResponseEntity<>(libraryRecord, HttpStatus.OK);
    } else {
      throw new LibraryRecordNotFoundException("Library Record not found");
    }
  }

  @Override
  @GetMapping
  public ResponseEntity<List<LibraryRecordDTO>> getAllLibraryRecords() {
    List<LibraryRecordDTO> libraryRecords = libraryRecordsService.getAll();
    if (!libraryRecords.isEmpty()) {
      return new ResponseEntity<>(libraryRecords, HttpStatus.OK);
    } else {
      throw new LibraryRecordNotFoundException("Library Records not found");
    }
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<LibraryRecordDTO> updateLibraryRecord(@PathVariable @Min(1) Long id,
      @RequestBody @Valid LibraryRecordDTO libraryRecord) {
    LibraryRecordDTO updatedRecord = libraryRecordsService.update(id, libraryRecord);
    if (updatedRecord != null) {
      return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    } else {
      throw new LibraryRecordNotFoundException("Library Record not found");
    }
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLibraryRecord(@PathVariable @Min(1) Long id) {
    libraryRecordsService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
