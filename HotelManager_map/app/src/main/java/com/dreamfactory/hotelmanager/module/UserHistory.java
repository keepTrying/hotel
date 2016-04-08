package com.dreamfactory.hotelmanager.module;

import java.io.Serializable;

/**
 * Created by yangpeidong on 16/4/1.
 */
public class UserHistory implements Serializable{
    public String time_begin;
    public String time_end;
    public int room_num;
    public UserHistory(){};

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

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }
}
