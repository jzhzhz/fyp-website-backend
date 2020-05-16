package com.fyp.websitebackend.common.entity;

public class CustomResponseEntity {
    private int code;
    private String codeMsg;
    private Object data;

    public CustomResponseEntity(int code, String message) {
        this.code = code;
        this.codeMsg = message;
    }

    public CustomResponseEntity(int code, String codeMsg, Object data) {
        this.code = code;
        this.codeMsg = codeMsg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static CustomResponseEntity success(Object o) {
        CustomResponseEntity entity = new CustomResponseEntity(0, "request success");
        entity.setData(o);
        return entity;
    }

    public static CustomResponseEntity error(Object o) {
        CustomResponseEntity entity = new CustomResponseEntity(-1, "request error");
        entity.setData(o);
        return entity;
    }
}
