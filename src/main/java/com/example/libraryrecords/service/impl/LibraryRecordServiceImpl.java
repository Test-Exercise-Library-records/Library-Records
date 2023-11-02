package com.example.libraryrecords.service.impl;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.entity.LibraryRecord;
import com.example.libraryrecords.mapper.LibraryRecordMapper;
import com.example.libraryrecords.repository.LibraryRecordRepository;
import com.example.libraryrecords.service.LibraryRecordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link LibraryRecordService} interface, providing methods for managing library records.
 */
@Service
@RequiredArgsConstructor
public class LibraryRecordServiceImpl implements LibraryRecordService {
  private final LibraryRecordRepository repository;
  private final LibraryRecordMapper mapper;

  @Override
  public LibraryRecordDTO create(LibraryRecordDTO dto) {
    LibraryRecord entity = mapper.libraryRecordDTOToLibraryRecord(dto);
    LibraryRecord savedEntity = repository.save(entity);
    return mapper.libraryRecordToLibraryRecordDTO(savedEntity);
  }

  @Override
  public LibraryRecordDTO getById(Long id) {
    LibraryRecord entity = repository.findById(id).orElse(null);
    return (entity != null) ? mapper.libraryRecordToLibraryRecordDTO(entity) : null;
  }

  @Override
  public List<LibraryRecordDTO> getAll() {
    List<LibraryRecord> entities = repository.findAll();
    return entities.stream().map(mapper::libraryRecordToLibraryRecordDTO).toList();
  }

  @Override
  public LibraryRecordDTO update(Long id, LibraryRecordDTO dto) {
    LibraryRecord existingEntity = repository.findById(id).orElse(null);
    if (existingEntity != null) {
      mapper.updateLibraryRecord(dto, existingEntity);
      repository.save(existingEntity);
      return mapper.libraryRecordToLibraryRecordDTO(existingEntity);
    }
    return null;
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
