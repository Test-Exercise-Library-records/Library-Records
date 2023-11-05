package com.example.libraryrecords.service.impl;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.entity.LibraryRecord;
import com.example.libraryrecords.mapper.LibraryRecordMapper;
import com.example.libraryrecords.repository.LibraryRecordsRepository;
import com.example.libraryrecords.service.LibraryRecordsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link LibraryRecordsService} interface, providing methods for managing
 * library records.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryRecordsServiceImpl implements LibraryRecordsService {
  private final LibraryRecordsRepository repository;
  private final LibraryRecordMapper mapper;

  @Override
  public LibraryRecordDTO create(LibraryRecordDTO dto) {
    LibraryRecord entity = mapper.libraryRecordDTOToLibraryRecord(dto);
    LibraryRecord savedEntity = repository.save(entity);
    log.debug("New record id {} is created", entity.getId());
    return mapper.libraryRecordToLibraryRecordDTO(savedEntity);
  }

  @Override
  public LibraryRecordDTO getById(Long id) {
    LibraryRecord entity = repository.findById(id).orElse(null);
    log.debug("Record id {} is retrieved successfully", id);
    return (entity != null) ? mapper.libraryRecordToLibraryRecordDTO(entity) : null;
  }

  @Override
  public List<LibraryRecordDTO> getAll() {
    List<LibraryRecord> entities = repository.findAll();
    log.debug("All records are retrieved");
    return entities.stream().map(mapper::libraryRecordToLibraryRecordDTO).toList();
  }

  @Override
  public LibraryRecordDTO update(Long id, LibraryRecordDTO dto) {
    LibraryRecord existingEntity = repository.findById(id).orElse(null);
    if (existingEntity != null) {
      mapper.updateLibraryRecord(dto, existingEntity);
      repository.save(existingEntity);
      log.debug("Record id {} is updated", id);
      return mapper.libraryRecordToLibraryRecordDTO(existingEntity);
    }
    log.debug("Record id {} is not found for update", id);
    return null;
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
    log.debug("Record id {} is deleted", id);
  }
}
