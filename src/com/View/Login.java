package com.View;

import com.Controller.ManageDbUtils;
import com.Model.Courses.Course;
import com.Model.MainBody.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Login extends JFrame implements ActionListener{
    private JFrame frame = new JFrame();
    private JTextField usernameTextField = new JTextField(20);
    private JPasswordField passwordTextField = new JPasswordField(20);
    private String[] userTypes = {"Student", "Teacher", "Administrator"};
    private JComboBox<String> selectUserType = new JComboBox<>(userTypes);
    private JButton registerBtn;
    private JButton loginBtn;
    private Object[] Users = {"Student","Instructor","Admin"};;
    private JComboBox userType = new JComboBox<>(Users);

    Container c;


    public Login(){

        //Creating Login form
        setTitle("Course Management System");
        setSize(400,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        c = getContentPane();
        c.setLayout(null);

        //Labels
        JLabel label1 = new JLabel("UserID");
        JLabel label2 = new JLabel("Password");
        JLabel label3 = new JLabel("User Type");
        label1.setBounds(20,50,100,20);
        label2.setBounds(20,100,100,20);
        label3.setBounds(20,150,100,20);
        c.add(label1);
        c.add(label2);
        c.add(label3);

        //Text & Password fields
        usernameTextField.setBounds(120,50,130,25);
        c.add(usernameTextField);
        passwordTextField.setBounds(120,100,130,25);
        c.add(passwordTextField);
        userType.setBounds(120,150,130,25);
        c.add(userType);
        Course course = new Course();
        course.cancelCourse();


        //Buttons
        loginBtn = new JButton("Login");
        loginBtn.setBounds(50,200,90,20);
        loginBtn.addActionListener(this);
        c.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = usernameTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());
                String UserType = userType.getSelectedItem().toString();
                if (userID.length()==0 || password.length() == 0){
                     JOptionPane.showMessageDialog(null,"Please fill the box with your UserID and Password!!", "EmptyBox",JOptionPane.ERROR_MESSAGE);

                    }else{
                         ManageDbUtils manageDbUtils = new ManageDbUtils();
                       String RealPassword = manageDbUtils.CheckLoginDetails(userID,UserType);
                       if (UserType == "Admin") {
                           if (RealPassword.equals(password)) {
                               dispose();
                               AdminFrame adminFrame = new AdminFrame();
                               adminFrame.createAdminFrame();
                           } else {
                               JOptionPane.showMessageDialog(null, "Invalid Password!!", "Password Incorrect", JOptionPane.ERROR_MESSAGE);
                           }
                       }else if(UserType == "Student"){
                           if (RealPassword.equals(password)) {
                               dispose();
                               int UserId = Integer.parseInt(userID);
                               StudentFrame studentFrame = new StudentFrame();
                               studentFrame.createStudentFrame(UserId,password);
                           } else {
                               JOptionPane.showMessageDialog(null, "Invalid Password!!", "Password Incorrect", JOptionPane.ERROR_MESSAGE);
                           }
                       }else if (UserType == "Instructor"){
                           if (RealPassword.equals(password)) {
                               dispose();
                                InstructorFrame instructorFrame = new InstructorFrame();
                                instructorFrame.createInstructorFrame(Integer.parseInt(userID),password);
                           } else {
                               JOptionPane.showMessageDialog(null, "Invalid Password!!", "Password Incorrect", JOptionPane.ERROR_MESSAGE);
                           }
                       }
                    }

                }

        });

        registerBtn = new JButton("Register");
        registerBtn.setBounds(200,200,90,20);
        registerBtn.addActionListener(this);
        c.add(registerBtn);


        setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==registerBtn){
            this.dispose();
            UserType userType = new UserType();

        }
    }

}


