package com.Model.Courses;

import com.Controller.ManageDbUtils;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Module {
    private String moduleName;
    private String moduleCode;
    private String instructorName;
    private int semester;
    private int credits;
    private String moduleType;
    private Double marksSecured;

    public Module(){}

    public Module(String moduleName, String moduleCode, String moduleType){
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.credits = 20;
        this.semester = 0;
        this.moduleType = moduleType;

        this.instructorName = null;
        this.marksSecured = 0.0;
    }




    public String[] selectModule(){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectModule();
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        availableModule = manageDbUtils.selectModule();
        String[] modules = new String[i];
        i=0;
        try {
            while (availableModule.next()){
                modules[i] = availableModule.getString("moduleName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return modules;
    }

    public String[] selectModule(String courseName){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectModule(courseName);
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        availableModule = manageDbUtils.selectModule(courseName);
        String[] modules = new String[i];
        i=0;
        try {
            while (availableModule.next()){
                modules[i] = availableModule.getString("moduleName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return modules;
    }
    public int totalNumOfModules(String courseName){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectModule(courseName);
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    public String[] getStudentModule(String studentLevel,int studentId){
        ManageDbUtils manageDbUtils= new ManageDbUtils();
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
        System.out.println(i);
        studentDetails = manageDbUtils.selectModuleDetails(studentId,semester1,semester2);
        int j = 0;
        String[] modules = new String[i];
        try {
            while (studentDetails.next()){
                modules[j] = studentDetails.getString("moduleName");
                j++;

            }
            return modules;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String[] selectModule(String courseName, int semester){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectModule(courseName, semester);
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        availableModule = manageDbUtils.selectModule(courseName,semester);
        String[] modules = new String[i];
        i=0;
        try {
            while (availableModule.next()){
                modules[i] = availableModule.getString("moduleName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return modules;
    }


    public String[] selectCoreModule(String courseName){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectCoreOrOptionalModule(courseName,"Core");
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        availableModule = manageDbUtils.selectCoreOrOptionalModule(courseName,"Core");
        String[] modules = new String[i];
        i=0;
        try {
            while (availableModule.next()){
                modules[i] = availableModule.getString("moduleName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return modules;
    }


    public int totalNumOfCoreModules(String courseName){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectCoreOrOptionalModule(courseName,"Core");
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    public int totalNumOfOptionalModules(String courseName){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectCoreOrOptionalModule(courseName,"Optional");
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    public String[] selectOptionalModules(String courseName){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectCoreOrOptionalModule(courseName,"Optional");
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        availableModule = manageDbUtils.selectCoreOrOptionalModule(courseName,"Optional");
        String[] modules = new String[i];
        i=0;
        try {
            while (availableModule.next()){
                modules[i] = availableModule.getString("moduleName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return modules;
    }

 public String[][] selectModules(String courseName){
     ManageDbUtils manageDbUtils = new ManageDbUtils();
     ResultSet availableModule = manageDbUtils.selectModule(courseName);
     int i = 0;
     try {
         while (availableModule.next()){
             i++;
         }
     }catch(SQLException e){
         e.printStackTrace();
     }
     availableModule = manageDbUtils.selectModule(courseName);
     String[][] modules = new String[i][4];
     i=0;
     try {
         while (availableModule.next()){
             int j = 0;
             modules[i][j] = availableModule.getString("moduleName");
             j++;
             modules[i][j] = availableModule.getString("semester");
             j++;
             modules[i][j] = availableModule.getString("credits");
             j++;
             modules[i][j] = availableModule.getString("moduleType");
             i++;
             }
         } catch (SQLException ex) {
         ex.printStackTrace();
     }
     System.out.println("hi");
     for ( i = 0; i < 20; i++) {
         for (int j = 0; j < 4; j++) {
             System.out.println(modules[i][j]);
         }
     }
     return modules;
 }

 public String[] selectOptionalModule(String courseName, int semester){
     ManageDbUtils manageDbUtils = new ManageDbUtils();
     ResultSet availableModule = manageDbUtils.selectOptionalModule(courseName,semester);
     int i = 0;
     try {
         while (availableModule.next()){
             i++;
         }
     }catch(SQLException e){
         e.printStackTrace();
     }
     availableModule = manageDbUtils.selectOptionalModule(courseName, semester);
     String[] modules = new String[i];
     i=0;
     try {
         while (availableModule.next()){
             modules[i] = availableModule.getString("moduleName");
             i++;
         }
     }catch(SQLException e){
         e.printStackTrace();
     }
     return modules;
 }
 public int numOfModule(String courseName, String semester){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
     ResultSet availableModule = manageDbUtils.selectOptionalModule(courseName, Integer.parseInt(semester));
     int i = 0;
     try {
         while (availableModule.next()){
             i++;
         }
     }catch(SQLException e){
         e.printStackTrace();
     }
     return i;
 }
 public int numOfCoreModule(String courseName,String semester){
     ManageDbUtils manageDbUtils = new ManageDbUtils();
     ResultSet availableModule = manageDbUtils.selectCoreModule(courseName, Integer.parseInt(semester));
     int i = 0;
     try {
         while (availableModule.next()){
             i++;
         }
     }catch(SQLException e){
         e.printStackTrace();
     }
     return i;
 }

    public int numOfOptionalModule(String courseName,String semester){

        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectOptionalModule(courseName, Integer.parseInt(semester));
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return i;
    }
 public String[] selectModule(int studentID, int semester){
     ManageDbUtils manageDbUtils = new ManageDbUtils();
     ResultSet availableModule = manageDbUtils.selectModuleInfo(studentID,semester);
     int i;
     String[] modules = new String[3];
     i=0;
     try {
         while (availableModule.next()){
             modules[i] = availableModule.getString("moduleName");
             i++;
         }
     }catch(SQLException e){
         e.printStackTrace();
     }
     return modules;
 }

    public String[] selectGrade(int studentID, int semester){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectModuleInfo(studentID,semester);

        String[] modules = new String[3];
        int i=0;
        try {
            while (availableModule.next()){
                modules[i] = availableModule.getString("marksSecured");
                i++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"The student has not been graded yet!",null,JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return modules;
    }

    public String[] selectModule(int studentId){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectModuleInfo(studentId);
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        String[] modules = new String[i];
        availableModule = manageDbUtils.selectModuleInfo(studentId);
        i=0;
        try {
            while (availableModule.next()){
                modules[i] = availableModule.getString("moduleName");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return modules;
    }


    public String[] moduleInfo(String moduleName){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectModules(moduleName);
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        String[] modules = new String[i];
        availableModule = manageDbUtils.selectModuleInfo(moduleName);
        i=0;
        try {
            while (availableModule.next()){
                modules[i] = availableModule.getString("moduleCode");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println(modules[0]);
        return modules;
    }

    public String[] selectCoreSemester(String courseName){
        ManageDbUtils manageDbUtils = new ManageDbUtils();
        ResultSet availableModule = manageDbUtils.selectCoreOrOptionalModule(courseName,"Core");
        int i = 0;
        try {
            while (availableModule.next()){
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        availableModule = manageDbUtils.selectCoreOrOptionalModule(courseName,"Core");
        String[] semester = new String[i];
        i=0;
        try {
            while (availableModule.next()){
                semester[i] = availableModule.getString("semester");
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return semester;
    }

}
