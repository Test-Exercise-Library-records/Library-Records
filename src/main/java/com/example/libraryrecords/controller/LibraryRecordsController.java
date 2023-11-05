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

  /**
   * Creates a new library record in the system.
   *
   * @param libraryRecord The library record to be created.
   *
   * @return A response entity containing the created library record with a status of 201 (Created).
   */
  @Override
  @PostMapping
  public ResponseEntity<LibraryRecordDTO> createLibraryRecord(
      @RequestBody @Valid LibraryRecordDTO libraryRecord) {
    LibraryRecordDTO createdRecord = libraryRecordsService.create(libraryRecord);
    return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
  }

  /**
   * Retrieves a library record by its unique ID.
   *
   * @param id The ID of the library record to retrieve.
   *
   * @return A response entity containing the retrieved library record with a status of 200 (OK) if
   *         found, or a response with a status of 404 (Not Found) if the record does not exist.
   */
  @GetMapping("/{id}")
  public ResponseEntity<LibraryRecordDTO> getLibraryRecordById(@PathVariable @Min(1) Long id) {
    LibraryRecordDTO libraryRecord = libraryRecordsService.getById(id);
    if (libraryRecord != null) {
      return new ResponseEntity<>(libraryRecord, HttpStatus.OK);
    } else {
      throw new LibraryRecordNotFoundException("Library Record not found");
    }
  }

  /**
   * Retrieves a list of all library records available in the system.
   *
   * @return A response entity containing a list of retrieved library records with a status of 200
   *         (OK) if records exist, or a response with a status of 204 (No Content) if there are no
   *         records.
   */
  @GetMapping
  public ResponseEntity<List<LibraryRecordDTO>> getAllLibraryRecords() {
    List<LibraryRecordDTO> libraryRecords = libraryRecordsService.getAll();
    if (!libraryRecords.isEmpty()) {
      return new ResponseEntity<>(libraryRecords, HttpStatus.OK);
    } else {
      throw new LibraryRecordNotFoundException("Library Records not found");
    }
  }

  /**
   * Updates an existing library record by its ID.
   *
   * @param id The ID of the library record to update.
   * @param libraryRecord The updated library record information.
   *
   * @return A response entity containing the updated library record with a status of 200 (OK) if
   *         the update is successful, or a response with a status of 404 (Not Found) if the record
   *         does not exist, or a response with a status of 400 (Bad Request) if the request is
   *         invalid.
   */
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

  /**
   * Deletes a library record by its ID.
   *
   * @param id The ID of the library record to delete.
   *
   * @return A response entity with a status of 204 (No Content) if the record is successfully
   *         deleted.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLibraryRecord(@PathVariable @Min(1) Long id) {
    libraryRecordsService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
