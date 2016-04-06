package com.dreamfactory.hotelmanager.module;

import android.media.Image;

import static java.lang.String.format;

/**
 * Created by yangpeidong on 16/3/24.
 */
public class Room {
    private int num;
    private int type;
    private float area;
    private float cost;
    private int img;

    public Room(float cost, int num, int img) {
        this.cost = cost;
        this.num = num;
        this.img = img;
    }

    public String getNum() {
        return format("%d",num);
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getCost() {
        return format("%.2f元",cost);
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
