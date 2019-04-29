package com.example.androidlibraryproject;


/**
 * @Description:
 * @Author:mac
 * @Date:2019/4/1
 */
public class ErrorBean{


    /**
     * code : 40010
     * error_msg : 参数token长度不得小于32位
     */
    public int code;
    public String error_msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    @Override
    public String toString() {
        return "ErrorBean{" +
                "code=" + code +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }
}
