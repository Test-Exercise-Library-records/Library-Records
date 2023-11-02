package com.example.libraryrecords.repository;

import com.example.libraryrecords.entity.LibraryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRecordRepository extends JpaRepository<LibraryRecord, Long> {

}