package com.Model.MainBody;
import java.util.Random;
import java.util.random.*;


import com.Controller.CreateDbUtils;

import java.sql.Connection;

public class Users {
    private Connection con;
    private String userId;
    private String userType;
    private String userPassword;

    public void users(){
        try {
            con = CreateDbUtils.getDbConnection();
        } catch (Exception ex) {
            System.out.println("Couldn't connect to database!");
        }

    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String password) {
        this.userPassword = password;
    }

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }


    public int GenerateID(){
        Random random = new Random();
        int ID = random.nextInt(100000,10000000);
        return  ID;
    }
}
