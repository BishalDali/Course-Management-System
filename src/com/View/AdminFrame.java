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
import java.sql.ResultSet;
import java.sql.SQLException;


    public class AdminFrame extends JFrame {
        private JFrame frame = new JFrame();

        private JButton buttonName;
        private ManageDbUtils manageDbUtils = new ManageDbUtils();
        private Course course = new Course();
        private Module module = new Module();

        public AdminFrame(){

        }
        public  void createAdminFrame(){
            AdminFrame adminFrame = new AdminFrame();
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

                    g.setColor(new Color(135,206,235));
                    g.fillRect(500,300,350,350);

                    g.setColor(new Color(144,238,144));
                    g.fillRect(900,300,350,350);


                }
            };
            JLabel title = new JLabel("Course Management System");
            title.setBounds(377,0,296,80);
            title.setFont(new Font("Serif", Font.BOLD, 24));
            title.setForeground(Color.WHITE);

            JLabel NoOfStudents = new JLabel("Total number of Students");
            NoOfStudents.setBounds(520,320,350,100);
            NoOfStudents.setFont(new Font("Roboto",Font.ITALIC,28));
            NoOfStudents.setForeground(Color.WHITE);

            //Select number of Students
            ResultSet selectedStudents = manageDbUtils.selectStudent();
            int count=0;
            try{
                while(selectedStudents.next()){
                    count++;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }


            JLabel totalNumbers = new JLabel(String.valueOf(count));
            totalNumbers.setBounds(650,400,200,200);
            totalNumbers.setFont(new Font("Roboto",Font.BOLD,75));
            totalNumbers.setForeground(Color.WHITE);



            JLabel NoOfCourses = new JLabel("Total number of Courses");
            NoOfCourses.setBounds(920,320,350,100);
            NoOfCourses.setFont(new Font("Roboto",Font.ITALIC,28));
            NoOfCourses.setForeground(Color.WHITE);

            //Selecting number of courses
            ResultSet courseSelected = manageDbUtils.selectCourse();
            count =0;
            try {
                while(courseSelected.next()){
                    count++;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            JLabel courseNumbers = new JLabel(String.valueOf(count));
            courseNumbers.setBounds(1050,400,200,200);
            courseNumbers.setFont(new Font("Roboto",Font.BOLD,75));
            courseNumbers.setForeground(Color.WHITE);



            JButton title1 = new JButton("ADMIN");
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

            JButton home = new JButton("Edit Student's Grade");
            home.setBounds(59,94,228,53);
            buttonName = home;
            editButton(buttonName);
            home.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    dispose();
                    adminFrame.AddEditStudentsGradeFrame();
                }
            });




            JButton result = new JButton("Students Result");
            result.setBounds(59,147,228,53);
            buttonName = result;
            editButton(buttonName);
            result.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    adminFrame.AddStudentResultFrame();
                }
            });

            JButton editCourse = new JButton("Edit Course");
            editCourse.setBounds(59,200,228,53);
            buttonName = editCourse;
            editButton(buttonName);
            editCourse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    adminFrame.AddEditCourseFrame();
                }
            });

            JButton editModule = new JButton("Edit Module");
            editModule.setBounds(59,253,228,53);
            buttonName = editModule;
            editButton(buttonName);
            editModule.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    adminFrame.AddEditModuleFrame();
                }
            });

            JButton editInstructor = new JButton("Edit Instructor");
            editInstructor.setBounds(59,306,228,53);
            buttonName = editInstructor;
            editButton(buttonName);
            editInstructor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    adminFrame.AddEditInstructorFrame();
                }
            });

            this.add(title);
            this.add(title1);
            this.add(home);
            this.add(result);
            this.add(editCourse);
            this.add(editModule);
            this.add(editInstructor);
            this.add(NoOfStudents);
            this.add(totalNumbers);
            this.add(NoOfCourses);
            this.add(courseNumbers);
            this.add(logOut);
            this.add(panel1);

            this.setVisible(true);
        }



        public void editButton(JButton buttonName){
            buttonName.setForeground(Color.WHITE);
            buttonName.setBackground(new Color(30,38,79));
            buttonName.setBorder(null);
            buttonName.setFocusable(false);
            buttonName.setHorizontalAlignment(SwingConstants.LEFT);
            buttonName.setFont(new Font("Roboto",Font.LAYOUT_LEFT_TO_RIGHT, 20));
        }
        public void BottomFramePanel(JFrame frameName){
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
                    AdminFrame adminFrame = new AdminFrame();
                    adminFrame.createAdminFrame();
                }
            });
            frameName.add(mainMenu);
            frameName.add(panel);


            frameName.setVisible(true);


        }

        public void AddEditStudentsGradeFrame(){
            Student student = new Student();
            JFrame editGradeFrame = new JFrame();
            editGradeFrame.setTitle("COURSE MANAGEMENT SYSTEM");
            editGradeFrame.setSize(1440,1024);
            editGradeFrame.setResizable(false);
            editGradeFrame.setBackground(new Color(90,90,90));
            editGradeFrame.setLocationRelativeTo(null);
            editGradeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            editGradeFrame.getContentPane();

            JLabel name = new JLabel("Select Student:");
            name.setBounds(400,20,200,20);
            name.setFont(new Font("Roboto",Font.LAYOUT_RIGHT_TO_LEFT,20));

            JComboBox<String > selectStudent = new JComboBox<>(student.selectStudent());
            selectStudent.setBounds(600,20,200,20);

            JLabel choose = new JLabel("Choose Semester:");
            choose.setBounds(400,60,200,20);
            choose.setFont(new Font("Roboto",Font.LAYOUT_RIGHT_TO_LEFT,20));

            Object[] semester = {1,2,3,4,5,6};
            JComboBox selectSemester = new JComboBox(semester);
            selectSemester.setBounds(600,60,200,20);
            JLabel moduleName = new JLabel("Module Names");

            moduleName.setFont(new Font("Roboto",Font.BOLD,20));
            JLabel marks = new JLabel("Marks");
            marks.setFont(new Font("Roboto",Font.BOLD,20));
            moduleName.setBounds(400,170,200,20);
            marks.setBounds(650,170,200,20);

            JTextField module1 = new JTextField();
            JTextField module2 = new JTextField();
            JTextField module3 = new JTextField();
            JTextField textField1 = new JTextField();
            JTextField textField2 = new JTextField();
            JTextField textField3 = new JTextField();

            module1.setBounds(400 , 200, 250, 20);
            module2.setBounds(400 , 250, 250, 20);
            module3.setBounds(400, 300 , 250, 20);
            textField1.setBounds(700,200,250,20);
            textField2.setBounds(700,250,250,20);
            textField3.setBounds(700,300,250,20);

            module1.setEditable(false);
            module2.setEditable(false);
            module3.setEditable(false);

            JButton next = new JButton("Next");
            next.setBounds(530,100,150,20);
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int studentID = student.selectStudentId(selectStudent.getSelectedItem().toString());
                    Module module = new Module();
                    String[]  grades = new String[3];
                    String[] modules = new String[3];
                    modules = module.selectModule(studentID, (Integer) selectSemester.getSelectedItem());
                    grades = module.selectGrade(studentID, (Integer) selectSemester.getSelectedItem());

                    module1.setText(modules[0]);
                    module2.setText(modules[1]);
                    module3.setText(modules[2]);
                    textField1.setText(grades[0]);
                    textField2.setText(grades[1]);
                    textField3.setText(grades[2]);

                }

            });

            JButton save = new JButton("Save");
            save.setBounds(550,350,200,20);
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int studentID = student.selectStudentId(selectStudent.getSelectedItem().toString());
                    manageDbUtils.updateMark(String.valueOf(studentID),module1.getText(),textField1.getText());
                    manageDbUtils.updateMark(String.valueOf(studentID),module2.getText(),textField2.getText());
                    manageDbUtils.updateMark(String.valueOf(studentID),module3.getText(),textField3.getText());
                    JOptionPane.showMessageDialog(null,"Saved Successfully!");
                }
            });

            editGradeFrame.add(save);
            editGradeFrame.add(moduleName);
            editGradeFrame.add(marks);
            editGradeFrame.add(module1);
            editGradeFrame.add(module2);
            editGradeFrame.add(module3);
            editGradeFrame.add(textField1);
            editGradeFrame.add(textField2);
            editGradeFrame.add(textField3);

            editGradeFrame.add(next);
            editGradeFrame.add(choose);
            editGradeFrame.add(selectSemester);
            editGradeFrame.add(selectStudent);
            editGradeFrame.add(name);
            BottomFramePanel(editGradeFrame);
            editGradeFrame.setVisible(true);

        }

        public void AddStudentResultFrame(){
            Student student = new Student();
            JFrame resultFrame = new JFrame();
            resultFrame.setTitle("COURSE MANAGEMENT SYSTEM");
            resultFrame.setSize(1440,1024);
            resultFrame.setResizable(false);
            resultFrame.setBackground(new Color(90,90,90));
            resultFrame.setLocationRelativeTo(null);
            resultFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            resultFrame.getContentPane();

            JLabel selectStudent = new JLabel("Select Student:");
            selectStudent.setBounds(300,20,200,20);
            selectStudent.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox<String > selectStudents = new JComboBox<>(student.selectStudent());
            selectStudents.setBounds(500,20,200,20);


            JLabel choose = new JLabel("Choose Semester:");
            choose.setBounds(750,20,200,20);
            choose.setFont(new Font("Roboto",Font.BOLD,20));



            Object[] semester = {1,2,3,4,5,6};
            JComboBox Semester = new JComboBox<>(semester);
            Semester.setBounds(950,20,200,20);


            JLabel module = new JLabel("Module Name");
            module.setBounds(320,120,300,30);
            module.setFont(new Font("Roboto",Font.BOLD,20));
            JLabel mark = new JLabel("Marks Obtained");
            mark.setBounds(620,120,300,30);
            mark.setFont(new Font("Roboto",Font.BOLD,20));
            JLabel status = new JLabel("Status");
            status.setBounds(920,120,300,30);
            status.setFont(new Font("Roboto",Font.BOLD,20));
            JTextField moduleName1 =new JTextField();
            moduleName1.setBounds(300,150,300,30);
            JTextField moduleName2 =new JTextField();
            moduleName2.setBounds(300,180,300,30);
            JTextField moduleName3 =new JTextField();
            moduleName3.setBounds(300,210,300,30);
            JTextField mark1 = new JTextField();
            mark1.setBounds(600,150,300,30);
            JTextField mark2 = new JTextField();
            mark2.setBounds(600,180,300,30);
            JTextField mark3 = new JTextField();
            mark3.setBounds(600,210,300,30);
            JTextField status1 = new JTextField();
            status1.setBounds(900,150,300,30);
            JTextField status2 = new JTextField();
            status2.setBounds(900,180,300,30);
            JTextField status3 = new JTextField();
            status3.setBounds(900,210,300,30);

            JLabel eligible = new JLabel("Remark :");
            eligible.setBounds(550,600,600,30);
            eligible.setFont(new Font("Roboto",Font.BOLD,20));


            moduleName1.setEditable(false);
            moduleName2.setEditable(false);
            moduleName3.setEditable(false);
            mark1.setEditable(false);
            mark2.setEditable(false);
            mark3.setEditable(false);
            status1.setEditable(false);
            status2.setEditable(false);
            status3.setEditable(false);


            JButton GenerateResult = new JButton("Generate Result");
            GenerateResult.setBounds(600,70,200,20);
            GenerateResult.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int studentID = student.selectStudentId(selectStudents.getSelectedItem().toString());
                    Module module = new Module();
                    String[] grades = new String[3];
                    String[] modules = new String[3];
                    String[] Status = new String[3];

                    modules = module.selectModule(studentID, (Integer) Semester.getSelectedItem());
                    grades = module.selectGrade(studentID, (Integer) Semester.getSelectedItem());

                    if (grades[0] == null||grades[1] == null||grades[2] == null){
                        JOptionPane.showMessageDialog(null,"The student has not been graded yet!",null,JOptionPane.ERROR_MESSAGE);
                    }else{
                    if (40<=Double.parseDouble(grades[0])){
                       status1.setText("Pass");
                        }else{
                        status1.setText("Fail");
                    }
                    if (40<=Double.parseDouble(grades[1])){
                        status2.setText("Pass");
                    }else{
                        status2.setText("Fail");
                    }
                    if (40<=Double.parseDouble(grades[2])){
                        status3.setText("Pass");
                    }else{
                        status3.setText("Fail");
                    }}

                    if (student.selectRemark(selectStudents.getSelectedItem().toString())==null){
                        eligible.setText("Remark: No remarks added yet" );
                    }else {
                        eligible.setText("Remark: " + student.selectRemark(selectStudents.getSelectedItem().toString()));
                    }
                    moduleName1.setText(modules[0]);
                    moduleName2.setText(modules[1]);
                    moduleName3.setText(modules[2]);
                    mark1.setText(grades[0]);
                    mark2.setText(grades[1]);
                    mark3.setText(grades[2]);
                    }

            });
            JLabel r = new JLabel("");

            resultFrame.add(eligible);
            resultFrame.add(selectStudent);
            resultFrame.add(selectStudents);
            resultFrame.add(choose);
            resultFrame.add(Semester);
            resultFrame.add(moduleName1);
            resultFrame.add(moduleName2);
            resultFrame.add(moduleName3);
            resultFrame.add(mark1);
            resultFrame.add(mark2);
            resultFrame.add(mark3);
            resultFrame.add(status1);
            resultFrame.add(status2);
            resultFrame.add(status3);
            resultFrame.add(module);
            resultFrame.add(status);
            resultFrame.add(mark);






            resultFrame.add(GenerateResult);

            resultFrame.add(r);



            resultFrame.setVisible(true);
            BottomFramePanel(resultFrame);
        }

        public void AddEditCourseFrame(){

            String[] courses = course.selectCourse();
            JFrame courseFrame = new JFrame();
            courseFrame.setTitle("COURSE MANAGEMENT SYSTEM");
            courseFrame.setSize(1440,1024);
            courseFrame.setResizable(false);
            courseFrame.setBackground(new Color(90,90,90));
            courseFrame.setLocationRelativeTo(null);
            courseFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            courseFrame.getContentPane();



            JLabel addOrRemoveCourse = new JLabel("Add Or Delete The Course");
            addOrRemoveCourse.setBounds(20,50,600,50);
            addOrRemoveCourse.setFont(new Font("Roboto",Font.BOLD,40));



            JLabel addCourse = new JLabel("Add New Course:");
            addCourse.setBounds(20,150,200,20);
            addCourse.setFont(new Font("Roboto",Font.BOLD,20));

            JTextField AddCourse = new JTextField();
            AddCourse.setBounds(200,145,200,30);

            JButton Add =new JButton("Add");
            Add.setBounds(420,145,200,30);
            Add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageDbUtils.addCourse(AddCourse.getText());
                    courseFrame.dispose();
                    AddEditCourseFrame();
                }
            });

            JLabel deleteCourse = new JLabel("Delete Course:");
            deleteCourse.setBounds(660,150,200,20);
            deleteCourse.setFont(new Font("Roboto",Font.BOLD,20));



            JComboBox<String> DeleteCourse = new JComboBox<>(courses);
            deleteCourse.setBackground(Color.WHITE);
            DeleteCourse.setBounds(860,145,200,30);


            JButton delete = new JButton("Delete");
            delete.setBounds(1100,145,200,30);
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageDbUtils.deleteCourse(DeleteCourse.getSelectedItem().toString());
                    courseFrame.dispose();
                    AddEditCourseFrame();
                }
            });


            JLabel cancelCourse = new JLabel("Cancel the course");
            cancelCourse.setBounds(20,200,600,100);
            cancelCourse.setFont(new Font("Roboto",Font.BOLD,40));

            JLabel cancelCourseLabel = new JLabel("Select course to cancel:");
            cancelCourseLabel.setBounds(20,300,300,20);
            cancelCourseLabel.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox<String> selectCourse = new JComboBox<>(courses);
            selectCourse.setBounds(270,295,200,30);


            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(500,295,200,30);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });


            JLabel editCourse = new JLabel("Edit Course Name");
            editCourse.setBounds(20,350,600,100);
            editCourse.setFont(new Font("Roboto",Font.BOLD,40));

            JLabel editCourseLabel = new JLabel("Select course to edit:");
            editCourseLabel.setBounds(20,450,300,20);
            editCourseLabel.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox<String> selectCourses = new JComboBox<>(courses);
            selectCourses.setBounds(270,445,200,30);

            JTextField editCourseName = new JTextField();
            editCourseName.setBounds(500,445,200,30);

            JButton editButton = new JButton("Change");
            editButton.setBounds(730,445,200,30);
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageDbUtils.editCourseName(editCourseName.getText(),selectCourse.getSelectedItem().toString());
                    courseFrame.dispose();
                    AddEditCourseFrame();
                }
            });



            courseFrame.add(addOrRemoveCourse);
            courseFrame.add(addCourse);
            courseFrame.add(AddCourse);
            courseFrame.add(Add);
            courseFrame.add(deleteCourse);
            courseFrame.add(DeleteCourse);
            courseFrame.add(delete);
            courseFrame.add(cancelCourse);
            courseFrame.add(cancelCourseLabel);
            courseFrame.add(selectCourse);
            courseFrame.add(cancelButton);
            courseFrame.add(editCourse);
            courseFrame.add(editCourseLabel);
            courseFrame.add(selectCourses);
            courseFrame.add(editCourseName);
            courseFrame.add(editButton);
            BottomFramePanel(courseFrame);
            courseFrame.setVisible(true);
        }
        public void AddEditModuleFrame(){
            JFrame moduleFrame = new JFrame();
            moduleFrame.setTitle("COURSE MANAGEMENT SYSTEM");
            moduleFrame.setSize(1440,1024);
            moduleFrame.setResizable(false);
            moduleFrame.setBackground(new Color(90,90,90));
            moduleFrame.setLocationRelativeTo(null);
            moduleFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            moduleFrame.getContentPane();



            JLabel addModuleLabel = new JLabel("Add, Delete And Edit Module To Course");
            addModuleLabel.setBounds(20,50,900,50);
            addModuleLabel.setFont(new Font("Roboto",Font.BOLD,40));

            JLabel addModuleLabel1 = new JLabel("Add module to:");
            addModuleLabel1.setBounds(20,150,300,20);
            addModuleLabel1.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox<String > availableCourse = new JComboBox<>(course.selectCourse());
            availableCourse.setBounds(200,145,200,30);

            JLabel moduleIDLabel = new JLabel("Module ID:");
            moduleIDLabel.setBounds(450, 150, 300,20);
            moduleIDLabel.setFont(new Font("Roboto",Font.BOLD,20));

            JTextField moduleIDField = new JTextField();
            moduleIDField.setBounds(600, 145, 200,30);

            JLabel moduleNameLabel = new JLabel("Module name:");
            moduleNameLabel.setBounds(850,150,300,20);
            moduleNameLabel.setFont(new Font("Roboto",Font.BOLD,20));

            JTextField moduleNameField = new JTextField();
            moduleNameField.setBounds(1000,145,200,30);

            JLabel moduleTypeLabel = new JLabel("Module Type:");
            moduleTypeLabel.setBounds(20,220,300,30);
            moduleTypeLabel.setFont(new Font("Roboto",Font.BOLD,20));

            Object[] type = {"Core","Optional"};
            JComboBox moduleType = new JComboBox(type);
            moduleType.setBounds(200,220,200,30);

            type = new Object[]{"1", "2", "3","4","5","6"};
            JLabel semesterLabel = new JLabel("Semester:");
            semesterLabel.setBounds(450,220,200,30);
            semesterLabel.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox semester = new JComboBox<>(type);
            semester.setBounds(600,220,200,30);

            JButton add = new JButton("Add");
            add.setBounds(850,220,200,30);
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int num = 0;
                    int numCore;
                    int numOptional;
                    if (Integer.parseInt(semester.getSelectedItem().toString())<5 ){
                        if (moduleType.getSelectedItem().toString().equals("Core")) {
                            num = module.numOfModule(availableCourse.getSelectedItem().toString(), semester.getSelectedItem().toString());
                            if (num < 3) {
                                manageDbUtils.addModule(moduleIDField.getText(), moduleNameField.getText(), availableCourse.getSelectedItem().toString(), moduleType.getSelectedItem().toString(), semester.getSelectedItem().toString());
                                JOptionPane.showMessageDialog(null, "Modules Added Successfully!!");
                            } else {
                                JOptionPane.showMessageDialog(null, "You cannot add more than three module in one semester!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "You cannot add Optional module in this semester!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    if (Integer.parseInt(semester.getSelectedItem().toString())>4){
                        numOptional = module.numOfOptionalModule(availableCourse.getSelectedItem().toString(),semester.getSelectedItem().toString());
                        numCore = module.numOfCoreModule(availableCourse.getSelectedItem().toString(),semester.getSelectedItem().toString());
                        if (numCore <2){
                            manageDbUtils.addModule(moduleIDField.getText(),moduleNameField.getText(), availableCourse.getSelectedItem().toString(),moduleType.getSelectedItem().toString(),semester.getSelectedItem().toString());
                            JOptionPane.showMessageDialog(null, "Modules Added Successfully!!");
                        }else{
                            JOptionPane.showMessageDialog(null,"You cannot add more than two core module in this semester!!","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        if (numOptional<2){
                            manageDbUtils.addModule(moduleIDField.getText(),moduleNameField.getText(), availableCourse.getSelectedItem().toString(),moduleType.getSelectedItem().toString(),semester.getSelectedItem().toString());
                            JOptionPane.showMessageDialog(null, "Modules Added Successfully!!");
                        }else{
                            JOptionPane.showMessageDialog(null,"You cannot add more than two optional module in this semester!!","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }






                    moduleFrame.dispose();
                    AddEditModuleFrame();
                }
            });


            JLabel DeleteModuleFrom = new JLabel("Select Module To Delete:");
            DeleteModuleFrom.setBounds(20,300,300,30);
            DeleteModuleFrom.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox<String> selectModule = new JComboBox<>(module.selectModule());
            selectModule.setBounds(300,300,200,30);

            JButton nextButton = new JButton("Delete");
            nextButton.setBounds(550,300,200,30);
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageDbUtils.deleteModule(selectModule.getSelectedItem().toString());



                    moduleFrame.dispose();
                    AddEditModuleFrame();

                }
            });

            JLabel editModule = new JLabel("Select Module To Change The Name:");
            editModule.setBounds(20,400,400,30);
            editModule.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox<String> selectModules = new JComboBox<>(module.selectModule());
            selectModules.setBounds(400,400,200,30);

            JTextField newName = new JTextField();
            newName.setBounds(650,400,200,30);

            JButton editButton = new JButton("Change");
            editButton.setBounds(900,400,200,30);
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageDbUtils.editModuleName(newName.getText(),selectModule.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null, "Successfully changed!!");
                    moduleFrame.dispose();
                    AddEditModuleFrame();
                }
            });

            moduleFrame.add(newName);
            moduleFrame.add(editButton);
            moduleFrame.add(selectModule);
            moduleFrame.add(editModule);
            moduleFrame.add(selectModules);
            moduleFrame.add(nextButton);
            moduleFrame.add(DeleteModuleFrom);
            moduleFrame.add(add);
            moduleFrame.add(semesterLabel);
            moduleFrame.add(semester);
            moduleFrame.add(moduleType);
            moduleFrame.add(moduleTypeLabel);
            moduleFrame.add(moduleNameField);
            moduleFrame.add(moduleNameLabel);
            moduleFrame.add(moduleIDLabel);
            moduleFrame.add(moduleIDField);
            moduleFrame.add(availableCourse);
            moduleFrame.add(addModuleLabel1);
            moduleFrame.add(addModuleLabel);
            BottomFramePanel(moduleFrame);
            moduleFrame.setVisible(true);
        }
        public void AddEditInstructorFrame(){
            Instructor instructor = new Instructor();
            Module module = new Module();
            JFrame instructorFrame = new JFrame();
            instructorFrame.setTitle("COURSE MANAGEMENT SYSTEM");
            instructorFrame.setSize(1440,1024);
            instructorFrame.setResizable(false);
            instructorFrame.setBackground(new Color(90,90,90));
            instructorFrame.setLocationRelativeTo(null);
            instructorFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            instructorFrame.getContentPane();


            JLabel title1 = new JLabel("Assign Instructors To Modules");
            title1.setBounds(50,20,800,50);
            title1.setFont(new Font("Roboto",Font.BOLD,40));

            JLabel addInstructorLabel = new JLabel("Select Instructor to add:");
            addInstructorLabel.setBounds(50,140,300,20);
            addInstructorLabel.setFont(new Font("Roboto",Font.BOLD,20));
            JComboBox<String> instructors = new JComboBox<>(instructor.selectInstructors());
            instructors.setBounds(300,140, 300 , 20);

            JLabel addModuleLabel = new JLabel("Select Module:");
            addModuleLabel.setBounds(650,140,300,20);
            addModuleLabel.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox<String> modules = new JComboBox<>(module.selectModule());
            modules.setBounds(800,140,300,20);
            JButton add = new JButton("Add Instructor");
            add.setBounds(500,210,200,20);
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageDbUtils.addInstructors(modules.getSelectedItem().toString(),instructors.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null,"Successfully Added!!");
                }
            });

            JLabel label = new JLabel("");
            label.setBounds(50,80,300,20);

            JLabel title2 = new JLabel("Remove Instructors From Modules");
            title2.setBounds(50,300,800,50);
            title2.setFont(new Font("Roboto",Font.BOLD,40));

            JLabel removeInstructorLabel = new JLabel("Select Module :");
            removeInstructorLabel.setBounds(50,380,300,20);
            removeInstructorLabel.setFont(new Font("Roboto",Font.BOLD,20));

            JLabel removeInstructor = new JLabel("Select Instructor to remove:");
            removeInstructor.setBounds(570,380,300,20);
            removeInstructor.setFont(new Font("Roboto",Font.BOLD,20));

            JComboBox<String> selectModule = new JComboBox<>(module.selectModule());
            selectModule.setBounds(230,380,300,20);


            JComboBox<String> selectInstructor = new JComboBox<>();
            selectInstructor.setBounds(870,380,300,20);

            selectModule.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int count = instructor.selectUpdatedInstructors(selectModule.getSelectedItem().toString()).length;
                    String[] availableInstructor = instructor.selectUpdatedInstructors(selectModule.getSelectedItem().toString());
                    for (int i = 0; i < count; i++) {
                        selectInstructor.addItem(availableInstructor[i]);
                    }

                }
            });

            JButton remove = new JButton("Remove");
            remove.setBounds(500,460,200,20);
            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageDbUtils.removeInstructors(selectModule.getSelectedItem().toString(),selectInstructor.getSelectedItem().toString());
                    instructorFrame.dispose();
                    AddEditInstructorFrame();
                }
            });



            instructorFrame.add(remove);
            instructorFrame.add(selectInstructor);
            instructorFrame.add(removeInstructor);
            instructorFrame.add(selectModule);
            instructorFrame.add(removeInstructorLabel);
            instructorFrame.add(title2);
            instructorFrame.add(title1);
            instructorFrame.add(add);
            instructorFrame.add(modules);
            instructorFrame.add(addModuleLabel);
            instructorFrame.add(instructors);
            instructorFrame.add(addInstructorLabel);
            instructorFrame.add(label);
            instructorFrame.setVisible(true);
            BottomFramePanel(instructorFrame);
        }




    }
