package com.Model.MainBody;

import com.Controller.ManageDbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Instructor extends Users{
    private String instructorName;
    private int instructorId;
    private String address;
    private String email;
    private String[] modules;
    private ManageDbUtils manageDbUtils = new ManageDbUtils();

    public Instructor(){}
    public Instructor(int userId, String password){
        this.instructorId = userId;
        super.setUserId(String.valueOf(userId));
        super.setUserPassword(password);
        super.setUserType("Instructor");
        setInstructorInfo();
    }

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getInstructorName() {
        return instructorName;
    }
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public int getInstructorId() {
        return instructorId;
    }
    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public void setInstructorInfo(){
        ResultSet resultSet = manageDbUtils.selectInstructorInfo(instructorId);
        try {
            while (resultSet.next()) {
                setAddress(resultSet.getString("instructorAddress"));
                setInstructorName(resultSet.getString("instructorName"));
                setEmail(resultSet.getString("instructorEmail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String[] selectUpdatedInstructors(){
        ResultSet availableInstructors = manageDbUtils.selectInstructorModule();
        int i=0;

        try{
            while (availableInstructors.next()){
                i++;
            }}catch(SQLException e){
            e.printStackTrace();
        }
        availableInstructors = manageDbUtils.selectInstructorModule();
        String[] instructors = new String[i];
        i=0;
        try{
            while(availableInstructors.next()){
                instructors[i] = availableInstructors.getString("instructorName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return instructors;
    }

    public String[]  selectInstructors(){
        ResultSet availableInstructors = manageDbUtils.selectInstructorsModule();
        int i=0;

        try{
            while (availableInstructors.next()){
                i++;
            }}catch(SQLException e){
            e.printStackTrace();
        }
        availableInstructors = manageDbUtils.selectInstructorsModule();
        String[] instructors = new String[i];
        i=0;
        try{
            while(availableInstructors.next()){
                instructors[i] = availableInstructors.getString("instructorName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return instructors;
    }

    public String[] selectUpdatedInstructors(String moduleName){

        ResultSet availableInstructors = manageDbUtils.selectInstructorModule(moduleName);
        int i=0;

        try{
            while (availableInstructors.next()){
                i++;
            }}catch(SQLException e){
            e.printStackTrace();
        }
        availableInstructors = manageDbUtils.selectInstructorModule(moduleName);
        String[] instructors = new String[i];
        i=0;
        try{
            while(availableInstructors.next()){
                instructors[i] = availableInstructors.getString("instructorName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return instructors;
    }
    public String assignInstructor(String moduleName){
        ResultSet resultSet = manageDbUtils.assignInstructor(moduleName);
        String instructor = new String();
        try {
            while (resultSet.next()){
                instructor = resultSet.getString("instructorName");
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return instructor;
    }

    public String[] selectInstructorsModule(String moduleName){
        ResultSet resultSet = manageDbUtils.selectInstructorsModule(moduleName);
        int i=0;

        try{
            while (resultSet.next()){
                i++;
            }}catch(SQLException e){
            e.printStackTrace();
        }
        resultSet = manageDbUtils.selectInstructorsModule(moduleName);
        String[] instructor = new String[i];
        i=0;
        try {
            while (resultSet.next()){
                instructor[i] = resultSet.getString("moduleName");
                i++;
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return instructor;
    }
}
