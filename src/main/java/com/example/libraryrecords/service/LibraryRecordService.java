package com.example.libraryrecords.service;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import java.util.List;

public interface LibraryRecordService {

  LibraryRecordDTO create(LibraryRecordDTO dto);

  LibraryRecordDTO getById(Long id);

  List<LibraryRecordDTO> getAll();

  LibraryRecordDTO update(Long id, LibraryRecordDTO dto);

  void delete(Long id);
}