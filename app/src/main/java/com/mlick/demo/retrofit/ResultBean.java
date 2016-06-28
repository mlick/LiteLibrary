package com.mlick.demo.retrofit;

/**
 * Created by lxx on 2016/6/28 12:52
 */
public class ResultBean {
    private int result;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
