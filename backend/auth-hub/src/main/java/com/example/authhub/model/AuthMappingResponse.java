package com.example.authhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@JsonPropertyOrder({
        "ID",
        "sSERVICE_ID",
        "CLIENT_ID",
        "AUTH_SCHEME",
        "DESCRIPTION",
        "SCOPE",
        "CLIENT_CONFIG_ID",
        "REVOKE_SCHEME",
        "IS_MULTI_USER",
        "CLIENT_SALT",
        "IP_WHITELIST_FLAG",
        "IP_WHITELIST_DATA"
})
@Entity
@Table(name = "SERVICE_CLIENT_AUTH_MAPPING")
public class AuthMappingResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("ID")
    private Integer ID;

    @Column(name = "SERVICE_ID", nullable = false, length = 50)
    @JsonProperty("SERVICE_ID")
    private String SERVICE_ID;

    @Column(name = "CLIENT_ID", nullable = false, length = 20)
    @JsonProperty("CLIENT_ID")
    private String CLIENT_ID;

    @Column(name = "AUTH_SCHEME", length = 20)
    @JsonProperty("AUTH_SCHEME")
    private String AUTH_SCHEME;

    @Column(name = "DESCRIPTION", length = 500)
    @JsonProperty("DESCRIPTION")
    private String DESCRIPTION;

    @Column(name = "SCOPE", length = 100)
    @JsonProperty("SCOPE")
    private String SCOPE;

    @Column(name = "CLIENT_CONFIG_ID")
    @JsonProperty("CLIENT_CONFIG_ID")
    private Integer CLIENT_CONFIG_ID;

    @Column(name = "REVOKE_SCHEME", length = 20)
    @JsonProperty("REVOKE_SCHEME")
    private String REVOKE_SCHEME;

    @Column(name = "IS_MULTI_USER")
    @JsonProperty("IS_MULTI_USER")
    private Integer IS_MULTI_USER;

    @Column(name = "CLIENT_SALT", length = 20)
    @JsonProperty("CLIENT_SALT")
    private String CLIENT_SALT;

    @Column(name = "IP_WHITELIST_FLAG")
    @JsonProperty("IP_WHITELIST_FLAG")
    private Integer IP_WHITELIST_FLAG;

    @Column(name = "IP_WHITELIST_DATA", length = 500)
    @JsonProperty("IP_WHITELIST_DATA")
    private String IP_WHITELIST_DATA;

    // --- Getters and Setters ---

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(String SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public String getCLIENT_ID() {
        return CLIENT_ID;
    }

    public void setCLIENT_ID(String CLIENT_ID) {
        this.CLIENT_ID = CLIENT_ID;
    }

    public String getAUTH_SCHEME() {
        return AUTH_SCHEME;
    }

    public void setAUTH_SCHEME(String AUTH_SCHEME) {
        this.AUTH_SCHEME = AUTH_SCHEME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getSCOPE() {
        return SCOPE;
    }

    public void setSCOPE(String SCOPE) {
        this.SCOPE = SCOPE;
    }

    public Integer getCLIENT_CONFIG_ID() {
        return CLIENT_CONFIG_ID;
    }

    public void setCLIENT_CONFIG_ID(Integer CLIENT_CONFIG_ID) {
        this.CLIENT_CONFIG_ID = CLIENT_CONFIG_ID;
    }

    public String getREVOKE_SCHEME() {
        return REVOKE_SCHEME;
    }

    public void setREVOKE_SCHEME(String REVOKE_SCHEME) {
        this.REVOKE_SCHEME = REVOKE_SCHEME;
    }

    public Integer getIS_MULTI_USER() {
        return IS_MULTI_USER;
    }

    public void setIS_MULTI_USER(Integer IS_MULTI_USER) {
        this.IS_MULTI_USER = IS_MULTI_USER;
    }

    public String getCLIENT_SALT() {
        return CLIENT_SALT;
    }

    public void setCLIENT_SALT(String CLIENT_SALT) {
        this.CLIENT_SALT = CLIENT_SALT;
    }

    public Integer getIP_WHITELIST_FLAG() {
        return IP_WHITELIST_FLAG;
    }

    public void setIP_WHITELIST_FLAG(Integer IP_WHITELIST_FLAG) {
        this.IP_WHITELIST_FLAG = IP_WHITELIST_FLAG;
    }

    public String getIP_WHITELIST_DATA() {
        return IP_WHITELIST_DATA;
    }

    public void setIP_WHITELIST_DATA(String IP_WHITELIST_DATA) {
        this.IP_WHITELIST_DATA = IP_WHITELIST_DATA;
    }
}