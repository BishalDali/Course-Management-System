package com.Controller;
import com.Model.Courses.Module;
import com.Model.Courses.Course;

import javax.swing.*;
import java.sql.*;

public class ManageDbUtils {
    private Connection con = null;
    public ManageDbUtils() {
        try {
            con = CreateDbUtils.getDbConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    //Saves Users Details
    public void saveUserDetails(int ID, String Password, String UserType) throws SQLException {
        String insert = "INSERT INTO user (UserID, UserPassword, UserType) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, String.valueOf(ID));
            statement.setString(2, String.valueOf(Password));
            statement.setString(3, UserType);
            statement.executeUpdate();
            statement.close();


        } catch (SQLException sqlException) {
//            JOptionPane.showMessageDialog(null, "The Person already exists!");
//             System.out.println("Error in user table");
            sqlException.printStackTrace();
        }

    }

    public void addInstructors(String moduleName, String instructorName){
        String insert = "INSERT INTO instructorModule (`moduleName`,`instructorName`) VALUES (?,?)";
        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1,moduleName);
            statement.setString(2,instructorName);
            statement.executeUpdate();
        }catch (SQLException sqlException){}

    }

    public  void saveInstructorDetails(String instructorName){
        String insert = "INSERT INTO `instructorModule` (instructorName) VALUES (?)";
        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1,instructorName);
            statement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }


    //Save Students Details
    public boolean saveStudentDetails(int StudentID, String name, String Email, String course,String address, String level) {
        String insert = "INSERT INTO student (StudentID, studentName, emailAddress, course, address, Level) VALUES (?, ?, ?, ?,?, ?)";
        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setInt(1, StudentID);
            statement.setString(2, name);
            statement.setString(3, Email);
            statement.setString(4, course);
            statement.setString(5, address);
            statement.setString(6, level);

            statement.executeUpdate();
            statement.close();
            return false;
        } catch (SQLIntegrityConstraintViolationException sqlException) {
            JOptionPane.showMessageDialog(null, "User Already Exists!!!", "Account already exists", JOptionPane.ERROR_MESSAGE);
            return true;
        } catch (SQLException sqlException) {
            System.out.println("Error in user table");

            sqlException.printStackTrace();
            return true;

        }
    }

    public void removeInstructors(String moduleName, String instructorName){
        String delete = "DELETE FROM `instructorModule` WHERE `moduleName` LIKE ? AND `instructorName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setString(1, moduleName);
            statement.setString(2, instructorName);
            statement.executeUpdate();


        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "User not found!!!", "Invalid UserID", JOptionPane.ERROR_MESSAGE);
        }
    }


    public ResultSet getStudentInfo(int studentId){
        String select = "SELECT * FROM `student` WHERE `studentId` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setInt(1, studentId);
            return statement.executeQuery();


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "User not found!!!","Invalid UserID",JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    public void saveStudentModule(int studentID, String courseName, String moduleName1, String moduleName2){
        Module modules = new Module();
        String[] availableModules;
        String[] semester;
        String insertOptional;
        availableModules = modules.selectCoreModule(courseName);
        int noOfModules = modules.totalNumOfCoreModules(courseName);
        semester = modules.selectCoreSemester(courseName);

        String insertCore = "INSERT INTO `studentModule` (`studentId`, `moduleName`, `semester` ) VALUES (?, ?, ?)";
        for (int i = 0; i < noOfModules; i++) {
            try {
                PreparedStatement statement = con.prepareStatement(insertCore);
                statement.setInt(1, studentID);
                statement.setString(2, availableModules[i]);
                statement.setString(3, semester[i]);
                statement.executeUpdate();




            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
                insertOptional = "INSERT INTO `studentModule` (`studentId`, `moduleName`,`semester`) VALUES (?,?,?), (?,?,?)";

            try {
                PreparedStatement statement = con.prepareStatement(insertOptional);
                statement.setInt(1, studentID);
                statement.setString(2, moduleName1);
                statement.setInt(3,5);
                statement.setInt(4, studentID);
                statement.setString(5, moduleName2);
                statement.setInt(6,6);
                statement.executeUpdate();
                statement.close();



            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }


        }
    }



    //Save Instructors Details
    public boolean saveInstructorDetails(int InstructorID, String name, String Address, String emailAddress){
        String insert = "INSERT INTO instructor (instructorId, instructorName, instructorAddress, instructorEmail) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setInt(1, InstructorID);
            statement.setString(2, name);
            statement.setString(3, Address);
            statement.setString(4, emailAddress);
            statement.executeUpdate();
            statement.close();
            return false;

        }catch (SQLIntegrityConstraintViolationException sqlException){
            JOptionPane.showMessageDialog(null, "User Already Exists!!!","Account already exists",JOptionPane.ERROR_MESSAGE);
            return true;
        }catch(SQLException sqlException){
            System.out.println("Error in user table");

            sqlException.printStackTrace();
            return true;

        }
    }




    //Select Login Details
    public String CheckLoginDetails(String ID, String userType){
        String select = "SELECT `userPassword` FROM `user` WHERE `userId` LIKE ? AND  `userType` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, ID);
            statement.setString(2, userType);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String Password = resultSet.getString("userPassword");
                return Password;
            }


        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "User not found!!!","Invalid UserID",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }


    //Select Course
    public ResultSet selectCourse(){
        String select = "SELECT * FROM `course`";


        try {
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();




        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }
    public ResultSet selectInstructorsModule(){
        String select = "SELECT * FROM `instructorModule`";

        try {
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();




        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }
    public ResultSet selectModule(){
        String select = "SELECT * FROM `module`";

        try {
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();




        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectStudentsId(String moduleName){
        String select = "SELECT * FROM `studentModule` WHERE `moduleName` LIKE ?";

        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,moduleName);
            return statement.executeQuery();




        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectModuleInstructor(String instructorName){
        String select = "SELECT * FROM `instructorModule` WHERE `instructorName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, instructorName);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectStudentName(int studentId){
        String select = "SELECT * FROM `student` WHERE `studentId` LIKE ?";
        try {

            PreparedStatement statement = con.prepareStatement(select);
            statement.setInt(1, studentId);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectInstructorModule(String moduleName){
        String select = "SELECT * FROM `instructorModule` WHERE `moduleName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, moduleName);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public void increaseLevel(int studentId,String Level){
        String update = "UPDATE `student` SET `level` = ? WHERE `studentId` LIKE ? ";
        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1,Level);
            statement.setInt(2,studentId);
            statement.executeUpdate();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public ResultSet selectCourse(String  cancelStatus){
        String select = "SELECT * FROM `course` WHERE `cancelStatus` LIKE ?";

        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,cancelStatus);
            return statement.executeQuery();




        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public void setCancelStatus(String courseName){
        String set = "UPDATE `course` SET `cancelStatus` = ? WHERE `courseName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(set);
            statement.setBoolean(1,true);
            statement.setString(2,courseName);
            statement.executeUpdate();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public ResultSet selectInstructorModule(){
        String select = "SELECT * FROM `instructorModule`";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectModuleDetails(int studentID, int semester1, int semester2){
        String select = "SELECT * FROM `studentModule` WHERE `studentId` LIKE ? AND( `semester` LIKE ? OR `semester` LIKE ?)";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setInt(1,studentID);
            statement.setInt(2,semester1);
            statement.setInt(3,semester2);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectModule(int studentId){
        String select = "SELECT * FROM `studentModule`";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectModuleInfo(String moduleName){
        String select = "SELECT * FROM `module` WHERE `moduleName` LIKE ?";
            try {
                PreparedStatement statement = con.prepareStatement(select);
                statement.setString(1,moduleName);
                return statement.executeQuery();
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
                return null;
        }
    }


    public ResultSet selectInstructorInfo(int instructorId){
        String select = "SELECT * FROM `instructor` WHERE `instructorId` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setInt(1,instructorId);
            return statement.executeQuery();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }



    public  ResultSet selectCoreOrOptionalModule(String courseName,String courseType){
        if (courseType == "Core") {
            String select = "SELECT * FROM `module` WHERE `courseName` LIKE ? AND `moduleType` LIKE ?";
            try {
                PreparedStatement statement = con.prepareStatement(select);
                statement.setString(1, courseName);
                statement.setString(2, "Core");
                return statement.executeQuery();


            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                return null;
            }
        }else {
            String select = "SELECT * FROM `module` WHERE `courseName` LIKE ? AND `moduleType` LIKE ?";
            try {
                PreparedStatement statement = con.prepareStatement(select);
                statement.setString(1, courseName);
                statement.setString(2, "Optional");
                return statement.executeQuery();


            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                return null;
            }
        }
    }

    public ResultSet selectOptionalModule(String courseName, int semester){
        String select = "SELECT * FROM `module` WHERE `courseName` LIKE ? AND `moduleType` LIKE ? AND `semester` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, courseName);
            statement.setString(2, "Optional");
            statement.setInt(3, semester);
            return statement.executeQuery();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public void deleteModule(String moduleName){
        String delete = "DELETE FROM `module` WHERE `moduleName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setString(1, moduleName);
            statement.executeUpdate();
            statement.close();
            System.out.println("Successfully Deleted");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    public  void editModuleName(String newName, String name) {
        String update = "UPDATE `module` SET `moduleName` = ? WHERE `moduleName` LIKE ? ";
        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, newName);
            statement.setString(2, name);
            statement.executeUpdate();
            statement.close();
            System.out.println("Successfully Changed");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public ResultSet assignInstructor(String moduleName){
        String select = "SELECT * FROM `instructorModule` WHERE `moduleName` LIKE ? ORDER BY RAND() LIMIT 1";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, moduleName);
            return statement.executeQuery();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


    public ResultSet selectModules(String moduleName){
        String select = "SELECT * FROM `module` WHERE `moduleName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,moduleName);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectModule(String courseName){
        String select = "SELECT * FROM `module` WHERE `courseName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,courseName);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectModule(String courseName, int semester){
        String select = "SELECT * FROM `module` WHERE `courseName` LIKE ? AND `semester` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, courseName);
            statement.setInt(2, semester);
            return statement.executeQuery();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectCoreModule(String courseName, int semester){
        String select = "SELECT * FROM `module` WHERE `courseName` LIKE ? AND `semester` LIKE ? AND `moduleType` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, courseName);
            statement.setInt(2, semester);
            statement.setString(3,"Core");
            return statement.executeQuery();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectModuleInfo(int studentId, int semester){
        String select = "SELECT * FROM `studentmodule` WHERE `studentId` LIKE ? AND `semester` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setInt(1, studentId);
            statement.setInt(2, semester);
            return statement.executeQuery();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public ResultSet selectModuleInfo(int studentId){
        String select = "SELECT * FROM `studentmodule` WHERE `studentId` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setInt(1, studentId);
            return statement.executeQuery();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }



    //Select Student
    public ResultSet selectStudent(){
        String select = "SELECT * FROM `student`";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet selectStudent(String courseName){
        String select = "SELECT * FROM `student` WHERE `course` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,courseName);
            return statement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet selectStudentId(String studentName){
        String select = "SELECT * FROM `student` WHERE `studentName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,studentName);
            return statement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public void addCourse(String courseName){
        int enrolled_status = 0;
        String insert = "INSERT INTO `course`(`courseName`, `enrolledStatus`) VALUES (?, ?)";
        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, courseName);
            statement.setInt(2, enrolled_status);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    public void editCourseName(String newCourseName, String courseName){
        String updateCourse = "UPDATE `course` SET `courseName` = ? WHERE `courseName` LIKE ? ";
        String updateModule = "UPDATE `module` SET `courseName` = ? WHERE `courseName` LIKE ? ";
        try {
            PreparedStatement statement = con.prepareStatement(updateCourse);
            statement.setString(1, newCourseName);
            statement.setString(2,courseName);
            statement.executeUpdate();
            statement.close();
            System.out.println("Successfully Changed");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        try {
            PreparedStatement statement = con.prepareStatement(updateModule);
            statement.setString(1, newCourseName);
            statement.setString(2,courseName);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException sqlException){
        }

    }

    public  void updateMark(String studentId, String moduleName, String markSecured){
        String update = "UPDATE `studentmodule` SET `marksSecured` = ?  WHERE `moduleName` LIKE ? AND `studentId` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setDouble(1, Double.parseDouble(markSecured));
            statement.setString(2,moduleName);
            statement.setInt(3, Integer.parseInt(studentId));
            statement.executeUpdate();
            statement.close();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void updateCourseStatus(boolean value,String courseName){
        if (value == true){
            String update = "UPDATE `course` SET `enrolledStatus` = ?  WHERE `courseName` LIKE ?";
            try {
                PreparedStatement statement = con.prepareStatement(update);
                statement.setInt(1, 1);
                statement.setString(2,courseName);
                statement.executeUpdate();
                statement.close();
            }catch(SQLException sqlException){}

        }else{
            String update = "UPDATE `course` SET `enrolledStatus` = ?  WHERE `courseName` LIKE ?";
            try {
                PreparedStatement statement = con.prepareStatement(update);
                statement.setInt(1, 0);
                statement.setString(2,courseName);
                statement.executeUpdate();
                statement.close();
            }catch(SQLException sqlException){}

        }
    }

    public void deleteCourse(String courseName){
        String deleteCourse = "DELETE FROM `course` WHERE `courseName` LIKE ?";
        String deleteModule = "DELETE FROM `module` WHERE `courseName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(deleteCourse);
            statement.setString(1, courseName);
            statement.executeUpdate();
            statement.close();
            System.out.println("Successfully Deleted");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        try {
            PreparedStatement statement = con.prepareStatement(deleteModule);
            statement.setString(1, courseName);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException sqlException){
        }
    }

    //Add Models to courses
    public void addModule(String moduleId, String moduleName, String course, String moduleType,String semester){
        String insert = "INSERT INTO `module`(moduleCode, moduleName, courseName, moduleType, semester, credits) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, moduleId);
            statement.setString(2, moduleName);
            statement.setString(3, course);
            statement.setString(4, moduleType);
            statement.setString(5, semester);
            statement.setInt(6, 20);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
//            JOptionPane.showMessageDialog(null, "The Person already exists!");
//             System.out.println("Error in user table");
            sqlException.printStackTrace();
        }
}
    public ResultSet selectInstructorsModule(String instructorName){
        String select = "SELECT * FROM `instructorModule` WHERE `instructorName` LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, instructorName);
            return statement.executeQuery();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }
}
