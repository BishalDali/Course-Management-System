package com.View;

import com.Controller.ManageDbUtils;
import com.Model.Courses.Course;
import com.Model.Courses.Module;
import com.Model.MainBody.Instructor;
import com.Model.MainBody.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InstructorFrame extends JFrame {
    private JButton buttonName;

    public void createInstructorFrame(int instructorId,String password){
        Instructor instructor = new Instructor(instructorId,password);
        this.setTitle("COURSE MANAGEMENT SYSTEM");
        this.setSize(1440,1024);
        this.setResizable(false);
        this.setBackground(new Color(90,90,90));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane();
        JPanel panel1 = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(30,38,79));
                g.fillRect(0, 0, 1440, 80);

                g.fillRect(0,0,297,1024);

                g.setColor(new Color(0,181,226));
                g.fillRect(500,200,700,500);


            }
        };

        JLabel quote = new JLabel("Quote of the Day");
        quote.setBounds(750,220,700,30);
        quote.setFont(new Font("Roboto", Font.BOLD, 25));
        quote.setForeground(Color.white);

        JLabel yourLevel= new JLabel("Good things come to people who wait, but better things come ");
        JLabel yourLevel2 = new JLabel("to those who go out and get them.");
        yourLevel.setBounds(520,270,1200,120);
        yourLevel.setFont(new Font("Roboto", Font.ITALIC, 25));
        yourLevel.setForeground(Color.WHITE);
        yourLevel2.setBounds(650,320,1200,120);
        yourLevel2.setFont(new Font("Roboto", Font.ITALIC, 25));
        yourLevel2.setForeground(Color.WHITE);

        JLabel title = new JLabel(instructor.getInstructorName());
        title.setBounds(377,0,296,80);
        title.setFont(new Font("Roboto", Font.ITALIC, 30));
        title.setForeground(Color.WHITE);





        JButton title1 = new JButton("Instructor");
        title1.setBounds(0,0,297,80);
        title1.setForeground(new Color(0,50,120));
        title1.setBackground(new Color(30,38,79));
        title1.setBorder(null);
        title1.setFocusable(false);
        title1.setFont(new Font("Roboto",Font.BOLD, 36));


        JButton logOut = new JButton("LOG OUT");
        logOut.setBounds(59,770,228,53);
        buttonName = logOut;
        editButton(buttonName);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int option = JOptionPane.showConfirmDialog(null,"Are you sure that you want to log out",null,dialogButton);
                if (option == 0){
                    dispose();
                    new Login();
                }
            }
        });

        JButton home = new JButton("MY PROFILE");
        home.setBounds(59,94,228,53);
        buttonName = home;
        editButton(buttonName);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                AddProfileFrame(instructorId,password);
            }
        });




        JButton enrolledModules = new JButton("Grade Student");
        enrolledModules.setBounds(59,147,228,53);
        buttonName = enrolledModules;
        editButton(buttonName);
        enrolledModules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddInstructorModule(instructorId,password);
            }
        });


        this.add(quote);
        this.add(yourLevel2);
        this.add(yourLevel);
        this.add(title);
        this.add(title1);
        this.add(home);
        this.add(enrolledModules);
        this.add(logOut);
        this.add(panel1);

        this.setVisible(true);
    }

    public void BottomFramePanel(JFrame frameName, int id , String password){
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(30,38,79));
                g.fillRect(0, 735, 1440, 100);


            }
        };

        JButton mainMenu = new JButton("MAIN MENU");
        mainMenu.setBounds(600,750,228,53);
        buttonName = mainMenu;
        editButton(buttonName);
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameName.dispose();
                createInstructorFrame(id,password);
            }
        });
        frameName.add(mainMenu);
        frameName.add(panel);


        frameName.setVisible(true);


    }
    public void editButton(JButton buttonName){
        buttonName.setForeground(Color.WHITE);
        buttonName.setBackground(new Color(30,38,79));
        buttonName.setBorder(null);
        buttonName.setFocusable(false);
        buttonName.setHorizontalAlignment(SwingConstants.LEFT);
        buttonName.setFont(new Font("Roboto",Font.LAYOUT_LEFT_TO_RIGHT, 20));
    }
    public void AddProfileFrame(int instructorId, String password){
        Instructor instructor = new Instructor(instructorId,password);
        JFrame profileFrame = new JFrame();
        profileFrame.setTitle("COURSE MANAGEMENT SYSTEM");
        profileFrame.setSize(1440,1024);
        profileFrame.setResizable(false);
        profileFrame.setBackground(new Color(90,90,90));
        profileFrame.setLocationRelativeTo(null);
        profileFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        profileFrame.getContentPane();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("My Details");
        label.setBounds(600,20,300,30);
        label.setFont(new Font("Roboto",Font.BOLD,25));

        JLabel id = new JLabel("Student ID:  "+ instructor.getInstructorId());
        id.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
        id.setBounds(500,100,400,30);

        JLabel name = new JLabel("Student Name:  "+ instructor.getInstructorName());
        name.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
        name.setBounds(500,150,400,30);

        JLabel Address = new JLabel("Address:  "+instructor.getAddress());
        Address.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
        Address.setBounds(500,200,400,30);

        JLabel emailAddress = new JLabel("Email Address: "+instructor.getEmail());
        emailAddress.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
        emailAddress.setBounds(500,250,800,30);


        JLabel a = new JLabel("");

        profileFrame.add(id);
        profileFrame.add(emailAddress);
        profileFrame.add(Address);
        profileFrame.add(label);
        profileFrame.add(name);


        profileFrame.add(a);
        profileFrame.setVisible(true);
        BottomFramePanel(profileFrame,instructorId,password);
    }
    public  void AddInstructorModule(int instructorId, String password){
        Student student = new Student();
        Module module = new Module();
        Instructor instructor = new Instructor(instructorId,password);
        Course course = new Course();
        JFrame instructorModule = new JFrame();
        instructorModule.setTitle("COURSE MANAGEMENT SYSTEM");
        instructorModule.setSize(1440,1024);
        instructorModule.setResizable(false);
        instructorModule.setBackground(new Color(90,90,90));
        instructorModule.setLocationRelativeTo(null);
        instructorModule.setDefaultCloseOperation(EXIT_ON_CLOSE);
        instructorModule.getContentPane();

        JLabel label = new JLabel("");

        JComboBox<String> selectModules = new JComboBox<>(instructor.selectInstructorsModule(instructor.getInstructorName()));
        selectModules.setBounds(700,50,200,20);

        JLabel students =  new JLabel("Select Student:");
        students.setBounds(500,100,200,20);
        students.setFont(new Font("Roboto",Font.BOLD,20));

        JComboBox<String> selectStudents = new JComboBox<>();
        selectStudents.setBounds(700,100,200,20);

        selectModules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = student.selectStudentsId(selectModules.getSelectedItem().toString()).length;
                int[] id = student.selectStudentsId(selectModules.getSelectedItem().toString());
                for (int i = 0; i < num; i++) {
                    selectStudents.addItem(String.valueOf(id[i]));

                }
            }
        });





        JLabel Name = new JLabel("");
        JTextField moduleName = new JTextField();
        Name.setBounds(500,150,400,20);
        moduleName.setBounds(850,150,200,20);
        Name.setFont(new Font("Roboto",Font.CENTER_BASELINE,15));
        JButton button = new JButton("ADD MARK");
        button.setBounds(600,200,200,20);

        selectModules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = student.selectStudentsId(selectModules.getSelectedItem().toString()).length;
                int[] id = student.selectStudentsId(selectModules.getSelectedItem().toString());
                for (int i = 0; i < num; i++) {
                    selectStudents.addItem(String.valueOf(id[i]));

                }
                Name.setText(selectModules.getSelectedItem().toString());
            }
        });


        JLabel selectModule = new JLabel("Select your Module: ");
        selectModule.setBounds(500,50,200,20);
        selectModule.setFont(new Font("Roboto",Font.BOLD,20));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageDbUtils manageDbUtils = new ManageDbUtils();
                manageDbUtils.updateMark(selectStudents.getSelectedItem().toString(),selectModules.getSelectedItem().toString(),moduleName.getText());
                JOptionPane.showMessageDialog(null,"Success!!");
            }
        });

        instructorModule.add(button);
        instructorModule.add(selectStudents);
        instructorModule.add(students);
        instructorModule.add(selectModule);
        instructorModule.add(selectModules);
        instructorModule.add(Name);
        instructorModule.add(moduleName);
        instructorModule.add(label);
        instructorModule.setVisible(true);
        BottomFramePanel(instructorModule,instructorId,password);
    }


}

