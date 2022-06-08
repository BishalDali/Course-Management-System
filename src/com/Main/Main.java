package com.Main;

import com.Controller.CreateDbUtils;
import com.View.Gui;

public class Main {
    public static void main(String[] args) {

        new CreateDbUtils();
        new Gui();
        System.out.println("This is the Course Management System. To use the system as student and instructors, you will need to create an account and login to it.\n");
        System.out.println("To use admin account, ADMIN ID is 12345 and password is Admin");

    }
}
