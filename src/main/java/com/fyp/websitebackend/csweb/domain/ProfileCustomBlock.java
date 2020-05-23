package com.fyp.websitebackend.csweb.domain;

public class ProfileCustomBlock {
    private Integer id;

    private String username;

    private String dateBar;

    private String codeSegment;

    private String type;

    private Integer deprecated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDateBar() {
        return dateBar;
    }

    public void setDateBar(String dateBar) {
        this.dateBar = dateBar == null ? null : dateBar.trim();
    }

    public String getCodeSegment() {
        return codeSegment;
    }

    public void setCodeSegment(String codeSegment) {
        this.codeSegment = codeSegment == null ? null : codeSegment.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Integer deprecated) {
        this.deprecated = deprecated;
    }
}