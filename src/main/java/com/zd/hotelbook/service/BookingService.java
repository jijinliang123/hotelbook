package com.zd.hotelbook.service;

import com.zd.hotelbook.common.Response;
import com.zd.hotelbook.entity.BookingSheet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ji jinliang
 * @Date: 2022/04/03/11:25
 * @Description:
 */
public class BookingService {

    /**
     * 单例创建本服务
     * 懒汉式，线程安全,双检锁，能在多线程的情况下保持高性能
     */
    private static BookingService instance=null;
    private BookingService (){}
    public static BookingService getInstance() {
        if (instance == null) {
            synchronized (BookingService.class) {
                if (instance == null) {
                    instance = new BookingService();
                }
            }
        }
        return instance;
    }


    //默认，初始房间列表
   private static Set<String> roomSet=new HashSet<>();
    static {
        roomSet.add("room01");
        roomSet.add("room02");
        roomSet.add("room03");
        roomSet.add("room04");
    }

    //酒店预定列表
    private List<BookingSheet> bookingSheetList=new ArrayList<>();

    /**
     * 预定房间
     * @param request
     * @return
     */
    public synchronized Response bookRoom(BookingSheet request) {
        //判断是否存在该房间
        boolean isRoomExist=roomSet.contains(request.getRoomNo());
        if(!isRoomExist){
            return Response.failed("预定失败，没有该房间号");
        }
        //判断该房间在那天是否被预定
        List<BookingSheet> list=bookingSheetList
                .stream()
                .filter(e->request.getRoomNo().equals(e.getRoomNo()) && request.getDate().equals(e.getDate()))
                .collect(Collectors.toList());
        if(list.size()>0){
            return Response.failed("预定失败，该房间当天已被预定");
        }
        bookingSheetList.add(request);
        return new Response().setMessage("预定成功");
    }

    /**
     * 按照日期查询可用的房间
     * @param date
     * @return
     */
    public Response queryAvailableRoomByDate(String date) {
        List<BookingSheet> temp=bookingSheetList.stream().filter(e->date.equals(e.getDate())).collect(Collectors.toList());
        Set<String> roomAvailable=new HashSet<>(roomSet);
        temp.forEach(t->{
            if(roomSet.contains(t.getRoomNo())){
                roomAvailable.remove(t.getRoomNo());
            }
        });
        return new Response().setData(roomAvailable);
    }

    /**
     * 根据用户名查询该用户所有的订单
     * @param name
     * @return
     */
    public Response queryBookingRecordByCustomerName(String name) {
        List<BookingSheet> list=bookingSheetList
                .stream()
                .filter(e->e.getName().equals(name))
                .collect(Collectors.toList());
        return new Response().setData(list);
    }

    /**
     * 添加房间
     * @param roomNo
     * @return
     */
    public synchronized Response addRoom(String roomNo) {
        //先判断房间列表中是否存在该房间号
        if(roomSet.contains(roomNo)){
            return Response.failed("添加失败，该房间号已存在");
        }
        roomSet.add(roomNo);
        return new Response().setData(roomSet);
    }

    /**
     * 删除房间
     * @param roomNo
     * @return
     */
    public synchronized Response delRoom(String roomNo) {
        //先判断房间列表中是否存在该房间号
        if(!roomSet.contains(roomNo)){
            return Response.failed("删除失败，不存在该房间号");
        }
        roomSet.remove(roomNo);
        return new Response().setData(roomSet);
    }

    /**
     * 调用服务
     */
    public static void serviceStart(){
        Scanner sc=new Scanner(System.in);
        BookingService service=BookingService.getInstance();
        while (true){
            System.out.println("请输入要使用的功能编号：\n1.预定酒店功能\n2.查找某日可用房间\n3.查找客人所有订单\n4.添加房间\n5.删除房间\n6.退出系统");
            int funNo=sc.nextInt();
            sc.nextLine();
            if(funNo==1){
                System.out.println("请输入客户名称");
                String name=sc.next();
                System.out.println("请输入房间号");
                String roomNo=sc.next();
                System.out.println("请输入预定日期，格式为：年-月-日");
                String date=sc.next();
                BookingSheet bookingSheet=new BookingSheet(name,roomNo,date);
                Response response=service.bookRoom(bookingSheet);
                System.out.println(response);
            }else if(funNo==2){
                System.out.println("请输入查询日期");
                String date=sc.next();
                Response response=service.queryAvailableRoomByDate(date);
                System.out.println(response);
            }else if(funNo==3){
                System.out.println("请输入客户姓名");
                String name=sc.next();
                Response response=service.queryBookingRecordByCustomerName(name);
                System.out.println(response);
            }else if(funNo==4){
                System.out.println("请输入新房间编号");
                String newRoomNo=sc.next();
                Response response=service.addRoom(newRoomNo);
                System.out.println(response);
            }else if(funNo==5){
                System.out.println("请输入要删除的房间编号");
                String newRoomNo=sc.next();
                Response response=service.delRoom(newRoomNo);
                System.out.println(response);
            }
            else if(funNo==6){
                break;
            }
            else {
                System.out.println("请输入正确的功能编号");
            }
        }
    }

}
