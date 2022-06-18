package com.sem;

import java.sql.*;
import java.util.*;

public class Sem{
    public static void main(String args[]){
        try{
            int ch;
            Scanner in = new Scanner(System.in);
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            String url = "jdbc:mysql://127.0.0.1:3306/java";
            String username = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();

            System.out.println("1. VIEW ALL");
            System.out.println("2. SELECT BY ROLL NUMBER");
            System.out.println("3. Update Mark");
            System.out.println("4. DELETE ENTRY");
            System.out.println("5. INSERT ENTRY");
            System.out.println("6. CREATE TABLE");
            
            System.out.println("Enter your choice: ");
            ch = in.nextInt();

            switch(ch){
                case 1:{
                    String q = "SELECT * FROM students";
        
                    ResultSet rs = st.executeQuery(q);

                    while(rs.next()){
                        System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3));
                    }
                    break;
                }
                case 2:{
                    System.out.println("Enter the roll number: ");
                    String rollno = in.next();
                    
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE ROLLNO = ?");
                    ps.setString(1, rollno);
                    
                    ResultSet rs = ps.executeQuery();
                    
                    while(rs.next()){
                        System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3));
                    }
                }
                case 3:{
                    System.out.println("Enter the roll number: ");
                    String rollno = in.next();
                    
                    System.out.println("Enter the New Mark: ");
                    int mark = in.nextInt();
                    
                    PreparedStatement ps = conn.prepareStatement("UPDATE students SET MARKS=? WHERE ROLLNO=?");
                    ps.setInt(1, mark);
                    ps.setString(2, rollno);
                    
                    int rs = ps.executeUpdate();
                    
                    System.out.println("Number of Rows Affected = " + rs);
                    break;
                }
                case 4:{
                    System.out.println("Enter the roll number: ");
                    String rollno = in.next();
                    
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE ROLLNO=?");
                    ps.setString(1, rollno);
                    
                    int rs = ps.executeUpdate();
                    
                    System.out.println("Number of Rows Affected = " + rs);
                    break;
                }
                case 5:{
                    System.out.println("Enter the roll number: ");
                    String rollno = in.next();
                    
                    System.out.println("Enter the New Mark: ");
                    int mark = in.nextInt();
                    
                    System.out.println("Enter the Name: ");
                    String name = in.next();
                    
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO students VALUES(?, ?, ?)");
                    ps.setString(1, name);
                    ps.setInt(2, mark);
                    ps.setString(3, rollno);
                    
                    int rs = ps.executeUpdate();
                    
                    System.out.println("Number of Rows Affected = " + rs);
                    break;
                }
                case 6:{
                    PreparedStatement ps = conn.prepareStatement("CREATE TABLE sample (NAME varchar(30), MARK int, ROLL varchar(30))");
                    
                    int rs = ps.executeUpdate();
                    
                    System.out.println("Number of Rows Affected = " + rs);
                    break;
                }
            }
            
            conn.close();
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
