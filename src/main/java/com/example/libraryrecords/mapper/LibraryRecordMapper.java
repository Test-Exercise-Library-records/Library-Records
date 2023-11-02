package com.example.libraryrecords.mapper;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.entity.LibraryRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LibraryRecordMapper {

  LibraryRecordDTO libraryRecordToLibraryRecordDTO(LibraryRecord libraryRecord);

  LibraryRecord libraryRecordDTOToLibraryRecord(LibraryRecordDTO libraryRecordDTO);

  @Mapping(target = "id", ignore = true)
  LibraryRecord updateLibraryRecord(LibraryRecordDTO libraryRecordDTO,
                                    @MappingTarget LibraryRecord libraryRecord);
}