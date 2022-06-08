package com.Model.MainBody;

import com.Controller.ManageDbUtils;
import com.Model.Courses.Module;
import com.Model.Courses.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends Users{
    private int studentId;
    private String studentName;
    private String level;
    private String address;
    private String emailAddress;
    private String semester;
    private Boolean eligibleForNextSem = true;
    private String remarks;
    private String course;
    ManageDbUtils manageDbUtils = new ManageDbUtils();

   ArrayList<Module> modulesSelected = new ArrayList<Module>();


   public  Student(){}
   public Student(int userId, String password){

       this.studentId = userId;
       setStudentInfo();
       super.setUserPassword(password);
       super.setUserType("Student");

   }

    public int getStudentId() {return studentId;}


    public String  getLevel(){return level;}
    public void setLevel(String level){
        this.level = level;
    }

    public String getStudentName(){return studentName;}
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    public String getCourse(){return course;}
    public void setCourse(String course){this.course = course;}

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEmailAddress(){return emailAddress;}
    public void setEmailAddress(String emailAddress){this.emailAddress = emailAddress;}

    public Boolean getEligibleForNextSem(){return eligibleForNextSem;}
    public void  setEligibleForNextSem(Boolean eligibleForNextSem){this.eligibleForNextSem = eligibleForNextSem;}



    public void setStudentInfo(){
        ResultSet resultSet = manageDbUtils.getStudentInfo(studentId);
        try {
            while(resultSet.next()){
                setAddress(resultSet.getString("address"));
                setLevel(resultSet.getString("level"));
                setStudentName(resultSet.getString("studentName"));
                setCourse(resultSet.getString("course"));
                setEmailAddress(resultSet.getString("emailAddress"));
                setEligibleForNextSem(resultSet.getBoolean("eligibleForNextSem"));
                setRemarks(resultSet.getString("remark"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public String[] selectStudent(){
       ResultSet availableStudent = manageDbUtils.selectStudent();
       int i = 0;
       try {
           while (availableStudent.next()){
               i++;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       availableStudent = manageDbUtils.selectStudent();
       String[] students = new String[i];
       i=0;
       try {
           while (availableStudent.next()){
               students[i] = availableStudent.getString("studentName");
               i++;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       return students;
   }


   public String[][] selectStudentDetails(){
       ResultSet availableStudents = manageDbUtils.selectStudent();
       int i = 0;
       try {
           while (availableStudents.next()){
               i++;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       availableStudents = manageDbUtils.selectStudent();
       String[][] students = new String[i][2];
       i=0;
       try {
           while (availableStudents.next()){
               int j=0;
               students[i][j] = availableStudents.getString("studentName");
               j++;
               students[i][j] = availableStudents.getString("studentId");
               i++;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       return students;
   }
   public int noOfStudents(){
       ResultSet availableStudents = manageDbUtils.selectStudent();
       int i = 0;
       try {
           while (availableStudents.next()){
               i++;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       return i;
   }

   public  int  selectStudentId(String studentName){
       ResultSet availableStudents = manageDbUtils.selectStudentId(studentName);
       
       int id = 0;
       try {
           while (availableStudents.next()){
               id = availableStudents.getInt("studentId");
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       return id;
   }

   public String selectRemark(String studentName){
       ResultSet availableStudent = manageDbUtils.selectStudent();
       int i = 0;
       try {
           while (availableStudent.next()){
               i++;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       availableStudent = manageDbUtils.selectStudent();
       String students = null;
       i=0;
       try {
           while (availableStudent.next()){
               students = availableStudent.getString("remark");
               i++;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       return students;
   }

   public String[][] getStudentModule(String studentLevel,int studentId){
       int semester1 = 0, semester2 = 0;
       if (studentLevel.equals("Four")){
           semester1 = 1;
           semester2 = 2;
       }else if(studentLevel.equals("Five")){
           semester1=3;
           semester2=4;
       }else if (studentLevel.equals("Six")){
           semester1=5;
           semester2=6;
       }
        ResultSet studentDetails = manageDbUtils.selectModuleDetails(studentId,semester1,semester2);
        int i = 0;
       try {
           while (studentDetails.next()){
               i++;
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
       studentDetails = manageDbUtils.selectModuleDetails(studentId,semester1,semester2);
       int j = 0;
       String[][] details = new String[i][4];
       try {
           while (studentDetails.next()){
               details[j][0] = studentLevel;
               details[j][1]= studentDetails.getString("moduleName");
               details[j][2]= studentDetails.getString("semester");
               details[j][3] = "Enrolled";
               j++;

           }
           return details;
       }catch(SQLException e){
           e.printStackTrace();
       }
   return null;}

    public int[] selectStudentsId(String moduleName){
        ResultSet availableStudent = manageDbUtils.selectStudentsId(moduleName);
        int i = 0;
        try {
            while (availableStudent.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        availableStudent = manageDbUtils.selectStudentsId(moduleName);
        int[] students = new int[i];
        i=0;
        try {
            while (availableStudent.next()){
                students[0] = availableStudent.getInt("studentId");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }
    public String  selectStudentName(int studentId){
       ResultSet resultSet = manageDbUtils.selectStudentName(studentId);
       String students= new String();
        try {
            while (resultSet.next()){
                students = resultSet.getString("studentId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }
}
