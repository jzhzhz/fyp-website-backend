package com.fyp.websitebackend.csweb.domain;

public class Label {
    private Integer id;

    private String label;

    private String url;

    private Integer deprecated;

    private String type;

    private String codeContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Integer deprecated) {
        this.deprecated = deprecated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent == null ? null : codeContent.trim();
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", url='" + url + '\'' +
                ", deprecated=" + deprecated +
                ", type='" + type + '\'' +
                ", codeContent='" + codeContent + '\'' +
                '}';
    }
}