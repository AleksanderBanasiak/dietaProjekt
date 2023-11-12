package org.example;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static final String CONNECTION_STRING ="jdbc:mysql://localhost:3306/bazadieta";
    public static final String USER ="root";
    public static final String PASSWORD ="admin";

    public static final String TABLE_COS ="cos";

    public static final String COLUMN_NAME ="cos";
    public static final String COLUMN_PHONE ="phone";
    public static final String COLUMN_EMAIL ="email";

    public static void main(String[] args) throws IOException {
//        try{
//
//        }catch (Exception e){
//            System.out.println("");
//        }

        //tutaj bedzie kod dla bazy danych

//        DietaApk dietaApk = new DietaApk();
//        dietaApk.run();



        try{
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            Statement statement = con.createStatement();
           // statement.execute("DROP TABLE IF EXISTS " + TABLE_COS);
            statement.execute("CREATE TABLE IF NOT EXISTS "+ TABLE_COS +"("+COLUMN_NAME+" TEXT," +COLUMN_PHONE+" INTEGER,"+ COLUMN_EMAIL +" TEXT"+ ")");

            insertCos(statement,"test4" , 321, "ee@email.com");
            insertCos(statement,"test5" , 321, "ee@email.com");
            insertCos(statement,"test6" , 321, "ee@email.com");
            insertCos(statement,"test7" , 321, "ee@email.com");
//            statement.execute("INSERT INTO "+ TABLE_COS+"("+COLUMN_NAME+","+ COLUMN_PHONE+","+ COLUMN_EMAIL+") VALUES ('test1' , 321, 'ee@email.com')");
//            statement.execute("INSERT INTO "+ TABLE_COS+"("+COLUMN_NAME+","+ COLUMN_PHONE+","+ COLUMN_EMAIL+") VALUES ('test2' , 132, 'ww@email.com')");
//            statement.execute("INSERT INTO "+ TABLE_COS+"("+COLUMN_NAME+","+ COLUMN_PHONE+","+ COLUMN_EMAIL+") VALUES ('test3' , 123, 'gg@email.com')");
//            statement.execute("UPDATE "+ TABLE_COS+" SET "+COLUMN_NAME+"='huj'"+" WHERE " +COLUMN_NAME+"='test2'");
            statement.execute("DELETE FROM "+TABLE_COS +" WHERE "+COLUMN_NAME+"='test3'");

//            statement.execute("SELECT * FROM cos");
       //     ResultSet resultSet = statement.getResultSet();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+ TABLE_COS);
            while (resultSet.next()){
                System.out.println(resultSet.getString(COLUMN_NAME)+" " +resultSet.getInt(COLUMN_PHONE));
            }
            resultSet.close();

            statement.close();
            con.close();



        }catch (SQLException e){
            System.out.println("cos poszlo nie tak \n" + e.getMessage());
            e.printStackTrace();
        }



    }
    public static void insertCos(Statement statement, String name, int phone, String emial) throws SQLException {

        statement.execute("INSERT INTO "+ TABLE_COS+"("+COLUMN_NAME+","+ COLUMN_PHONE+","+ COLUMN_EMAIL+") VALUES ('"+name+"'," + phone + ",'"+emial +"')");

    }

}