package com.zd.hotelbook.common;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ji jinliang
 * @Date: 2022/04/03/12:35
 * @Description:
 */
public enum  ResponseCodeEnum {

    SUCCESS("success", 1000, "请求成功"),
    FAILED("failed", 3000, "请求失败");

    ResponseCodeEnum(String status, Integer code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }

    private String status;

    private Integer code;

    private String description;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
