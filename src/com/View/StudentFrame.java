package com.View;

import com.Controller.ManageDbUtils;
import com.Model.Courses.Course;
import com.Model.Courses.Module;
import com.Model.MainBody.Instructor;
import com.Model.MainBody.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

public class StudentFrame extends JFrame {
    private JButton buttonName;

    public void createStudentFrame(int studentId,String password){
        Module module = new Module();
        Student student = new Student(studentId,password);
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

        JLabel title = new JLabel(student.getStudentName());
        title.setBounds(377,0,296,80);
        title.setFont(new Font("Roboto", Font.ITALIC, 30));
        title.setForeground(Color.WHITE);


        JButton nextLevel =  new JButton("Next Level");
        nextLevel.setBounds(700,750,200,20);
        nextLevel.setFocusable(false);
        nextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] grades1;
                String [] grades2;
                Boolean pass;
                int semester1 = 0, semester2 = 0;
                if (student.getLevel().equals("Four")){
                    semester1 = 1;
                    semester2 = 2;
                }else if(student.getLevel().equals("Five")){
                    semester1=3;
                    semester2=4;
                }else if (student.getLevel().equals("Six")){
                    semester1=5;
                    semester2=6;
                }
                grades1 = module.selectGrade(studentId, semester1);
                grades2 = module.selectGrade(studentId, semester1);
                try {
                    if (Double.parseDouble(grades1[0]) >=40 && Double.parseDouble(grades1[1])>=40 && Double.parseDouble(grades1[2])>=40){
                        pass=true;
                    }else{
                        pass=false;
                    }
                    if (Double.parseDouble(grades2[0]) >=40 && Double.parseDouble(grades2[1])>=40 && Double.parseDouble(grades2[2])>=40){
                        pass=true;
                    }else{
                        pass=false;
                    }
                    ManageDbUtils manageDbUtils = new ManageDbUtils();
                    if (pass== true){
                        if (student.getLevel().equals("Four")){
                            manageDbUtils.increaseLevel(studentId,"Five");
                            JOptionPane.showMessageDialog(null,"Congratulation! You have been promoted to Level Five!");
                            dispose();
                            createStudentFrame(studentId,password);
                        }else if(student.getLevel().equals("Five")){
                            manageDbUtils.increaseLevel(studentId,"Six");
                            JOptionPane.showMessageDialog(null,"Congratulation! You have been promoted to Level Six!");
                            dispose();
                            createStudentFrame(studentId,password);
                        }else{
                            manageDbUtils.increaseLevel(studentId,"Graduated");
                            JOptionPane.showMessageDialog(null,"Congratulation! You have Graduated!");
                            dispose();
                            createStudentFrame(studentId,password);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"You do not meet requirement to be promoted!!",null, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null,"You are not Graded Yet!!",null,JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        JButton title1 = new JButton("STUDENT");
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
                AddProfileFrame(studentId,password);
            }
        });




