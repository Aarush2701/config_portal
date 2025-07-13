package com.example.authhub.services;

import com.example.authhub.model.AuthMappingRequest;
import com.example.authhub.model.AuthMappingResponse;


public interface AuthHubServices {


    public boolean addMapping(AuthMappingRequest mapping);

    public AuthMappingResponse searchById(String ID);

    public AuthMappingRequest updateMapping(int ID, AuthMappingRequest newData);

    public boolean deleteMapping(int id);


}




