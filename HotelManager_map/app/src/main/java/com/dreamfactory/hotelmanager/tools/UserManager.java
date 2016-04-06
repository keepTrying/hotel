package com.dreamfactory.hotelmanager.tools;

import com.dreamfactory.hotelmanager.module.User;

import java.util.HashMap;

/**
 * Created by yangpeidong on 16/4/1.
 */
public class UserManager {
    private User user;


    private static UserManager ourInstance = new UserManager();

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
