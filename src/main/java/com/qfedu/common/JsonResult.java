package com.qfedu.common;

public class JsonResult {
    private int code;  //状态码   1是正确返回码，0是错误的返回码
    private Object info;   //返回的信息可以是任意内容

    public JsonResult() {
    }

    public JsonResult(int code, Object info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
