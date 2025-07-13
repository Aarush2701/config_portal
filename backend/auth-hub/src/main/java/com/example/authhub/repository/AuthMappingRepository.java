package com.example.authhub.repository;

import com.example.authhub.model.AuthMappingRequest;
import com.example.authhub.model.AuthMappingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AuthMappingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AuthMappingResponse> findAll() {
        String sql = "SELECT * FROM SERVICE_CLIENT_AUTH_MAPPING";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AuthMappingResponse.class));
    }
    public AuthMappingResponse findById(String ID) {
        String sql = "SELECT * FROM SERVICE_CLIENT_AUTH_MAPPING WHERE ID = ?";
        List<AuthMappingResponse> results = jdbcTemplate.query(
                sql,
                new Object[]{ID},
                new BeanPropertyRowMapper<>(AuthMappingResponse.class)
        );
        return results.isEmpty() ? null : results.get(0);
    }
    public int insert(AuthMappingRequest mapping) {
        String sql = "INSERT INTO SERVICE_CLIENT_AUTH_MAPPING (ID, SERVICE_ID, CLIENT_ID, AUTH_SCHEME, DESCRIPTION, SCOPE, CLIENT_CONFIG_ID, REVOKE_SCHEME, IS_MULTI_USER, CLIENT_SALT, IP_WHITELIST_FLAG, IP_WHITELIST_DATA) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                mapping.getAuthField1(),
                mapping.getAuthField2(),
                mapping.getAuthField3(),
                mapping.getAuthField4(),
                mapping.getAuthField5(),
                mapping.getAuthField6(),
                mapping.getAuthField7(),
                mapping.getAuthField8(),
                mapping.getAuthField9(),
                mapping.getAuthField10(),
                mapping.getAuthField11(),
                mapping.getAuthField12()
        );
    }
    public int updateMapping(AuthMappingRequest mapping) {
        String sql = "UPDATE SERVICE_CLIENT_AUTH_MAPPING SET " +
                "SERVICE_ID = ?, CLIENT_ID = ?, AUTH_SCHEME = ?, " +
                "DESCRIPTION = ?, SCOPE = ?, CLIENT_CONFIG_ID = ?, " +
                "REVOKE_SCHEME = ?, IS_MULTI_USER = ?, CLIENT_SALT = ?, " +
                "IP_WHITELIST_FLAG = ?, IP_WHITELIST_DATA = ? " +
                "WHERE ID = ?";
        int rows = jdbcTemplate.update(sql,
                mapping.getAuthField2(),
                mapping.getAuthField3(),
                mapping.getAuthField4(),
                mapping.getAuthField5(),
                mapping.getAuthField6(),
                mapping.getAuthField7(),
                mapping.getAuthField8(),
                mapping.getAuthField9(),
                mapping.getAuthField10(),
                mapping.getAuthField11(),
                mapping.getAuthField12(),
                mapping.getAuthField1()
        );
        System.out.println("Rows updated: " + rows);
        return rows;
    }
    public int deleteById(int id) {
        String sql = "DELETE FROM SERVICE_CLIENT_AUTH_MAPPING WHERE ID = ?";
        return jdbcTemplate.update(sql, id);
    }
}


