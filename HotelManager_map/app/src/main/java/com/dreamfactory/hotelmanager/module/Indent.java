package com.dreamfactory.hotelmanager.module;

import java.sql.Time;
import java.util.Date;

/**
 * Created by yangpeidong on 16/3/25.
 */
public class Indent {


    String time_begin;
    String time_end;
    int pay;
    int room_num;
    int indent_status;
    int user_id;
    float cost;
    int indent_type;
    int indent_id;
    String indent_time;


    public Indent(){}

    public Indent(String time_begin, String time_end, int pay, int room_num, int indent_status, int user_id, float cost, int indent_type, int indent_id, String indent_time) {
        this.time_begin = time_begin;
        this.time_end = time_end;
        this.pay = pay;
        this.room_num = room_num;
        this.indent_status = indent_status;
        this.user_id = user_id;
        this.cost = cost;
        this.indent_type = indent_type;
        this.indent_id = indent_id;
        this.indent_time = indent_time;
    }

    public String getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(String time_begin) {
        this.time_begin = time_begin;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
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

    public int getIndent_status() {
        return indent_status;
    }

    public void setIndent_status(int indent_status) {
        this.indent_status = indent_status;
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

    public int getIndent_type() {
        return indent_type;
    }

    public void setIndent_type(int indent_type) {
        this.indent_type = indent_type;
    }

    public int getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(int indent_id) {
        this.indent_id = indent_id;
    }

    public String getIndent_time() {
        return indent_time;
    }

    public void setIndent_time(String indent_time) {
        this.indent_time = indent_time;
    }
}
