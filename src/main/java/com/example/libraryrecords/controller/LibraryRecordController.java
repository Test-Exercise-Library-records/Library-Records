package com.example.libraryrecords.controller;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.service.LibraryRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RestController
@RequestMapping("/library-records")
@RequiredArgsConstructor
@Tag(name = "Library Record Controller", description = "Library Records")
public class LibraryRecordController {

  private final LibraryRecordService libraryRecordService;

  @PostMapping
  @Operation(summary = "Create a new library record", description = "This operation allows you to create a new library record in the system. You can provide detailed information about the record, including title, author, ISBN, and more.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Library record created successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
  public ResponseEntity<LibraryRecordDTO> createLibraryRecord(
      @RequestBody LibraryRecordDTO libraryRecord) {
    LibraryRecordDTO createdRecord = libraryRecordService.create(libraryRecord);
    return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Retrieve a library record by ID", description = "This operation retrieves a library record by its unique ID. If the record exists, it will be returned.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Library record retrieved successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "404", description = "Library record not found", content = @Content)})
  public ResponseEntity<LibraryRecordDTO> getLibraryRecordById(@PathVariable Long id) {
    LibraryRecordDTO libraryRecord = libraryRecordService.getById(id);
    if (libraryRecord != null) {
      return new ResponseEntity<>(libraryRecord, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

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

  @PutMapping("/{id}")
  @Operation(summary = "Update a library record by ID", description = "This operation allows you to update an existing library record by its ID. If the record exists and the update is successful, the updated record will be returned.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Library record updated successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "404", description = "Library record not found", content = @Content),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
  public ResponseEntity<LibraryRecordDTO> updateLibraryRecord(@PathVariable Long id, @RequestBody
  LibraryRecordDTO libraryRecord) {
    LibraryRecordDTO updatedRecord = libraryRecordService.update(id, libraryRecord);
    if (updatedRecord != null) {
      return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a library record by ID", description = "This operation deletes a library record by its ID. If the record is successfully deleted, no content will be returned.", tags = {
      "Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Library record deleted successfully")})
  public ResponseEntity<Void> deleteLibraryRecord(@PathVariable Long id) {
    libraryRecordService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}