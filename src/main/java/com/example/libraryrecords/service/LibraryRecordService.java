package com.example.libraryrecords.service;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import java.util.List;

/**
 * Defines the service for managing library records in the system.
 */
public interface LibraryRecordService {

  /**
   * Creates a new library record based on the provided DTO.
   *
   * @param dto The DTO representing the library record to be created.
   * @return The DTO of the created library record.
   */
  LibraryRecordDTO create(LibraryRecordDTO dto);

  /**
   * Retrieves a library record by its unique ID.
   *
   * @param id The ID of the library record to retrieve.
   * @return The DTO of the retrieved library record, or null if not found.
   */
  LibraryRecordDTO getById(Long id);

  /**
   * Retrieves a list of all available library records.
   *
   * @return A list of DTOs representing all library records in the system.
   */
  List<LibraryRecordDTO> getAll();

  /**
   * Updates an existing library record with the provided DTO.
   *
   * @param id  The ID of the library record to update.
   * @param dto The DTO containing the updated information.
   * @return The DTO of the updated library record, or null if not found.
   */
  LibraryRecordDTO update(Long id, LibraryRecordDTO dto);

  /**
   * Deletes a library record by its unique ID.
   *
   * @param id The ID of the library record to delete.
   */
  void delete(Long id);
}