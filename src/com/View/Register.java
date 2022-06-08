package com.View;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Controller.ManageDbUtils;
import com.Model.MainBody.Users;

public class Register extends JFrame{
    private JFrame registrationFrame = new JFrame();
    private JTextField GetName = new JTextField();
    private JTextField GetAddress = new JTextField();
    private JTextField GetEmailAddress = new JTextField();
    private JPasswordField GetPassword = new JPasswordField();
    private JPasswordField GetConfirmPassword = new JPasswordField();
    private Object[] Level = {"Four","Five","Six"};
    private JComboBox GetLevel = new JComboBox(Level);

    ManageDbUtils manageDbUtils = new ManageDbUtils();
    Users users = new Users();


    //Student Registration form
    Register(){
        //Creating Register frame
        String userType = "Student";
        setTitle("Course Management System");
        setSize(400,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane();
        this.setLayout(null);


        //Labels
        JLabel name1 = new JLabel("Full Name");
        name1.setBounds(20,50,120,20);
        JLabel address1 = new JLabel("Address");
        address1.setBounds(20,100,100,20);
        JLabel emailAddress1 = new JLabel("Email Address");
        emailAddress1.setBounds(20,150,100,20);
        JLabel password1 = new JLabel("Password");
        password1.setBounds(20,200,100,20);
        JLabel confirmPassword1 = new JLabel("Confirm Password");
        confirmPassword1.setBounds(20,250,120,20);
        JLabel level1 = new JLabel("Level");
        level1.setBounds(20,300,100,20);
        JLabel course1 = new JLabel("Course");
        course1.setBounds(20,350,100,20);
        this.add(name1);
        this.add(address1);
        this.add(emailAddress1);
        this.add(level1);
        this.add(course1);
        this.add(password1);
        this.add(confirmPassword1);

// Adding Courses from database
        ResultSet availableCourse = manageDbUtils.selectCourse("false");
        int i=0;

        try{
            while (availableCourse.next()){
                i++;
            }}catch(SQLException e){
            e.printStackTrace();
        }
        availableCourse = manageDbUtils.selectCourse("false");
        System.out.println(i);
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




         JComboBox<String > GetCourse = new JComboBox<>(courses);
        //Text fields
        GetName.setBounds(150,50,150,20);
        GetAddress.setBounds(150,100,150,20);
        GetEmailAddress.setBounds(150,150,150,20);
        GetPassword.setBounds(150,200,150,20);
        GetConfirmPassword.setBounds(150,250,150,20);
        GetLevel.setBounds(150,300,150,20);
        GetCourse.setBounds(150,350,150,20);

        this.add(GetName);
        this.add(GetAddress);
        this.add(GetEmailAddress);
        this.add(GetLevel);
        this.add(GetCourse);
        this.add(GetPassword);
        this.add(GetConfirmPassword);



        //Button
        JButton register = new JButton("Register");
        register.setBounds(140,400,120,20);
        this.add(register);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String name = GetName.getText();
                 String address = GetAddress.getText();
                 String emailAddress = GetEmailAddress.getText();
                 String password = String.valueOf(GetPassword.getPassword());
                 String confirmPassword = String.valueOf(GetConfirmPassword.getPassword());
                 String level = GetLevel.getSelectedItem().toString();
                 String course = GetCourse.getSelectedItem().toString();

                if (name.length()==0 || address.length()==0 || password.length()==0 || emailAddress.length()==0) {
                    JOptionPane.showMessageDialog(null,"Please fill all the empty box!!", "Empty Information!!",JOptionPane.ERROR_MESSAGE);
                    return;

                }else{
                    if (password.equals(confirmPassword)){
                        try {
                            int StudentID = users.GenerateID();
                            int ID = StudentID;
                            manageDbUtils.saveUserDetails(ID, password, userType);
                            boolean error = manageDbUtils.saveStudentDetails(StudentID, name, emailAddress, course, address, level);
                            manageDbUtils.updateCourseStatus(true, course);
                            if (!error) {
                                JOptionPane.showMessageDialog(null, "Your Account is successfully created!!! \n" + ID + " is your userID.");

                                dispose();
                                new SelectModules(StudentID,course);
                            }
                        } catch (Exception ex) {
                            System.out.println("Account couldn't be registered.");
                        }
                    }
                    }

            }
        });

        setVisible(true);

    }





    //Instructor Registration form
    Register(int InstructorID){
        // Instructor's Frame
        String userType = "Instructor";
        setTitle("Instructor's Registration");
        setSize(350,450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane();
        this.setLayout(null);


        JLabel name1 = new JLabel("Full Name");
        name1.setBounds(20,50,120,20);
        JLabel address1 = new JLabel("Address");
        address1.setBounds(20,100,100,20);
        JLabel emailAddress1 = new JLabel("Email Address");
        emailAddress1.setBounds(20,150,100,20);
        JLabel password1 = new JLabel("Password");
        password1.setBounds(20,200,100,20);
        JLabel confirmPassword1 = new JLabel("Confirm Password");
        confirmPassword1.setBounds(20,250,120,20);

        //Text fields
        GetName.setBounds(150,50,150,20);
        GetAddress.setBounds(150,100,150,20);
        GetEmailAddress.setBounds(150,150,150,20);
        GetPassword.setBounds(150,200,150,20);
        GetConfirmPassword.setBounds(150,250,150,20);


        this.add(name1);
        this.add(address1);
        this.add(emailAddress1);
        this.add(password1);
        this.add(confirmPassword1);
        this.add(GetName);
        this.add(GetAddress);
        this.add(GetEmailAddress);
        this.add(GetPassword);
        this.add(GetConfirmPassword);




        //Button
        JButton register = new JButton("Register");
        register.setBounds(100,300,120,20);
        this.add(register);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = GetName.getText();
                String address = GetAddress.getText();
                String emailAddress = GetEmailAddress.getText();
                String password = String.valueOf(GetPassword.getPassword());
                String confirmPassword = String.valueOf(GetConfirmPassword.getPassword());


                if (name.length() == 0 || address.length() == 0 || password.length() == 0 || emailAddress.length() == 0) {
                    JOptionPane.showMessageDialog(null,"Please fill all the empty box!!", "Empty Information!!",JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    if(password.equals(confirmPassword)){
                        try {
                            int InstructorID = users.GenerateID();
                            int ID = InstructorID;
                            manageDbUtils.saveUserDetails(ID, password, userType);
                            boolean error = manageDbUtils.saveInstructorDetails(InstructorID, name, address, emailAddress );
                            manageDbUtils.saveInstructorDetails(name);
                            if (!error) {
                                JOptionPane.showMessageDialog(null, "Your Account is successfully created!!! \n" + ID + " is your userID.");
                                dispose();
                                new Login();
                            }
                        } catch (Exception exception) {
                            System.out.println("Account couldn't be registered.");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Please Enter the Matching Password!!", "Password Mismatched!!", JOptionPane.ERROR_MESSAGE);
                    }
                    }
            }
        });



        setVisible(true);
    }



}