package com.example.libraryrecords.controller;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Library Record Controller", description = "Library Records")
public interface LibraryRecordsAPI {
  @Operation(summary = "Create a new library record",
      description = "This operation allows you to create a new library record in the system. You can provide detailed information about the record, including title, author, ISBN, and more.",
      tags = {"Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Library record created successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
  ResponseEntity<LibraryRecordDTO> createLibraryRecord(LibraryRecordDTO libraryRecord);

  @Operation(summary = "Retrieve a library record by ID",
      description = "This operation retrieves a library record by its unique ID. If the record exists, it will be returned.",
      tags = {"Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Library record retrieved successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "404", description = "Library record not found",
          content = @Content)})
  ResponseEntity<LibraryRecordDTO> getLibraryRecordById(Long id);

  @Operation(summary = "Retrieve all library records",
      description = "This operation retrieves a list of all library records available in the system. If there are no records, an empty list will be returned.",
      tags = {"Library Records"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Library records retrieved successfully",
              content = {@Content(mediaType = "application/json",
                  array = @ArraySchema(
                      schema = @Schema(implementation = LibraryRecordDTO.class)))}),
          @ApiResponse(responseCode = "204", description = "No library records found",
              content = @Content)})
  ResponseEntity<List<LibraryRecordDTO>> getAllLibraryRecords();

  @Operation(summary = "Update a library record by ID",
      description = "This operation allows you to update an existing library record by its ID. If the record exists and the update is successful, the updated record will be returned.",
      tags = {"Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Library record updated successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "404", description = "Library record not found",
          content = @Content),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
  ResponseEntity<LibraryRecordDTO> updateLibraryRecord(Long id, LibraryRecordDTO libraryRecord);

  @Operation(summary = "Delete a library record by ID",
      description = "This operation deletes a library record by its ID. If the record is successfully deleted, no content will be returned.",
      tags = {"Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Library record deleted successfully")})
  ResponseEntity<Void> deleteLibraryRecord(Long id);
}
