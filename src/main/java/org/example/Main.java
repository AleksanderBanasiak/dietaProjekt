package org.example;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        try{
//
//        }catch (Exception e){
//            System.out.println("");
//        }

        //tutaj bedzie kod dla bazy danych

        DietaApk dietaApk = new DietaApk();
        dietaApk.run();



//        try{
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bazadieta", "root", "admin");
//
//            Statement statement = con.createStatement();
//            statement.execute("CREATE TABLE cos (name TEXT, phone INTEGER, email TEXT)");
//        }catch (SQLException e){
//            System.out.println("cos poszlo nie tak" + e.getMessage());
//        }



    }
}