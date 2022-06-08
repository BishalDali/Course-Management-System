package com.Controller;

import com.Model.Courses.Module;
import com.Model.MainBody.Instructor;

import javax.swing.*;
import  java.sql.*;


public class CreateDbUtils {
    static final String username = "root";
    static final String password = "";
    static final String url = "jdbc:mysql://localhost/";
    static final String dbname = "CourseManagementSystem";


    public CreateDbUtils() {
        //Database connection and its utilities
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement command = conn.createStatement();
            String sql = "Create Database " + dbname;
            command.execute(sql);
            System.out.println("Database has been successfully created.");
        } catch (SQLException se) {
            System.out.println("Database Already Exists!");
        } catch (Exception e) {
            e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Couldn't connect to the database! Please make sure that your database server is active.", "Connection Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        createTable();
    }

    public static Connection getDbConnection() {
        try{
            String Username = "root";
            String Password = "";
            String dbName = "coursemanagementsystem";
            String connectionString = "jdbc:mysql://localhost/"+dbName+"?useTimezone=true&serverTimezone=UTC";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(connectionString, Username,Password);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Couldn't connect to the database! Please make sure that your database server is active.", "Connection Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return null;
        }
    }

    public void createTable(){
        createStudentTable();
        createInstructorTable();
        createModuleTable();
        createCourseTable();
        createUserTable();
        createStudentModuleTable();
        createInstructorModuleTable();
    }

