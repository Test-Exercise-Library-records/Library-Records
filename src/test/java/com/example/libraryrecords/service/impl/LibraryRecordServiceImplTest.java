package com.example.libraryrecords.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.example.libraryrecords.entity.LibraryRecord;
import com.example.libraryrecords.mapper.LibraryRecordMapper;
import com.example.libraryrecords.repository.LibraryRecordRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class LibraryRecordServiceImplTest {

  private static final String TITLE = "testTitle";
  private static final String AUTHOR = "testAuthor";
  private static final long RECORD_ID = 1L;

  @Autowired
  private LibraryRecordServiceImpl libraryRecordService;
  @MockBean
  private LibraryRecordRepository repository;
  @MockBean
  private LibraryRecordMapper mapper;

  @Test
  void testCreate() {
    LibraryRecordDTO inputDto = new LibraryRecordDTO();
    inputDto.setTitle(TITLE);
    inputDto.setAuthor(AUTHOR);
    LibraryRecord entity = new LibraryRecord();
    entity.setId(RECORD_ID);
    LibraryRecord savedEntity = new LibraryRecord();
    savedEntity.setId(RECORD_ID);

    when(mapper.libraryRecordDTOToLibraryRecord(inputDto)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(savedEntity);
    when(mapper.libraryRecordToLibraryRecordDTO(savedEntity)).thenReturn(inputDto);

    LibraryRecordDTO createdDto = libraryRecordService.create(inputDto);

    assertEquals(TITLE, createdDto.getTitle());
    assertEquals(AUTHOR, createdDto.getAuthor());
  }

  @Test
  void testGetById() {
    LibraryRecord entity = new LibraryRecord();
    entity.setId(RECORD_ID);
    entity.setTitle(TITLE);
    entity.setAuthor(AUTHOR);
    LibraryRecordDTO libraryRecordDTO = new LibraryRecordDTO();
    libraryRecordDTO.setTitle(TITLE);
    libraryRecordDTO.setAuthor(AUTHOR);

    when(repository.findById(RECORD_ID)).thenReturn(Optional.of(entity));
    when(mapper.libraryRecordToLibraryRecordDTO(entity)).thenReturn(libraryRecordDTO);

    LibraryRecordDTO retrievedDto = libraryRecordService.getById(RECORD_ID);

    assertEquals(AUTHOR, retrievedDto.getAuthor());
    assertEquals(TITLE, retrievedDto.getTitle());
  }

  @Test
  void testGetById_NotFound() {
    when(repository.findById(RECORD_ID)).thenReturn(Optional.empty());

    LibraryRecordDTO retrievedDto = libraryRecordService.getById(RECORD_ID);

    assertNull(retrievedDto);
  }

  @Test
  void testGetAll() {
    List<LibraryRecord> entities = Collections.singletonList(new LibraryRecord());

    when(repository.findAll()).thenReturn(entities);
    when(mapper.libraryRecordToLibraryRecordDTO(any(LibraryRecord.class)))
        .thenReturn(new LibraryRecordDTO());

    List<LibraryRecordDTO> retrievedDtos = libraryRecordService.getAll();

    assertEquals(entities.size(), retrievedDtos.size());
  }

  @Test
  void testUpdate() {
    LibraryRecordDTO inputDto = new LibraryRecordDTO();
    inputDto.setAuthor(AUTHOR);
    inputDto.setTitle(TITLE);
    LibraryRecord existingEntity = new LibraryRecord();
    existingEntity.setId(RECORD_ID);
    existingEntity.setAuthor(AUTHOR);
    existingEntity.setTitle(TITLE);

    when(repository.findById(RECORD_ID)).thenReturn(Optional.of(existingEntity));
    when(mapper.updateLibraryRecord(inputDto, existingEntity)).thenReturn(existingEntity);
    when(repository.save(existingEntity)).thenReturn(existingEntity);
    when(mapper.libraryRecordToLibraryRecordDTO(existingEntity)).thenReturn(inputDto);

    LibraryRecordDTO updatedDto = libraryRecordService.update(RECORD_ID, inputDto);

    assertEquals(AUTHOR, updatedDto.getAuthor());
    assertEquals(TITLE, updatedDto.getTitle());
  }

  @Test
  void testUpdate_NotFound() {
    when(repository.findById(RECORD_ID)).thenReturn(Optional.empty());

    LibraryRecordDTO updatedDto = libraryRecordService.update(RECORD_ID, new LibraryRecordDTO());

    assertNull(updatedDto);
  }

  @Test
  void testDelete() {
    libraryRecordService.delete(RECORD_ID);

    verify(repository, times(1)).deleteById(RECORD_ID);
  }

}