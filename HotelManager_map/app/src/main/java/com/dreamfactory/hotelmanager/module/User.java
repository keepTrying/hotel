package com.dreamfactory.hotelmanager.module;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangpeidong on 16/4/1.
 */
public class User implements Serializable{
    private int user_id;
    private String user_nick;
    private int user_type;
    private int user_gender;
    private int user_years;
    private String user_email;
    private String user_time;
    private String user_password;
    private int user_point;
    private String user_phone;
    private String user_id_num;
    private String user_name;
    private String user_que;
    private String user_ans;
    private int user_img;
    private List<UserHistory> user_history=new ArrayList<UserHistory>();

    public User(){}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(int user_gender) {
        this.user_gender = user_gender;
    }

    public int getUser_years() {
        return user_years;
    }

    public void setUser_years(int user_years) {
        this.user_years = user_years;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_time() {
        return user_time;
    }

    public void setUser_time(String user_time) {
        this.user_time = user_time;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_point() {
        return user_point;
    }

    public void setUser_point(int user_point) {
        this.user_point = user_point;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_id_num() {
        return user_id_num;
    }

    public void setUser_id_num(String user_id_num) {
        this.user_id_num = user_id_num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<UserHistory> getUser_history() {
        return user_history;
    }

    public void setUser_history(List<UserHistory> user_history) {
        this.user_history = user_history;
    }

    public String getUser_que() {
        return user_que;

    }

    public void setUser_que(String user_que) {
        this.user_que = user_que;
    }

    public String getUser_ans() {
        return user_ans;
    }

    public void setUser_ans(String user_ans) {
        this.user_ans = user_ans;
    }

    public int getUser_img() {
        return user_img;
    }

    public void setUser_img(int user_img) {
        this.user_img = user_img;
    }
}
