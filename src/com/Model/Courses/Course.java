package com.Model.Courses;

import com.Controller.ManageDbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course {
    private Module module = new Module();
    private String level;
    private String courseName;
    private String courseCode;
    private int enrolledStatus;
    private Boolean cancelStatus;
    ArrayList<Module> mods = new ArrayList<Module>(24);
    public void setEnrolledStatus(int enrolledStatus) {this.enrolledStatus = enrolledStatus;}

    public Course(){

    }




    ManageDbUtils manageDbUtils = new ManageDbUtils();
    public String[] selectCourse(){
        ResultSet availableCourse = manageDbUtils.selectCourse();
        int i=0;

        try{
            while (availableCourse.next()){
                i++;
            }}catch(SQLException e){
            e.printStackTrace();
        }
        availableCourse = manageDbUtils.selectCourse();
        String[] courses = new String[i];
        i=0;
        try{
            while(availableCourse.next()){
                courses[i] = availableCourse.getString("courseName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return courses;
    }

    public void cancelCourse(){
       String[] courses = selectCourse();

        ResultSet availableCourse = manageDbUtils.selectCourse();
        int i=0;
        int j,count=0;
        try{
            while (availableCourse.next()){
                i++;
            }
            System.out.println(i);
        }catch(SQLException e){
            e.printStackTrace();
        }

            for ( j = 0; j < i; j++) {
                ResultSet student = manageDbUtils.selectStudent(courses[j]);
                try{

                    while(availableCourse.next()){
                        count++;
                    }
                    System.out.println(count);
                    if(count==0){
                        manageDbUtils.updateCourseStatus(false,courses[j]);
                        System.out.println(courses[j]);
                    }
                }catch(SQLException err){
                    err.printStackTrace();
                }}


            }

    }

