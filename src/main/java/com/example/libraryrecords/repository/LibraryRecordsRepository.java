package com.example.libraryrecords.repository;

import com.example.libraryrecords.entity.LibraryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing library records in the database.
 */
@Repository
public interface LibraryRecordsRepository extends JpaRepository<LibraryRecord, Long> {

}
