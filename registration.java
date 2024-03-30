package com.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame extends JFrame implements ActionListener{
    JLabel label1,label2,label3,label4,label5;
    JTextField t1,t2;
    JRadioButton Male,Female;
    JComboBox day,month,year;
    JTextArea tA1;
    JCheckBox terms;
    JButton submit;
    JLabel msg;
    JTextArea screen;
   MyFrame(){
       setTitle("Registration Form");
       setSize(700,500);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       Container c=getContentPane();
       c.setLayout(null);
       //add 1st label and its textfield
       label1 =new JLabel("name");
       label1.setBounds(20,50,100,20);
       c.add(label1);
       t1=new JTextField();
       t1.setBounds(130,50,100,20);
       c.add(t1);

       //add 2nd label and its textfield
       label2 =new JLabel("mobile");
       label2.setBounds(20,100,100,20);
       c.add(label2);
       t2=new JTextField();
       t2.setBounds(130,100,100,20);
       c.add(t2);

      //add 3rd label and its radio button
       label3 =new JLabel("gender");
       label3.setBounds(20,150,100,20);
       c.add(label3);
       Male=new JRadioButton("Male");
       Male.setBounds(130,150,90,25);
       c.add(Male);
       Female=new JRadioButton("Female");
       Female.setBounds(220,150,90,25);
       c.add(Female);
       //for only one select at one time
       ButtonGroup gen=new ButtonGroup();
       gen.add(Male);
       gen.add(Female);

       //add 4th label and its comboBox
       label4 =new JLabel("dob");
       label4.setBounds(20,200,100,20);
       c.add(label4);
       String days[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
       String months[]={"january","febuary","march","april","may","june","july", "august","september","october","november","december"};
       String years[]={"1995","1996","1997","1998","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025"};
       day=new JComboBox(days);
       month=new JComboBox(months);
       year=new JComboBox(years);
       day.setBounds(130,200,50,20);
       month.setBounds(180,200,90,20);
       year.setBounds(270,200,70,20);
       c.add(day);
       c.add(month);
       c.add(year);

       //add 5th label and its textArea
       label5 =new JLabel("address");
       label5.setBounds(20,250,100,20);
       c.add(label5);
       tA1=new JTextArea();
       tA1.setBounds(130,250,150,50);
       tA1.setLineWrap(true);
       c.add(tA1);

       //term and condition check box
       terms =new JCheckBox("accept term and condition");
       terms.setBounds(100,300,200,30);
       c.add(terms);

       //submit button
       submit=new JButton("Submit");
       submit.setBounds(120,350,80,20);
       c.add(submit);
       submit.addActionListener(this);
       //show message
       msg=new JLabel("");
       msg.setBounds(30,400,300,20);
       msg.setFont(new Font("Arial",Font.BOLD,15));
       c.add(msg);

       screen=new JTextArea();
       screen.setBounds(350,20,300,400);
       screen.setLineWrap(true);
       screen.setFont(new Font("Arial",Font.BOLD,18));
       c.add(screen);

       c.setBackground(Color.yellow);

       setVisible(true);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
     if(terms.isSelected()){
         msg.setText("registration successful");

         String name=t1.getText();
         String mobile=t2.getText();
         String gender="null";
         if(Male.isSelected()) {
              gender = "male";
         }
         if(Female.isSelected()) {
              gender = "female";
         }
         String dob=day.getSelectedItem()+"-"+month.getSelectedItem()+"-"+year.getSelectedItem();
         String address=tA1.getText();
         screen.setText("Name :    "+name+"\n"+"Mobile :   "+mobile+"\n"+"Gender :  "+gender+"\n"+"DOB      :   "+dob+"\n"+"Address :  "+address);


     }else{
         msg.setText("please accept term and condition");
         screen.setText("");
     }
    }
}

public class registration {
    public static void main(String[] args) {
        MyFrame frame=new MyFrame();
    }
}
