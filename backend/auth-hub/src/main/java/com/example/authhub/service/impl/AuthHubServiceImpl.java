package com.example.authhub.service.impl;

import com.example.authhub.model.AuthMappingRequest;
import com.example.authhub.model.AuthMappingResponse;
import com.example.authhub.repository.AuthMappingRepository;
import com.example.authhub.services.AuthHubServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthHubServiceImpl implements AuthHubServices {

    @Autowired
    private AuthMappingRepository repository;


    public List<AuthMappingResponse> searchAll() {
        return repository.findAll();
    }

    @Override
    public AuthMappingResponse searchById(String ID) {
        return repository.findById(ID);
    }

    @Override
    public boolean addMapping(AuthMappingRequest mapping) {
        int result = repository.insert(mapping);
        return result > 0;
    }

    @Override
    public AuthMappingRequest updateMapping(int ID, AuthMappingRequest newData) {
        newData.setAuthField1(ID);  // Set ID inside the object

        int result = repository.updateMapping(newData);  // Pass single object

        if (result > 0) {
            return newData;
        } else {
            throw new RuntimeException("Update failed for ID: " + ID);
        }
    }

    @Override
    public boolean deleteMapping(int id) {
        int result = repository.deleteById(id);
        return result > 0;
    }
}





