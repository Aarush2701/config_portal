package com.example.authhub.controller;

import com.example.authhub.model.AuthMappingRequest;
import com.example.authhub.model.AuthMappingResponse;
import com.example.authhub.service.impl.AuthHubServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthHubController.class)
class AuthHubControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthHubServiceImpl service;

    private ObjectMapper objectMapper;
    private AuthMappingRequest sampleRequest;
    private AuthMappingResponse sampleResponse;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();

        sampleRequest = new AuthMappingRequest();
        sampleRequest.setAuthField1(1); // assuming 1 = ID

        sampleResponse = new AuthMappingResponse();
        // set fields if necessary
    }

    @Test
    void testGetAllMappings_ReturnsList() throws Exception {
        List<AuthMappingResponse> mockList = List.of(new AuthMappingResponse(), new AuthMappingResponse());
        Mockito.when(service.searchAll()).thenReturn(mockList);

        mockMvc.perform(get("/authhub"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testSearchById_Found() throws Exception {
        Mockito.when(service.searchById("abc123")).thenReturn(sampleResponse);

        mockMvc.perform(get("/authhub/search/ID/abc123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testSearchById_NotFound() throws Exception {
        Mockito.when(service.searchById("notfound")).thenReturn(null);

        mockMvc.perform(get("/authhub/search/ID/notfound"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No entry found for ID: notfound"));
    }

    @Test
    void testAddMapping_Success() throws Exception {
        Mockito.when(service.addMapping(any(AuthMappingRequest.class))).thenReturn(true);

        mockMvc.perform(post("/authhub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    void testAddMapping_Failure() throws Exception {
        Mockito.when(service.addMapping(any(AuthMappingRequest.class))).thenThrow(new RuntimeException("DB Error"));

        mockMvc.perform(post("/authhub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value("error"));
    }

    @Test
    void testUpdateMapping_Success() throws Exception {
        Mockito.when(service.updateMapping(eq(1), any(AuthMappingRequest.class))).thenReturn(sampleRequest);

        mockMvc.perform(put("/authhub/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authField1").value(1));
    }

    @Test
    void testDeleteMapping_Success() throws Exception {
        Mockito.when(service.deleteMapping(1)).thenReturn(true);

        mockMvc.perform(delete("/authhub/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Record with ID 1 deleted successfully."));
    }

    @Test
    void testDeleteMapping_NotFound() throws Exception {
        Mockito.when(service.deleteMapping(99)).thenReturn(false);

        mockMvc.perform(delete("/authhub/delete/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No record found with ID 99"));
    }
}
