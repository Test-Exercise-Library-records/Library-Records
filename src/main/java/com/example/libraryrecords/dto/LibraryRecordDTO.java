package com.example.libraryrecords.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a library record.
 */
@Getter
@Setter
public class LibraryRecordDTO {

  /**
   * The title of the library record.
   */
  private String title;

  /**
   * The author of the library record.
   */
  private String author;
}