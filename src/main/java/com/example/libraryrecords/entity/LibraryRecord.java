package com.example.libraryrecords.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a library record.
 */
@Entity
@Getter
@Setter
@Table(name = "library_records")
public class LibraryRecord {

  /**
   * Unique identifier for the library record.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The title of the library record.
   */
  private String title;

  /**
   * The author of the library record.
   */
  private String author;
}
