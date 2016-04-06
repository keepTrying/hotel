package com.dreamfactory.hotelmanager.module;

import java.sql.Time;
import java.util.Date;

/**
 * Created by yangpeidong on 16/3/25.
 */
public class Indent {

    int id;
    Date time_begin;
    Date time_end;
    int pay;
    int room_num;
    int type;
    String time;
    int user_id;
    float cost;

    public Indent(int id, Date time_begin, Date time_end, int status, int room_num, int type, String time, int user_id, float cost) {
        this.id = id;
        this.time_begin = time_begin;
        this.time_end = time_end;
        this.pay = status;
        this.room_num = room_num;
        this.type = type;
        this.time = time;
        this.user_id = user_id;
        this.cost = cost;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(Date time_begin) {
        this.time_begin = time_begin;
    }

    public Date getTime_end() {
        return time_end;
    }

    public void setTime_end(Date time_end) {
        this.time_end = time_end;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