        JButton enrolledModules = new JButton("ENROLLED MODULES");
        enrolledModules.setBounds(59,147,228,53);
        buttonName = enrolledModules;
        editButton(buttonName);
        enrolledModules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddModuleEnrolledFrame(studentId,password);
            }
        });

        JButton moduleDetails = new JButton("Module Details");
        moduleDetails.setBounds(59,200,228,53);
        buttonName = moduleDetails;
        editButton(buttonName);
        moduleDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddModuleDetailFrame(studentId,password);
            }
        });
        this.add(nextLevel);
        this.add(quote);
        this.add(yourLevel2);
        this.add(yourLevel);
        this.add(title);
        this.add(title1);
        this.add(home);
        this.add(enrolledModules);
        this.add(moduleDetails);
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
                createStudentFrame(id,password);
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

   public void AddProfileFrame(int studentId, String password){
       Student student = new Student(studentId,password);
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

       JLabel id = new JLabel("Student ID:  "+ student.getStudentId());
       id.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
       id.setBounds(500,100,400,30);

       JLabel name = new JLabel("Student Name:  "+ student.getStudentName());
       name.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
       name.setBounds(500,150,400,30);

       JLabel Address = new JLabel("Address:  "+student.getAddress());
       Address.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
       Address.setBounds(500,200,400,30);

       JLabel emailAddress = new JLabel("Email Address: "+student.getEmailAddress());
       emailAddress.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
       emailAddress.setBounds(500,250,800,30);

       JLabel courseEnrolled = new JLabel("Course Enrolled:  "+ student.getCourse());
       courseEnrolled.setFont(new Font("Roboto",Font.CENTER_BASELINE,25));
       courseEnrolled.setBounds(500,300,800,30);

       JLabel a = new JLabel("");

       profileFrame.add(id);
       profileFrame.add(courseEnrolled);
       profileFrame.add(emailAddress);
       profileFrame.add(Address);
       profileFrame.add(label);
       profileFrame.add(name);


       profileFrame.add(a);
       profileFrame.setVisible(true);
       BottomFramePanel(profileFrame,studentId,password);
   }
    public  void AddModuleEnrolledFrame(int studentId, String password){
        Student student = new Student(studentId,password);
        Course course = new Course();
        JFrame moduleEnrolledFrame = new JFrame();
        moduleEnrolledFrame.setTitle("COURSE MANAGEMENT SYSTEM");
        moduleEnrolledFrame.setSize(1440,1024);
        moduleEnrolledFrame.setResizable(false);
        moduleEnrolledFrame.setBackground(new Color(90,90,90));
        moduleEnrolledFrame.setLocationRelativeTo(null);
        moduleEnrolledFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        moduleEnrolledFrame.getContentPane();

        JLabel label = new JLabel("");
        Object[] columnName = {"Level","Module Name","Semester","Enrolled Status"};
        String[][] rowData = student.getStudentModule(student.getLevel(),studentId);

        JTable table = new JTable(7,4);
        ScrollPane scrollPane = new ScrollPane();

        DefaultTableModel model = new DefaultTableModel(rowData, columnName);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        table.setAutoResizeMode( JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setCellSelectionEnabled(false);
        scrollPane.setBounds(300,200,800,185);

        JLabel level = new JLabel("Level");
        JLabel moduleName = new JLabel("Module Name");
        JLabel semester = new JLabel("Semester");
        JLabel status = new JLabel("Enrolled Status");
        level.setBounds(300,170,200,20);
        semester.setBounds(700,170,200,20);
        moduleName.setBounds(500,170,200,20);
        status.setBounds(900,170,200,20);
        level.setFont(new Font("Roboto",Font.CENTER_BASELINE,20));
        semester.setFont(new Font("Roboto",Font.CENTER_BASELINE,20));
        moduleName.setFont(new Font("Roboto",Font.CENTER_BASELINE,20));
        status.setFont(new Font("Roboto",Font.CENTER_BASELINE,20));


        JLabel title = new JLabel("ENROLLED MODULES");
        title.setBounds(500,30,800,30);
        title.setFont(new Font("Roboto",Font.BOLD,30));

        table.setModel(model);
        scrollPane.add(table);
        moduleEnrolledFrame.add(title);
        moduleEnrolledFrame.add(status);
        moduleEnrolledFrame.add(level);
        moduleEnrolledFrame.add(semester);
        moduleEnrolledFrame.add(moduleName);
        moduleEnrolledFrame.add(scrollPane);
        moduleEnrolledFrame.add(label);
        moduleEnrolledFrame.setVisible(true);
        BottomFramePanel(moduleEnrolledFrame,studentId,password);
    }
    public  void AddModuleDetailFrame(int studentId, String password){
        Student student = new Student(studentId, password);
        Instructor ins = new Instructor();
        Module module = new Module();
        JFrame moduleDetailFrame = new JFrame();
        moduleDetailFrame.setTitle("COURSE MANAGEMENT SYSTEM");
        moduleDetailFrame.setSize(1440,1024);
        moduleDetailFrame.setResizable(false);
        moduleDetailFrame.setBackground(new Color(90,90,90));
        moduleDetailFrame.setLocationRelativeTo(null);
        moduleDetailFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        moduleDetailFrame.getContentPane();
        JLabel label = new JLabel("");

        JLabel selectModule = new JLabel("Select Module:");
        selectModule.setBounds(500,20,200,20);
        selectModule.setFont(new Font("Roboto",Font.BOLD,20));

        JComboBox<String> modules = new JComboBox<>(module.getStudentModule(student.getLevel(),studentId));
        modules.setBounds(650,20,200,20);

        JLabel moduleCode = new JLabel("Module Code: ");
        JLabel moduleName = new JLabel("Module Name : ");
        JLabel instructor = new JLabel("Assigned Instructor : ");
        moduleCode.setBounds(400,120,800,30);
        moduleName.setBounds(400,170,800,30);
        instructor.setBounds(400,220,800,30);
        moduleCode.setFont(new Font("Roboto",Font.BOLD,20));
        moduleName.setFont(new Font("Roboto",Font.BOLD,20));
        instructor.setFont(new Font("Roboto",Font.BOLD,20));

        modules.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String[] code = module.moduleInfo(modules.getSelectedItem().toString());
                moduleName.setText("Module Name: "+ modules.getSelectedItem().toString());
                moduleCode.setText("Module Code: " + code[0]);
                instructor.setText("Assigned Instructor: " + ins.assignInstructor(modules.getSelectedItem().toString()));
            }
        });



        moduleDetailFrame.add(moduleName);
        moduleDetailFrame.add(moduleCode);
        moduleDetailFrame.add(instructor);

        moduleDetailFrame.add(modules);
        moduleDetailFrame.add(selectModule);
        moduleDetailFrame.add(label);
        moduleDetailFrame.setVisible(true);
        BottomFramePanel(moduleDetailFrame,studentId,password);
    }

}
