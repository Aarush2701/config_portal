package com.example.authhub.controller;

import com.example.authhub.model.AuthMappingRequest;
import com.example.authhub.model.AuthMappingResponse;
import com.example.authhub.repository.AuthMappingRepository;
import com.example.authhub.service.impl.AuthHubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/authhub")
@CrossOrigin(origins = "http://localhost:4200")  // Enable Angular frontend access
public class AuthHubController {

//    @Autowired
//    private AuthMappingRepository repository;
    @Autowired
    private AuthHubServiceImpl service;

    // ✅ GET method to fetch all records
    @GetMapping
    public List<AuthMappingResponse> getAllMappings() {
        System.out.println(service.searchAll());
        return service.searchAll();
    }
    @GetMapping("/search/ID/{ID}")
    public ResponseEntity<?> searchById(@PathVariable String ID) {
        AuthMappingResponse result = service.searchById(ID);
        if (result != null) {
            return ResponseEntity.ok(Collections.singletonList(result));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No entry found for ID: " + ID);
        }
    }
    // add recors
    @PostMapping
    public ResponseEntity<Map<String, Object>> addMapping(@RequestBody AuthMappingRequest mapping) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.addMapping(mapping);
            response.put("status", "success");
            response.put("message", "Data inserted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Insert failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    //update details
    @PutMapping("/{id}")
    public AuthMappingRequest updateById(@PathVariable int id, @RequestBody AuthMappingRequest request) {
        return service.updateMapping(id, request);
    }
    // delete records
@DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable("id") int id) {
        Map<String, String> response = new HashMap<>();
     boolean deleted = service.deleteMapping(id);
            if (deleted) {
                response.put("message", "Record with ID " + id + " deleted successfully.");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "No record found with ID " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

//        try {
//            boolean deleted = service.deleteMapping(id);
//            if (deleted) {
//                response.put("message", "Record with ID " + id + " deleted successfully.");
//                return ResponseEntity.ok(response);
//            } else {
//                response.put("message", "No record found with ID " + id);
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.put("message", "Error deleting record with ID " + id);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
    }

}
