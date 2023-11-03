package com.example.libraryrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The main application class for the Library Records microservice.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LibraryRecordsApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryRecordsApplication.class, args);
  }

}
