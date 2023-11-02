package com.example.libraryrecords.mapper;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.entity.LibraryRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * For converting between {@link LibraryRecord} entities and their corresponding
 * Data Transfer Objects (DTOs).
 */
@Mapper(componentModel = "spring")
public interface LibraryRecordMapper {

  /**
   * Converts a {@link LibraryRecord} entity to a {@link LibraryRecordDTO} Data Transfer Object.
   *
   * @param libraryRecord The source LibraryRecord entity to convert.
   * @return The corresponding LibraryRecordDTO.
   */
  LibraryRecordDTO libraryRecordToLibraryRecordDTO(LibraryRecord libraryRecord);

  /**
   * Converts a {@link LibraryRecordDTO} Data Transfer Object to a {@link LibraryRecord} entity.
   *
   * @param libraryRecordDTO The source LibraryRecordDTO to convert.
   * @return The corresponding LibraryRecord entity.
   */
  LibraryRecord libraryRecordDTOToLibraryRecord(LibraryRecordDTO libraryRecordDTO);

  /**
   * Updates a {@link LibraryRecord} entity with values from a {@link LibraryRecordDTO}.
   *
   * @param libraryRecordDTO The source LibraryRecordDTO with updated values.
   * @param libraryRecord    The target LibraryRecord entity to update.
   * @return The corresponding LibraryRecord entity.
   */
  @Mapping(target = "id", ignore = true)
  LibraryRecord updateLibraryRecord(LibraryRecordDTO libraryRecordDTO,
                                    @MappingTarget LibraryRecord libraryRecord);
}