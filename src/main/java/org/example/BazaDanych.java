package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BazaDanych {


    public static final String CONNECTION_STRING ="jdbc:mysql://localhost:3306/bazadieta";
    public static final String USER ="root";
    public static final String PASSWORD ="admin";
    //tutaj beda odniesienia do kolumn i tabel w bazie
    public static final String TABLE_PRODUKT ="produkty";

    public static final String COLUMN_IDPRODUKTY ="idprodukty";

    public static final String COLUMN_NAZWAPRODUKTOW ="nazwaProduktow";
    public static final String COLUMN_KCAL ="kcal";
    public static final String COLUMN_BIALKO ="bialko";
    public static final String COLUMN_WEGLOWODANY ="weglowodany";
    public static final String COLUMN_BLONNIK ="blonnik";
    public static final String COLUMN_TLUSZCZE ="tluszcze";
    public static final int INDEX_IDPRODUKTY =1;
    public static final int INDEX_NAZWAPRODUKTOW =2;
    public static final int INDEX_KCAL =3;
    public static final int INDEX_BIALKO =4;
    public static final int INDEX_WEGLOWODANY =5;
    public static final int INDEX_BLONNIK =6;
    public static final int INDEX_TLUSZCZE =7;

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

        try(Statement statement = con.createStatement();
            ResultSet result =statement.executeQuery("SELECT * FROM produkty")){
            List<Produkt> produkts = new ArrayList<>();
            while (result.next()){

                Produkt produkt = new Produkt(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getDouble(4),
                        result.getDouble(5),
                        result.getDouble(6),
                        result.getDouble(7));
                produkts.add(produkt);
            }
            return produkts;
        }catch (SQLException e) {
            System.out.println("Problem z kwerendą " + e.getMessage());
            return null;
        }
    }

    public void dodajDoBazyProdukt(String nazwa, double kcal, double bialko, double wegle, double blonnik, double tluszcze){
        //INSERT INTO produkty values (2, 'muj', 122.1, 3.2, 41.2, 93.9 , 2);
        //"INSERT INTO "+ TABLE_PRODUKT +" VALUES ("'test3' , 123, 'gg@email.com')");

        //ResultSet result =statement.executeQuery("SELECT * FROM produkty")

        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(TABLE_PRODUKT);
        sb.append(" VALUES (");
        // id


    }

}
