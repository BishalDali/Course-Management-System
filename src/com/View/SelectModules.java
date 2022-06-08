package com.View;

import com.Controller.ManageDbUtils;
import com.Model.Courses.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectModules extends JFrame{
    private JFrame frame = new JFrame();
    ManageDbUtils manageDbUtils = new ManageDbUtils();
    Module module = new Module();
    private Object[] columnNames = {"Module Name","Semester","Credits", "Module Type"};
    String[][] rowData ;

    public SelectModules(int studentID, String courseName){
        this.setTitle("COURSE MANAGEMENT SYSTEM");
        this.setSize(1440,1024);
        this.setResizable(false);
        this.setBackground(new Color(90,90,90));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane();
        int numberOfRows = module.totalNumOfModules(courseName);
        JLabel title = new JLabel("Modules For Your Course");
        title.setBounds(500,510,800,50);
        title.setFont(new Font("Roboto",Font.BOLD,25));

        JLabel note = new JLabel("Note: You can select a optional subject for 5th and 6th semester!");
        note.setBounds(470,560,800,30);
        note.setForeground(Color.red);

        JTable table = new JTable(numberOfRows,4);
        JScrollPane scrollPane = new JScrollPane();

        rowData = module.selectModules(courseName);
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        table.setBackground(Color.WHITE);
        table.setRowHeight(25);
        table.setAutoResizeMode( JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setCellSelectionEnabled(false);

        JLabel selectModule1 = new JLabel("Select Optional Module for 5th semester: ");
        JLabel selectModule2 = new JLabel("Select Optional Module for 6th semester: ");
        selectModule1.setBounds(300,600,400,30);
        selectModule1.setFont(new Font("Roboto",Font.BOLD,18));
        selectModule2.setBounds(300,640,400,30);
        selectModule2.setFont(new Font("Roboto",Font.BOLD,18));


        JComboBox<String> selectModules1 = new JComboBox<>(module.selectOptionalModule(courseName,5));
        JComboBox<String> selectModules2 = new JComboBox<>(module.selectOptionalModule(courseName,6));
        selectModules1.setBounds(700,600,400,30);
        selectModules1.setFont(new Font("San-Serif",Font.TRUETYPE_FONT,15));
        selectModules2.setBounds(700,640,400,30);
        selectModules2.setFont(new Font("San-Serif",Font.TRUETYPE_FONT,15));
        table.setModel(model);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(600,700,200,30);
        confirm.setFocusable(false);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int option = JOptionPane.showConfirmDialog(null,"Are you sure that you want to select those modules?",null,dialogButton);
                if (option == 0){
                    manageDbUtils.saveStudentModule(studentID,courseName,selectModules1.getSelectedItem().toString(),selectModules2.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null,"Sucessfully Selected");

                    dispose();
                    new Login();
                }
            }
        });


        scrollPane.setViewportView(table);
        this.add(confirm);
        this.add(selectModule2);
        this.add(selectModule1);
        this.add(selectModules2);
        this.add(selectModules1);
        this.add(note);
        this.add(title);
        this.add(scrollPane);
        this.setVisible(true);
    }
}
