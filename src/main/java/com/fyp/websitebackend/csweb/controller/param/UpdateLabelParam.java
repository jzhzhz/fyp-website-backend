package com.fyp.websitebackend.csweb.controller.param;

public class UpdateLabelParam {
    private int id;

    private String label;

    private String url;

    private String codeContent;

    private int deprecated = 0;

    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public int getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(int deprecated) {
        this.deprecated = deprecated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UpdateLabelParam{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", url='" + url + '\'' +
                ", codeContent='" + codeContent + '\'' +
                ", deprecated=" + deprecated +
                ", type='" + type + '\'' +
                '}';
    }
}