    private void createStudentTable() {
        try{
            String url = "jdbc:mysql://localhost/"+dbname+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE student " +
                    "(studentId VARCHAR (20), " +
                    " studentName VARCHAR(255), " +
                    " course VARCHAR(255), " +
                    " level VARCHAR(255), " +
                    " semester int(11), " +
                    " creditCompleted int(11), " +
                    " address VARCHAR(255), " +
                    " emailAddress VARCHAR(255) UNIQUE ," +
                    " eligibleForNextSem VARCHAR(255) ," +
                    " remark VARCHAR(255))";
            createTable.executeUpdate(sql);
            }catch (SQLSyntaxErrorException se){
            System.out.println("Student table already exists!!");
            }catch(SQLException se){
                se.printStackTrace();
            }

    }
    private void createInstructorTable(){
        try{
            String url = "jdbc:mysql://localhost/"+dbname+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE instructor " +
                    "(instructorId VARCHAR(20), " +
                    " instructorName VARCHAR(255), " +
                    " instructorLevel VARCHAR(255), " +
                    " instructorAddress VARCHAR(255), " +
                    " instructorModule Varchar(255), " +
                    " instructorEmail VARCHAR(50) UNIQUE , " +
                    " instuctorRating tinyint(1))";
            createTable.executeUpdate(sql);
        }catch (SQLSyntaxErrorException se){

            System.out.println("Instructor table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    private void createCourseTable(){
        try{
            String url = "jdbc:mysql://localhost/"+dbname+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE course " +
                    "(courseCode VARCHAR(20), " +
                    " courseName VARCHAR(255), " +
                    " enrolledStatus tinyint(1), " +
                    " cancelStatus VARCHAR (10), " +
                    " courseLeader VARCHAR(50), " +
                    " PRIMARY KEY (courseName))";
            createTable.executeUpdate(sql);
            String insert = "INSERT INTO `course` (`courseCode`, `courseName`, `enrolledStatus`, `courseLeader`,`cancelStatus`) VALUES ('BSC1001', 'BSc (Hons) Computer Science', '1', 'Rukesh Shrestha','false'), ('BA1001', 'BA (Hons)', '1', 'Deepson Shrestha','false');";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.executeUpdate();
        }catch (SQLSyntaxErrorException se){

            System.out.println("Course table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    private void createModuleTable(){
        try{
            String url = "jdbc:mysql://localhost/"+dbname+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE module " +
                    "( semester int(11), " +
                    " moduleCode VARCHAR(255), " +
                    " moduleName VARCHAR(255), " +
                    " courseName VARCHAR(255), " +
                    " instructorName VARCHAR(255), " +
                    " moduleType Varchar(255), " +
                    " credits int(11)) ";

            createTable.executeUpdate(sql);

            //Dummy Data
            String[] insert =  new String[50];
            insert[0] ="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI018', 'Academic Skills and Team-based Learning', 'BSc (Hons) Computer Science', 'Wilson Fisk', 'Core', '1', '20');";
            insert[1]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4MM013', 'Computational Mathematics', 'BSc (Hons) Computer Science', 'Uttam Acharya', 'Core', '1', '20');";
            insert[2]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CS016', 'Embedded Systems Programming', 'BSc (Hons) Computer Science', 'Prabin Sapkota', 'Core', '1', '20');";
            insert[3]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CS015', 'Fundamentals of Computing', 'BSc (Hons) Computer Science', 'Sangay Lama', 'Core', '2', '20');";
            insert[4]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CS017', 'Internet Software Architecture', 'BSc (Hons) Computer Science', 'Matt Murdock', 'Core', '2', '20');";
            insert[5]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CS001', 'Introductory Programming And Problem Solving', 'BSc (Hons) Computer Science', 'Aashish Acharya', 'Core', '2', '20');";
            insert[6]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('5CS024', 'Collaborative Development', 'BSc (Hons) Computer Science', 'Tony Stark', 'Core', '3', '20')";
            insert[7]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('5CS021', 'Numerical Methods and Concurrency', 'BSc (Hons) Computer Science', 'Prabin Khadka', 'Core', '3', '20');";
            insert[8]= "INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('5CS019', 'Object-Oriented Design and Programming', 'BSc (Hons) Computer Science', 'Bishal Khadka', 'Core', '3', '20');";
            insert[9]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('5CS037', 'Concepts and Technologies of AI', 'BSc (Hons) Computer Science', 'Biraj Dulal', 'Core', '4', '20');";
            insert[10]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('5CS020', 'Human-Computer Interaction', 'BSc (Hons) Computer Science', 'Sachin Khadka', 'Core', '4', '20');";
            insert[11]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('5CS022', 'Distributed and Cloud Systems Programming', 'BSc (Hons) Computer Science', 'Peter Parker', 'Core', '4', '20');";
            insert[12]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('6CS030', 'Big Data', 'BSc (Hons) Computer Science', 'Chris Evans', 'Core', '5', '20');";
            insert[13]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('6CS005', 'High Performance Computing', 'BSc (Hons) Computer Science', 'Tara Subedi', 'Core', '5', '20');";
            insert[14]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('6CS028', 'Advance Web Development', 'BSc (Hons) Computer Science', 'Hang pin', 'Optional', '5', '20');";
            insert[15]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('6CS012', 'Artificial Intelligence and Machine Learning', 'BSc (Hons) Computer Science', 'Jacob Smith', 'Optional', '5', '20');";
            insert[16]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('6CS014', 'Complex Systems', 'BSc (Hons) Computer Science', 'Bruce Banner', 'Core', '6', '20');";
            insert[17]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('6CS025', 'Advance Games Technologies and Programming', 'BSc (Hons) Computer Science', 'Lucy Hamilton', 'Core', '6', '20');";
            insert[18]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('6CS007', 'Project and Professionalism', 'BSc (Hons) Computer Science', 'Chris Hemsworth', 'Optional', '6', '20');";
            insert[19]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('6CS027', 'Secure Mobile Application Development', 'BSc (Hons) Computer Science', 'Nirmal Thapa', 'Optional', '6', '20');";
            insert[20]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI018', 'The Digital Business', 'BA (Hons)', 'Leslie Weaver', 'Core', '1', '20');";
            insert[21]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI018', 'The Responsible Business', 'BA (Hons)', 'Alondra Medina', 'Core', '1', '20');";
            insert[22]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI019', 'The Sustainable Business', 'BA (Hons)', 'Killian Duarte', 'Core', '1', '20');";
            insert[23]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI020', 'Customer Acquisition & Retention', 'BA (Hons)', 'Niko Mcpherson', 'Core', '2', '20');";
            insert[24]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI021', 'Managing Finance and Accounts', 'BA (Hons)', 'Adeline Stephenson', 'Core', '2', '20');";
            insert[25]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI022', 'Operations and Project Planning', 'BA (Hons)', 'Alayna Sweeney', 'Core', '2', '20');";
            insert[26]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI023', 'The Professional Manager and Leadership', 'BA (Hons)', 'Izayah Calderon', 'Core', '3', '20');";
            insert[27]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI024', 'Organisational Behaviour', 'BA (Hons)', 'Zachariah Hebert', 'Core', '3', '20');";
            insert[28]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI025', 'The Business Communicator', 'BA (Hons)', 'Roderick Hickman', 'Core', '3', '20');";
            insert[29]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI026', 'The Professional Project', 'BA (Hons)', 'Alfred Cooke', 'Core', '4', '20');";
            insert[30]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI027', 'The Strategic Business', 'BA (Hons)', 'Kaylee Zhang', 'Core', '4', '20');";
            insert[31]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI028', 'Business in a Global Context', 'BA (Hons)', 'Natalia Schultz', 'Core', '4', '20');";
            insert[32]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI029', 'Contract Law', 'BA (Hons)', 'Lillianna Kerr', 'Core', '5', '20');";
            insert[33]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI030', 'Law of Torts', 'BA (Hons)', 'Anderson Anthony', 'Core', '5', '20');";
            insert[34]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI031', 'Financial Management', 'BA (Hons)', 'Landin Davies', 'Optional', '5', '20');";
            insert[35]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI032', 'Principles of Marketing', 'BA (Hons)', 'Camilla Estrada', 'Optional', '5', '20');";
            insert[36]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI033', 'Quantitative Analysis', 'BA (Hons)', 'Lorelei Charles', 'Core', '6', '20');";
            insert[37]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI034', 'Commercial Law', 'BA (Hons)', 'Cody Davenport', 'Core', '6', '20');";
            insert[38]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI035', 'Operations and Supply Management', 'BA (Hons)', 'Evangeline Hughes', 'Optional', '6', '20');";
            insert[39]="INSERT INTO `module` (`moduleCode`, `moduleName`, `courseName`, `instructorName`, `moduleType`, `semester`, `credits`) VALUES ('4CI036', 'Marketing Planning', 'BA (Hons)', 'Simon Waters', 'Optional', '6', '20');";
            for (int i = 0; i < 40; i++) {
                PreparedStatement statement = con.prepareStatement(insert[i]);
                statement.executeUpdate();
            }



        }catch (SQLSyntaxErrorException se){
            System.out.println("Module table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    private void createUserTable(){
        try{
            String url = "jdbc:mysql://localhost/"+dbname+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement createTable = con.createStatement();
            String sql ="CREATE TABLE user " +
                    "(userId VARCHAR(20), " +
                    " userType VARCHAR(255), " +
                    " userPassword VARCHAR(255), " +
                    " PRIMARY KEY ( userId ))";
            createTable.executeUpdate(sql);
            String insert = "INSERT INTO `user`(`userId`, `userType`, `userPassword`) VALUES ('12345','Admin','Admin')";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.executeUpdate();
        }catch (SQLSyntaxErrorException se){
            System.out.println("User table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    private void createInstructorModuleTable(){
        try{
            String url = "jdbc:mysql://localhost/"+dbname+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement createTable = con.createStatement();
            String sql ="CREATE TABLE instructorModule " +
                    "(moduleName VARCHAR(255), " +
                    " instructorName VARCHAR(255)) ";

            createTable.executeUpdate(sql);
            Module module = new Module();
            Instructor instructor = new Instructor();
            int i = module.selectModule().length;
            String[] modules, instructors;
            modules= module.selectModule();
            instructors = instructor.selectInstructors();

            String insert = "INSERT INTO `instructorModule`(`moduleName`,`instructorName`) VALUES (?,?)";
            for (int j = 0; j < i; j++) {
                PreparedStatement statement = con.prepareStatement(insert);
                statement.setString(1,modules[j]);
                statement.setString(2,instructors[j]);
                statement.executeUpdate();
            }

        }catch (SQLSyntaxErrorException se){
            System.out.println("User table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    private void createStudentModuleTable(){
        try{
            String url = "jdbc:mysql://localhost/"+dbname+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE studentModule " +
                    "(studentId int(20), " +
                    " moduleName VARCHAR(255), " +
                    " semester int , " +
                    " marksSecured double)";
            createTable.executeUpdate(sql);
        }catch (SQLSyntaxErrorException se){
            System.out.println("moduleResult table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }


}
