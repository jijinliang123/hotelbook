package com.zd.hotelbook.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ji jinliang
 * @Date: 2022/04/03/11:23
 * @Description:
 */
public class BookingSheet {

    @Override
    public String toString() {
        return "BookingSheet{" +
                "name='" + name + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 房间号
     */
    private String roomNo;

    /**
     * 预定日期
     */
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookingSheet(String name, String roomNo, String date) {
        this.name = name;
        this.roomNo = roomNo;
        this.date = date;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
