package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ZapytaniaDoBazy {



    public static final String URL = "jdbc:mysql://localhost:3306/bazadieta";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

    public static final String TABLE_PRODUKT = "produkty";
    public static final String TABLE_DANIA = "dania";
    public static final String TABLE_DANIA_HAS_PRODUKTY = "produkty_has_dania";
    public static final String COLUMN_PRODUKTY_IDPODUKTY = "produkty_idprodukty";
    public static final String COLUMN_PRODUKTY_IDDANIA = "dania_iddania";

    public static final String COLUMN_IDPRODUKTY = "idprodukty";
    public static final String COLUMN_NAZWAPRODUKTOW = "nazwaProduktow";
    public static final String COLUMN_NAZWADANIA = "nazwaDania";
    public static final String COLUMN_KCAL = "kcal";
    public static final String COLUMN_BIALKO = "bialko";
    public static final String COLUMN_WEGLOWODANY = "weglowodany";
    public static final String COLUMN_BLONNIK = "blonnik";
    public static final String COLUMN_TLUSZCZE = "tluszcze";
    public static final int INDEX_IDPRODUKTY = 1;
    public static final int INDEX_NAZWAPRODUKTOW = 2;
    public static final int INDEX_KCAL = 3;
    public static final int INDEX_BIALKO = 4;
    public static final int INDEX_WEGLOWODANY = 5;
    public static final int INDEX_BLONNIK = 6;
    public static final int INDEX_TLUSZCZE = 7;


    //zapytania do bazy
    public static final String QUERY_GET_ALL_PRODUCTS = "SELECT * FROM "+ TABLE_PRODUKT;
    public static final String QUERY_TEST_PREPERE = " SELECT * FROM "+TABLE_PRODUKT+" WHERE "+ COLUMN_NAZWAPRODUKTOW + " = ? OR "+ COLUMN_KCAL + " = ?";


    public static final String QUERY_INSERT_PRODUKT = " INSERT INTO "+TABLE_PRODUKT+"("+ COLUMN_IDPRODUKTY +","+COLUMN_NAZWAPRODUKTOW+","+COLUMN_KCAL
            +","+COLUMN_BIALKO+","+COLUMN_WEGLOWODANY+","+COLUMN_BLONNIK+","+COLUMN_TLUSZCZE+") VALUES (?,?,?,?,?,?,?)";


    //SELECT idprodukty FROM bazadieta.produkty order by idprodukty DESC limit 1;
    public static final String LAST_ID = "SELECT "+ COLUMN_IDPRODUKTY + " FROM " +TABLE_PRODUKT + " ORDER BY " + COLUMN_IDPRODUKTY + " DESC LIMIT 1";

    public static final String QUERY_ALL_PRODUCTS_BY_ID_DANIA = "SELECT * FROM "+TABLE_PRODUKT+" INNER JOIN "+TABLE_DANIA_HAS_PRODUKTY + " ON "+TABLE_PRODUKT+"."+
            COLUMN_IDPRODUKTY+ " = " + TABLE_DANIA_HAS_PRODUKTY + "." + COLUMN_PRODUKTY_IDPODUKTY + " WHERE " + TABLE_DANIA_HAS_PRODUKTY +"." +COLUMN_PRODUKTY_IDDANIA
            + " = ?";

    public static final String QUERY_ADD_PRODUCTS_TO_DANIE = "INSERT INTO "+TABLE_DANIA_HAS_PRODUKTY + "("+COLUMN_PRODUKTY_IDPODUKTY+","+ COLUMN_PRODUKTY_IDDANIA +")" +
        " VALUES (?, ?)";
    public static final String QUERY_ALL_PRODUKTS_NAMES ="SELECT "+ COLUMN_NAZWAPRODUKTOW + " FROM "+ TABLE_PRODUKT;
    public static final String QUERY_ALL_DANIA_NAMES ="SELECT "+ COLUMN_NAZWADANIA + " FROM "+ TABLE_DANIA;




    private Connection con;
    private PreparedStatement prepereTest;
    private PreparedStatement insertIntoProdukt;

    private PreparedStatement allProductsByIdDania;
    private PreparedStatement addProductsToDanie;




    public boolean open(){
        try{
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            prepereTest = con.prepareStatement(QUERY_TEST_PREPERE);
            insertIntoProdukt = con.prepareStatement(QUERY_INSERT_PRODUKT);
            allProductsByIdDania = con.prepareStatement(QUERY_ALL_PRODUCTS_BY_ID_DANIA);
            addProductsToDanie = con.prepareStatement(QUERY_ADD_PRODUCTS_TO_DANIE);





            return true;
        }catch (SQLException e){
            System.out.println("Nie można się połączyć z bazą danych " + e.getMessage());
            return false;
        }
    }
    public void close(){
        try{
            if(prepereTest != null){
                prepereTest.close();
            }
            if(insertIntoProdukt != null){
                insertIntoProdukt.close();
            }
            if(allProductsByIdDania != null){
                allProductsByIdDania.close();
            }


            if(con != null){
                con.close();
            }
        }catch (SQLException e){
            System.out.println("Nie można zamknąć bazy danych "+e.getMessage());
        }
    }
    public List<Produkt> wyswietlWyszyskieProdukty(){
        List<Produkt> wszyskieProdukty = new ArrayList<>();
        try(Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(QUERY_GET_ALL_PRODUCTS)){
            while (result.next()){
               Produkt produkt = new Produkt(
                       result.getInt(1),
                       result.getString(2),
                       result.getDouble(3),
                       result.getDouble(4),
                       result.getDouble(5),
                       result.getDouble(6),
                       result.getDouble(7));
               wszyskieProdukty.add(produkt);
            }
            return wszyskieProdukty;
        }catch (SQLException e){
            System.out.println("Problem z zapytaniem o produkty "+e.getMessage() );
            return null;
        }
    }
    public int pobierzOstatnieID(){
        int ostatnieID =0;
        try(Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(LAST_ID)){

            while (result.next()){
                ostatnieID = result.getInt(1);
                ostatnieID++;
            }
        }catch (SQLException e) {
            System.out.println("Nie można pobrać ostaniego id "+ e.getMessage());
        }
        return ostatnieID;
    }
    public void insertIntoProdukt(String name, double kacl, double bialko, double wegle, double blonnik, double tluszcze){
        int id = pobierzOstatnieID();
        try {
            insertIntoProdukt.setInt(1, id);
            insertIntoProdukt.setString(2, name);
            insertIntoProdukt.setDouble(3, kacl);
            insertIntoProdukt.setDouble(4, bialko);
            insertIntoProdukt.setDouble(5, wegle);
            insertIntoProdukt.setDouble(6, blonnik);
            insertIntoProdukt.setDouble(7, tluszcze);
            insertIntoProdukt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Nie można dodać produktu do bazy "+e.getMessage() );
        }
    }
    public List<Produkt> wyswietlWszystkieProduktyZDanegoDania(int id){
        try{
            allProductsByIdDania.setInt(1, id);
            ResultSet result = allProductsByIdDania.executeQuery();
            List<Produkt> wszyskieProdukty = new ArrayList<>();
            while (result.next()){

                Produkt produkt = new Produkt(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getDouble(4),
                        result.getDouble(5),
                        result.getDouble(6),
                        result.getDouble(7));
                wszyskieProdukty.add(produkt);
            }
            return wszyskieProdukty;
        }catch (SQLException e ){
            System.out.println("Nie można wyświetlić produktów z danego dania");
            return null;
        }
    }

    public List<String> wyswietlWszystkieNazwy(String query) {

        List<String> wszyskieDania = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                String nazwa = result.getString(1);
                wszyskieDania.add(nazwa);
            }
            return wszyskieDania;
        }catch (SQLException e ){
            System.out.println("Nie można zwrócić nazw produktów "+e.getMessage() );
            return null;
        }
    }


    public void dodajProduktyDoDania(int idProduktu, int idDania){
        try {
            addProductsToDanie.setInt(1, idProduktu);
            addProductsToDanie.setInt(2, idDania);
            addProductsToDanie.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem z zapytaniem o produkty "+e.getMessage() );
        }
    }





    public List<Produkt> podwojneZapytanieSelect(String table, double kcal){
        try {
            prepereTest.setString(1, table);
            prepereTest.setDouble(2, kcal);
            ResultSet result = prepereTest.executeQuery();
            List<Produkt> wszyskieProdukty = new ArrayList<>();
            while (result.next()){
               Produkt produkt = new Produkt(
                       result.getInt(1),
                       result.getString(2),
                       result.getDouble(3),
                       result.getDouble(4),
                       result.getDouble(5),
                       result.getDouble(6),
                       result.getDouble(7));
               wszyskieProdukty.add(produkt);
            }
            return wszyskieProdukty;
        }catch (SQLException e){
            System.out.println("Problem z zapytaniem o produkty "+e.getMessage() );
            return null;
        }
    }


    public boolean sprawdzCzyJestWBazieTakiProdukt(String nazwa, ZapytaniaDoBazy zapytaniaDoBazy){
        boolean flaga = true;
        List<String> nazwy = zapytaniaDoBazy.wyswietlWszystkieNazwy(QUERY_ALL_PRODUKTS_NAMES);
        for (String s : nazwy) {
            if(Objects.equals(nazwa, s)){
                return false;
            }
        }
        return flaga;
    }
    public boolean sprawdzCzyJestWBazieTakieDanie(String nazwa, ZapytaniaDoBazy zapytaniaDoBazy){
        boolean flaga = true;
        List<String> nazwy = zapytaniaDoBazy.wyswietlWszystkieNazwy(QUERY_ALL_DANIA_NAMES);
        for (String s : nazwy) {
            if(Objects.equals(nazwa, s)){
                return false;
            }
        }
        return flaga;
    }








}
