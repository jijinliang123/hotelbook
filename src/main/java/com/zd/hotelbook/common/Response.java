package com.zd.hotelbook.common;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ji jinliang
 * @Date: 2022/04/03/11:31
 * @Description:
 */
public class Response<T> {

    private Integer code;

    private String message;

    private T data;

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static Response failed(String msg) {
        return new Response(ResponseCodeEnum.FAILED.getCode(), msg, null);
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response() {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.message = ResponseCodeEnum.SUCCESS.getDescription();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }
}
