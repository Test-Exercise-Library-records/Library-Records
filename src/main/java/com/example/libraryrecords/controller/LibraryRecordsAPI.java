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

/**
 * Library records OpenAPI description.
 */
@Tag(name = "Library Record Controller", description = "Library Records")
public interface LibraryRecordsAPI {

  /**
   * Creates a new library record in the system.
   *
   * @param libraryRecord The library record to be created.
   * @return A response entity containing the created library record with a status of 201 (Created).
   */
  @Operation(summary = "Create a new library record",
      description = "This operation allows you to create a new library record in the system. You can provide detailed information about the record, including title, author, ISBN, and more.",
      tags = {"Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Library record created successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = LibraryRecordDTO.class))}),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
  ResponseEntity<LibraryRecordDTO> createLibraryRecord(LibraryRecordDTO libraryRecord);

  /**
   * Retrieves a library record by its unique ID.
   *
   * @param id The ID of the library record to retrieve.
   * @return A response entity containing the retrieved library record with a status of 200 (OK) if
   *         found, or a response with a status of 404 (Not Found) if the record does not exist.
   */
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

  /**
   * Retrieves a list of all library records available in the system.
   *
   * @return A response entity containing a list of retrieved library records with a status of 200
   *         (OK) if records exist, or a response with a status of 204 (No Content) if there are no
   *         records.
   */
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

  /**
   * Updates an existing library record by its ID.
   *
   * @param id The ID of the library record to update.
   * @param libraryRecord The updated library record information.
   * @return A response entity containing the updated library record with a status of 200 (OK) if
   *         the update is successful, or a response with a status of 404 (Not Found) if the record
   *         does not exist, or a response with a status of 400 (Bad Request) if the request is
   *         invalid.
   */
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

  /**
   * Deletes a library record by its ID.
   *
   * @param id The ID of the library record to delete.
   * @return A response entity with a status of 204 (No Content) if the record is successfully
   *         deleted.
   */
  @Operation(summary = "Delete a library record by ID",
      description = "This operation deletes a library record by its ID. If the record is successfully deleted, no content will be returned.",
      tags = {"Library Records"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Library record deleted successfully")})
  ResponseEntity<Void> deleteLibraryRecord(Long id);
}
