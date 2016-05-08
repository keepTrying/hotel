package com.dreamfactory.hotelmanager.module;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import static java.lang.String.format;

/**
 * Created by yangpeidong on 16/3/24.
 */
public class Room implements Parcelable{
    private String room_num;
    private int room_type;
    private float room_area;
    private float room_cost;
    private String room_img;
    private String room_facility;

    public Room(String room_num, int room_type, float room_area, float room_cost, String room_img, String room_facility) {
        this.room_num = room_num;
        this.room_type = room_type;
        this.room_area = room_area;
        this.room_cost = room_cost;
        this.room_img = room_img;
        this.room_facility = room_facility;
    }

    public Room(){}



    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public int getRoom_type() {
        return room_type;
    }

    public void setRoom_type(int room_type) {
        this.room_type = room_type;
    }

    public float getRoom_area() {
        return room_area;
    }

    public void setRoom_area(float room_area) {
        this.room_area = room_area;
    }

    public float getRoom_cost() {
        return room_cost;
    }

    public void setRoom_cost(float room_cost) {
        this.room_cost = room_cost;
    }

    public String getRoom_img() {
        return room_img;
    }

    public void setRoom_img(String room_img) {
        this.room_img = room_img;
    }

    public String getRoom_facility() {
        return room_facility;
    }

    public void setRoom_facility(String room_facility) {
        this.room_facility = room_facility;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(room_num);
        dest.writeInt(room_type);
        dest.writeFloat(room_area);
        dest.writeFloat(room_cost);
        dest.writeString(room_img);
        dest.writeString(room_facility);
    }

    protected Room(Parcel in) {
        room_num = in.readString();
        room_type = in.readInt();
        room_area = in.readFloat();
        room_cost = in.readFloat();
        room_img = in.readString();
        room_facility = in.readString();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };


}
