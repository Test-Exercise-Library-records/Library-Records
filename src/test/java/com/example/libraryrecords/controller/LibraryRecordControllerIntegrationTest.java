package com.example.libraryrecords.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.libraryrecords.dto.LibraryRecordDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LibraryRecordControllerIntegrationTest {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private static final String TITLE = "testTitle";
  private static final String AUTHOR = "testAuthor";
  private static final long RECORD_ID = 1L;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(0)
  void testGetAllLibraryRecords_retrieveAll_NoContentAndResponseIsEmpty() throws Exception {
    mockMvc.perform(get("/library-records")).andExpect(status().isNoContent());
  }

  @Test
  @Order(1)
  void testCreateLibraryRecord_create_CreatedAndFieldValueIsValid() throws Exception {
    LibraryRecordDTO inputRecord = new LibraryRecordDTO();
    inputRecord.setTitle(TITLE);
    inputRecord.setAuthor(AUTHOR);

    mockMvc.perform(post("/library-records").contentType(MediaType.APPLICATION_JSON)
            .content(OBJECT_MAPPER.writeValueAsString(inputRecord))).andExpect(status().isCreated())
        .andExpect(jsonPath("$.title").value(TITLE));
  }

  @Test
  @Order(2)
  void testGetLibraryRecordById_retrieveOne_OkAndFieldValueIsValid() throws Exception {
    Long recordId = RECORD_ID;
    mockMvc.perform(get("/library-records/{id}", recordId)).andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value(TITLE));
  }

  @Test
  @Order(3)
  void testGetAllLibraryRecords_retrieveAll_OkAndArrayContainsExpectedRecord() throws Exception {
    mockMvc.perform(get("/library-records")).andExpect(status().isOk())
        .andExpect(jsonPath("$.*").isArray())
        .andExpect(jsonPath("$.*", hasSize(1)))
        .andExpect(jsonPath("$.[0].title").value(TITLE));
  }

  @Test
  @Order(4)
  void testUpdateLibraryRecord_update_OkAndValueIsUpdated() throws Exception {
    LibraryRecordDTO updatedRecord = new LibraryRecordDTO();
    String updatedTitle = "updatedTitle";
    updatedRecord.setTitle(updatedTitle);
    updatedRecord.setAuthor(AUTHOR);

    mockMvc.perform(put("/library-records/{id}", RECORD_ID).contentType(MediaType.APPLICATION_JSON)
            .content(OBJECT_MAPPER.writeValueAsString(updatedRecord))).andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value(updatedTitle));
  }

  @Test
  @Order(5)
  void testDeleteLibraryRecord_delete_noContentAndRecordIsDeleted() throws Exception {
    mockMvc.perform(delete("/library-records/{id}", RECORD_ID)).andExpect(status().isNoContent());
  }

  @Test
  @Order(6)
  void testGetAllLibraryRecords_deleted_noContentAndResponseIsEmpty() throws Exception {
    mockMvc.perform(get("/library-records")).andExpect(status().isNoContent());
  }
}