package com.dreamfactory.hotelmanager.module;

/**
 * Created by yangpeidong on 16/3/25.
 */
public class RecommendItem {

    String type;
    String desc;

    public RecommendItem(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
