package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bazaDanych {


    public static final String CONNECTION_STRING ="jdbc:mysql://localhost:3306/bazadieta";
    public static final String USER ="root";
    public static final String PASSWORD ="admin";
    //tutaj beda odniesienia do kolumn i tabel w bazie
    public static final String TABLE_COS ="cos";

    public static final String COLUMN_NAME ="cos";
    public static final String COLUMN_PHONE ="phone";
    public static final String COLUMN_EMAIL ="email";

    private Connection con;

    public boolean open(){
        try{
            con = DriverManager.getConnection(CONNECTION_STRING,USER,PASSWORD );
            return true;
        }catch (SQLException e){
            System.out.println("Nie można się połączyć z bazą danych: "+e.getMessage());
            return false;
        }
    }
    public void close(){
        try{
            if(con != null){
                con.close();
            }
        }catch (SQLException e){
            System.out.println("Nie można zamknąć bazy"+e.getMessage());
        }
    }

    public List<Produkt> wszystkieProdukty(){

        Statement statement =null;
        ResultSet result = null;



        try{
            statement = con.createStatement();
            result =statement.executeQuery("SELECT * FROM produkty");


            List<Produkt> produkts = new ArrayList<>();
            while (result.next()){
             //   Produkt produkt = new Produkt();
            }


        }catch (SQLException e){
            System.out.println("Problem z kwerendą "+ e.getMessage());
            return null;
        }finally {
            try {
                if(statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                System.out.println("e");
            }
        }


    }

}
