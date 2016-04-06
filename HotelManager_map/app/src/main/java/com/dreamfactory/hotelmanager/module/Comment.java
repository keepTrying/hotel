package com.dreamfactory.hotelmanager.module;

import java.sql.Time;

/**
 * Created by yangpeidong on 16/3/24.
 */
public class Comment {
    int uid;
    String time;
    String content;
    int id;
    int room_num;

    public Comment(int uid, String time, String content, int id, int room_num, int star, String reply, int img, String uName) {
        this.uid = uid;
        this.time = time;
        this.content = content;
        this.id = id;
        this.room_num = room_num;
        this.star = star;
        this.reply = reply;
        this.img = img;
        this.uName = uName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    int star;
    String reply;
    int img;
    String uName;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
