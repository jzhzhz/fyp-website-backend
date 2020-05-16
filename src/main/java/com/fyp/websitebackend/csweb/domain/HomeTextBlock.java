package com.fyp.websitebackend.csweb.domain;

public class HomeTextBlock {
    private Integer id;

    private String title;

    private String content;

    private String url;

    private String type;

    private Integer deprecated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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