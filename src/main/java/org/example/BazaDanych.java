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
    public static final String TABLE_TEST ="test";

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


    public static final String KWERENDA_PRODUKTY = "SELECT " +COLUMN_NAZWAPRODUKTOW+ " FROM "+TABLE_PRODUKT+ " WHERE "+ COLUMN_NAZWAPRODUKTOW+ " = ?";

    public static final String wyswietlWszystkoZDanejTabeli = " SELECT * FROM produkty WHERE " + INDEX_NAZWAPRODUKTOW +" = ?";
    //public static final String INSERT_PRODUKT = "INSERT INTO " + TABLE_PRODUKT + "VALUES (" +COLUMN_IDPRODUKTY +", " +
      //      ""+COLUMN_NAZWAPRODUKTOW +", "+COLUMN_KCAL+", "+COLUMN_BIALKO +", " +", "+COLUMN_WEGLOWODANY +", "+COLUMN_BLONNIK +", "+COLUMN_TLUSZCZE +")";
    public static final String INSERT_PRODUKT = "INSERT INTO " + TABLE_TEST + "VALUES (?)";



    private Connection con;

    private PreparedStatement wybierzWsztkoZDanejTabeli;
    private PreparedStatement insertIntoProdukt;
    private PreparedStatement kwerendaProdukt;

    public boolean open(){
        try{
            con = DriverManager.getConnection(CONNECTION_STRING,USER,PASSWORD );
            wybierzWsztkoZDanejTabeli =con.prepareStatement(wyswietlWszystkoZDanejTabeli);
            insertIntoProdukt = con.prepareStatement(INSERT_PRODUKT, Statement.RETURN_GENERATED_KEYS);
            kwerendaProdukt = con.prepareStatement(KWERENDA_PRODUKTY);

            return true;
        }catch (SQLException e){
            System.out.println("Nie można się połączyć z bazą danych: "+e.getMessage());
            return false;
        }
    }
    public void close(){
        try{
            if(wybierzWsztkoZDanejTabeli != null){
                wybierzWsztkoZDanejTabeli.close();
            }
            if(insertIntoProdukt !=null){
                insertIntoProdukt.close();
            }
            if(kwerendaProdukt != null ){
                kwerendaProdukt.close();
            }

            if(con != null){
                con.close();
            }
        }catch (SQLException e){
            System.out.println("Nie można zamknąć bazy"+e.getMessage());
        }
    }

    public List<Produkt> wyswietlProdukty(String tabela){

        try{
            wybierzWsztkoZDanejTabeli.setString(1, tabela);

            ResultSet result = wybierzWsztkoZDanejTabeli.executeQuery();
            result.getString(1);
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


    public int insertProdukty(String name) throws SQLException{

        kwerendaProdukt.setString(1, name);
        ResultSet resultSet = kwerendaProdukt.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt(1);
        }else {
            insertIntoProdukt.setString(1, name);
            int sprawdzoneWiersze = insertIntoProdukt.executeUpdate();

            if(sprawdzoneWiersze != 1){
                throw new SQLException("nie można dodać produktu");
            }

            ResultSet wygenerowanyKlucz = insertIntoProdukt.getGeneratedKeys();
            if(wygenerowanyKlucz.next()){
                return wygenerowanyKlucz.getInt(1);
            }else{
                throw new SQLException("nie można pobrac id produktu");
            }

        }

    }




}
