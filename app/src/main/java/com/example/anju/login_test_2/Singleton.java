package com.example.anju.login_test_2;

import java.util.HashMap;

public class Singleton {
    private static final Singleton instance = new Singleton();
    public static HashMap<String, user_signup> userinfo = new HashMap<String, user_signup>();

    // Private constructor prevents instantiation from other classes
    private Singleton() {
    }

    public static HashMap<String, user_signup> getMap() {
        return userinfo;
    }
}
