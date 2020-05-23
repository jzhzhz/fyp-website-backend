package com.fyp.websitebackend.csweb.controller.param;

public class UpdateProfileCustomParam {
    private int id;

    private String username;

    private String dateBar;

    private String codeSegment;

    private String type = "news";

    private int deprecated = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateBar() {
        return dateBar;
    }

    public void setDateBar(String dateBar) {
        this.dateBar = dateBar;
    }

    public String getCodeSegment() {
        return codeSegment;
    }

    public void setCodeSegment(String codeSegment) {
        this.codeSegment = codeSegment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(int deprecated) {
        this.deprecated = deprecated;
    }
}
