package com.wd.logindemo2.bean;

/**
 * @author dingtao
 * @date 2019/4/25 14:32
 * qq:1940870847
 */
public class Result<T> {
    private String message="请求失败";
    private String status="-1";
    private T result;//请求结果


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
