package com.example.authhub.service.impl;

import com.example.authhub.model.AuthMappingRequest;
import com.example.authhub.model.AuthMappingResponse;
import com.example.authhub.repository.AuthMappingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AuthHubServiceImplTest {

    @InjectMocks
    private AuthHubServiceImpl service;

    @Mock
    private AuthMappingRepository repository;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchAll_ReturnsList() {
        List<AuthMappingResponse> mockList = List.of(new AuthMappingResponse(), new AuthMappingResponse());
        when(repository.findAll()).thenReturn(mockList);

        List<AuthMappingResponse> result = service.searchAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSearchAll_DoesNotReturnsList() {
        when(repository.findAll()).thenReturn(null);

        List<AuthMappingResponse> result = service.searchAll();

        assertNull(result);
        assertEquals( null,result);
    }

    @Test
    void testSearchById_Success(){
        String ID = "1";
        AuthMappingResponse mockResponse = new AuthMappingResponse();
        mockResponse.setID(1);
        mockResponse.setSERVICE_ID("Hello");
        mockResponse.setCLIENT_ID("Hello");
        mockResponse.setIS_MULTI_USER(0);
        mockResponse.setIP_WHITELIST_FLAG(0);

        when(repository.findById(ID)).thenReturn(mockResponse);

        AuthMappingResponse result = service.searchById(ID);
        assertNotNull(result);
        assertEquals(1,result.getID());
    }

    @Test
    void testSearchById_UserNotFound(){
        String ID = "1";
        AuthMappingResponse mockResponse = new AuthMappingResponse();

        when(repository.findById(ID)).thenReturn(null);

        AuthMappingResponse result = service.searchById(ID);
        assertNull(result);
    }

    @Test
    void testAddMapping_Success(){
        AuthMappingRequest request = new AuthMappingRequest();
        when(repository.insert(request)).thenReturn(1);

        boolean result = service.addMapping(request);

        assertTrue(result);
        verify(repository, times(1)).insert(request);
    }

    @Test
    void testAddMapping_Fail(){
        AuthMappingRequest request = new AuthMappingRequest();
        when(repository.insert(request)).thenReturn(0);

        boolean result = service.addMapping(request);

        assertFalse(result);
    }

    @Test
    void testUpdateMapping_Success(){
        AuthMappingRequest newData = new AuthMappingRequest();
        when(repository.updateMapping(any(AuthMappingRequest.class))).thenReturn(1);
        AuthMappingRequest result = service.updateMapping(10, newData);

        assertNotNull(result);
        assertEquals(newData, result);
    }

    @Test
    void testUpdateMapping_Fail(){
        AuthMappingRequest newData = new AuthMappingRequest();
        when(repository.updateMapping(any(AuthMappingRequest.class))).thenReturn(0);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.updateMapping(10, newData);
        });

        assertEquals("Update failed for ID: 10", exception.getMessage());
    }

    @Test
    void testDeleteMapping_Success() {
        when(repository.deleteById(5)).thenReturn(1);

        boolean result = service.deleteMapping(5);

        assertTrue(result);
        verify(repository, times(1)).deleteById(5);
    }

    @Test
    void testDeleteMapping_Fail() {
        when(repository.deleteById(5)).thenReturn(0);

        boolean result = service.deleteMapping(5);

        assertFalse(result);
    }


}
