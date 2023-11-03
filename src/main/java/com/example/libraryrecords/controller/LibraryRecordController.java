package com.example.libraryrecords.controller;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.exception.LibraryRecordNotFoundException;
import com.example.libraryrecords.service.LibraryRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Library Record Controller", description = "Library Records")
public class LibraryRecordController {

  private final LibraryRecordService libraryRecordService;

  /**
   * Creates a new library record in the system.
   *
   * @param libraryRecord The library record to be created.
   * @return A response entity containing the created library record with a status of 201 (Created).
   */
  @PostMapping
  @Operation(summary = "Create a new library record", description = "This operation allows you to create a new library record in the system. You can provide detailed information about the record, including title, author, ISBN, and more.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Library record created successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
  public ResponseEntity<LibraryRecordDTO> createLibraryRecord(
      @RequestBody @Valid LibraryRecordDTO libraryRecord) {
    LibraryRecordDTO createdRecord = libraryRecordService.create(libraryRecord);
    return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
  }

  /**
   * Retrieves a library record by its unique ID.
   *
   * @param id The ID of the library record to retrieve.
   * @return A response entity containing the retrieved library record with a status of 200 (OK) if found,
   * or a response with a status of 404 (Not Found) if the record does not exist.
   */
  @GetMapping("/{id}")
  @Operation(summary = "Retrieve a library record by ID", description = "This operation retrieves a library record by its unique ID. If the record exists, it will be returned.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Library record retrieved successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "404", description = "Library record not found", content = @Content)})
  public ResponseEntity<LibraryRecordDTO> getLibraryRecordById(@PathVariable @Min(1) Long id) {
    LibraryRecordDTO libraryRecord = libraryRecordService.getById(id);
    if (libraryRecord != null) {
      return new ResponseEntity<>(libraryRecord, HttpStatus.OK);
    } else {
      throw new LibraryRecordNotFoundException("Library Record not found");
    }
  }

  /**
   * Retrieves a list of all library records available in the system.
   *
   * @return A response entity containing a list of retrieved library records with a status of 200 (OK) if records
   * exist, or a response with a status of 204 (No Content) if there are no records.
   */
  @GetMapping
  @Operation(summary = "Retrieve all library records", description = "This operation retrieves a list of all library records available in the system. If there are no records, an empty list will be returned.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Library records retrieved successfully", content = {
          @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = LibraryRecordDTO.class)))}),
      @ApiResponse(responseCode = "204", description = "No library records found", content = @Content)})
  public ResponseEntity<List<LibraryRecordDTO>> getAllLibraryRecords() {
    List<LibraryRecordDTO> libraryRecords = libraryRecordService.getAll();
    if (!libraryRecords.isEmpty()) {
      return new ResponseEntity<>(libraryRecords, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  /**
   * Updates an existing library record by its ID.
   *
   * @param id            The ID of the library record to update.
   * @param libraryRecord The updated library record information.
   * @return A response entity containing the updated library record with a status of 200 (OK) if the update is successful,
   * or a response with a status of 404 (Not Found) if the record does not exist, or a response with a status of 400 (Bad Request)
   * if the request is invalid.
   */
  @PutMapping("/{id}")
  @Operation(summary = "Update a library record by ID", description = "This operation allows you to update an existing library record by its ID. If the record exists and the update is successful, the updated record will be returned.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Library record updated successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "404", description = "Library record not found", content = @Content),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
  public ResponseEntity<LibraryRecordDTO> updateLibraryRecord(@PathVariable @Min(1) Long id,
                                                              @RequestBody
                                                              @Valid LibraryRecordDTO libraryRecord) {
    LibraryRecordDTO updatedRecord = libraryRecordService.update(id, libraryRecord);
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
   * @return A response entity with a status of 204 (No Content) if the record is successfully deleted.
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a library record by ID", description = "This operation deletes a library record by its ID. If the record is successfully deleted, no content will be returned.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Library record deleted successfully")})
  public ResponseEntity<Void> deleteLibraryRecord(@PathVariable @Min(1) Long id) {
    libraryRecordService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}