package com.example.authhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;

@JsonPropertyOrder({
        "authField1", "authField2", "authField3", "authField4", "authField5",
        "authField6", "authField7", "authField8", "authField9", "authField10",
        "authField11", "authField12"
})
@Entity
@Table(name = "SERVICE_CLIENT_AUTH_MAPPING")
public class AuthMappingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("authField1")
    private Integer authField1;

    @Column(name = "SERVICE_ID")
    @JsonProperty("authField2")
    private String authField2;

    @Column(name = "CLIENT_ID")
    @JsonProperty("authField3")
    private String authField3;

    @Column(name = "AUTH_SCHEME")
    @JsonProperty("authField4")
    private String authField4;

    @Column(name = "DESCRIPTION")
    @JsonProperty("authField5")
    private String authField5;

    @Column(name = "SCOPE")
    @JsonProperty("authField6")
    private String authField6;

    @Column(name = "CLIENT_CONFIG_ID")
    @JsonProperty("authField7")
    private Integer authField7;

    @Column(name = "REVOKE_SCHEME")
    @JsonProperty("authField8")
    private String authField8;

    @Column(name = "IS_MULTI_USER")
    @JsonProperty("authField9")
    private Integer authField9;

    @Column(name = "CLIENT_SALT")
    @JsonProperty("authField10")
    private String authField10;

    @Column(name = "IP_WHITELIST_FLAG")
    @JsonProperty("authField11")
    private Integer authField11;

    @Column(name = "IP_WHITELIST_DATA")
    @JsonProperty("authField12")
    private String authField12;

    // ✅ Getters & Setters

    public Integer getAuthField1() {
        return authField1;
    }

    public void setAuthField1(Integer authField1) {
        this.authField1 = authField1;
    }

    public String getAuthField2() {
        return authField2;
    }

    public void setAuthField2(String authField2) {
        this.authField2 = authField2;
    }

    public String getAuthField3() {
        return authField3;
    }

    public void setAuthField3(String authField3) {
        this.authField3 = authField3;
    }

    public String getAuthField4() {
        return authField4;
    }

    public void setAuthField4(String authField4) {
        this.authField4 = authField4;
    }

    public String getAuthField5() {
        return authField5;
    }

    public void setAuthField5(String authField5) {
        this.authField5 = authField5;
    }

    public String getAuthField6() {
        return authField6;
    }

    public void setAuthField6(String authField6) {
        this.authField6 = authField6;
    }

    public Integer getAuthField7() {
        return authField7;
    }

    public void setAuthField7(Integer authField7) {
        this.authField7 = authField7;
    }

    public String getAuthField8() {
        return authField8;
    }

    public void setAuthField8(String authField8) {
        this.authField8 = authField8;
    }

    public Integer getAuthField9() {
        return authField9;
    }

    public void setAuthField9(Integer authField9) {
        this.authField9 = authField9;
    }

    public String getAuthField10() {
        return authField10;
    }

    public void setAuthField10(String authField10) {
        this.authField10 = authField10;
    }

    public Integer getAuthField11() {
        return authField11;
    }

    public void setAuthField11(Integer authField11) {
        this.authField11 = authField11;
    }

    public String getAuthField12() {
        return authField12;
    }

    public void setAuthField12(String authField12) {
        this.authField12 = authField12;
    }
}

