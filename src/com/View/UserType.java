package com.View;

import com.View.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserType extends JFrame implements ActionListener {
    private JRadioButton student, instructor;
    private JButton next;
    private int InstructorID;


    UserType() {

        setTitle("Course Management System");
        setSize(300,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane();
        this.setLayout(null);
        JLabel label = new JLabel("Select your role:");
        Font font = new Font("Courier", Font.BOLD,18);
        label.setFont(font);
        label.setBounds(40,40,500,20);
        student = new JRadioButton("Student");
        instructor = new JRadioButton("Instructor");
//        admin = new JRadioButton("Admin");
        next = new JButton("Next");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(student);
        buttonGroup.add(instructor);
//        buttonGroup.add(admin);
        student.setBounds(40,80,100,20);
        instructor.setBounds(140,80,100,20);
//        admin.setBounds(240,50,100,20);

        student.addActionListener(this);
        instructor.addActionListener(this);
//        admin.addActionListener(this);
        next.addActionListener(this);
        this.add(student);
        this.add(instructor);
        this.add(label);
//        this.add(admin);

        this.setVisible(true);


    }
//    public void caseAdmin() {
//        JLabel label1 = new JLabel("AdminID");
//        JLabel label2 = new JLabel("Password");
//        JLabel label3 = new JLabel("For admin registration please enter admin id and password!!");
//        label1.setBounds(40, 150 , 100, 20);
//        label2.setBounds(40, 200, 100, 20);
//        label3.setBounds(20,100,500,20);
//        adminID = new JTextField();
//        adminID.setBounds(140, 150, 130, 25);
//        password = new JPasswordField();
//        password.setBounds(140, 200, 130, 25);
//        next.setBounds(150,300,70,20);
//        adminID.addActionListener(this);
//        password.addActionListener(this);
//        next.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource()==next){
//                    if(adminID.equals("admin") && (password.equals("admin"))) {
//                        this.dis
//                        Register register = new Register("admin","admin");
//                    }else{
//                        JOptionPane.showMessageDialog(null, "You don't have an access to create admin account!!", "Access denied!", JOptionPane.ERROR_MESSAGE);
//
//                    }
//
//
//                }
//            }
//        });
//        this.add(next);
//        this.add(label1);
//        this.add(label2);
//        this.add(label3);
//        this.add(adminID);
//        this.add(password);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
//         if (e.getSource()==admin){
//            this.dispose();
//            caseAdmin();
//            setVisible(true);
//             }
        if (e.getSource()==student){
            this.dispose();
            Register register = new Register();
        }else if (e.getSource()==instructor){
             this.dispose();
             Register register = new Register(InstructorID);
         }
    }
}


