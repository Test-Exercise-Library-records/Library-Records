package com.example.libraryrecords.dto;

import jakarta.validation.constraints.NotBlank;
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
  @NotBlank(message = "Book title is mandatory")
  private String title;

  /**
   * The author of the library record.
   */
  @NotBlank(message = "Book author is mandatory")
  private String author;
}
