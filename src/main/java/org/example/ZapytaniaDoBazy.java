package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ZapytaniaDoBazy {



    public static final String URL = "jdbc:mysql://localhost:3306/bazadieta";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

    public static final String TABLE_PRODUKT = "produkty";
    public static final String TABLE_DANIA = "dania";
    public static final String TABLE_POSILKI = "posilki";
    public static final String TABLE_GRAM = "gramaturaPosilku";
    public static final String TABLE_GRAMPRODUCTS_HAS_DANIE = "gramaturaposilku_has_dania";
    public static final String TABLE_DANIA_HAS_PRODUKTY = "produkty_has_dania";
    public static final String COLUMN_PRODUKTY_IDPODUKTY = "produkty_idprodukty";
    public static final String COLUMN_GRAMPRODUKTY_IDPODUKTY = "GramaturaPosilku_idGramaturaPosilku";
    public static final String COLUMN_GRAMPRODUKTY_IDGRAMPODUKTY = "idGramaturaPosilku";
    public static final String COLUMN_PRODUKTY_IDDANIA = "dania_iddania";

    public static final String COLUMN_IDPRODUKTY = "idprodukty";
    public static final String COLUMN_IDPOSILKI = "idposilki";
    public static final String COLUMN_IDGRAM = "idGramaturaPosilku";
    public static final String COLUMN_IDDANIA = "iddania";
    public static final String COLUMN_NAZWAPRODUKTOW = "nazwaProduktow";

    public static final String COLUMN_NAZWADANIA = "nazwaDania";
    public static final String COLUMN_DATAPOSILKU = "dataPosilku";
    public static final String CZAS = "NOW()";
    public static final String COLUMN_ILEGRAM = "ileGram";
    public static final String COLUMN_TYPDANIA = "typDania";
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

    public static final String CULUMN_GRAMIDGRAMPOSULKU = "idGramaturaPosilku";
    public static final String CULUMN_GRAMNAZWAPRODUKTOW = "nazwaProduktow";
    public static final String CULUMN_GRAMTDATA = "data";


    //zapytania do bazy


//    SELECT bazadieta.dania.nazwaDania, bazadieta.gramaturaposilku.nazwaProduktow, bazadieta.gramaturaposilku.kcal  FROM bazadieta.gramaturaposilku
//    JOIN bazadieta.gramaturaposilku_has_dania ON bazadieta.gramaturaposilku.idGramaturaPosilku = bazadieta.gramaturaposilku_has_dania.GramaturaPosilku_idGramaturaPosilku
//    JOIN bazadieta.dania ON bazadieta.dania.iddania = bazadieta.gramaturaposilku_has_dania.dania_iddania;




    public static final String QUERY_SELECT_ALL_DATA_FROM_DAY = "SELECT "+COLUMN_TYPDANIA+"," +COLUMN_NAZWADANIA + ", " + COLUMN_NAZWAPRODUKTOW + ", "+ COLUMN_KCAL  + ", "+ COLUMN_BIALKO
             + ", "+ COLUMN_WEGLOWODANY + ", "+ COLUMN_BLONNIK + ", "+ COLUMN_TLUSZCZE + " FROM "+ TABLE_GRAM + " JOIN "+ TABLE_GRAMPRODUCTS_HAS_DANIE +" ON " + TABLE_GRAM + "."+
            CULUMN_GRAMIDGRAMPOSULKU + " = "+ TABLE_GRAMPRODUCTS_HAS_DANIE+"."+COLUMN_GRAMPRODUKTY_IDPODUKTY + " JOIN "+ TABLE_DANIA + " ON " + TABLE_DANIA+"."+COLUMN_IDDANIA + " = "+
            TABLE_GRAMPRODUCTS_HAS_DANIE+"."+COLUMN_PRODUKTY_IDDANIA + " WHERE "+ TABLE_DANIA+"."+COLUMN_TYPDANIA + " = ?" + " AND "+ TABLE_GRAM+"."+CULUMN_GRAMTDATA + " = ?";


    public static final String QUERY_SELECT_ALL_SUMS_FROM_DAY = "SELECT SUM("+ COLUMN_KCAL  + "), SUM("+ COLUMN_BIALKO + "), SUM("+ COLUMN_WEGLOWODANY + "), SUM("+ COLUMN_BLONNIK +
            "), SUM("+ COLUMN_TLUSZCZE + ") FROM "+ TABLE_GRAM + " JOIN "+ TABLE_GRAMPRODUCTS_HAS_DANIE +" ON " + TABLE_GRAM + "."+ CULUMN_GRAMIDGRAMPOSULKU + " = "+
            TABLE_GRAMPRODUCTS_HAS_DANIE+"."+COLUMN_GRAMPRODUKTY_IDPODUKTY + " JOIN "+ TABLE_DANIA + " ON " + TABLE_DANIA+"."+COLUMN_IDDANIA + " = "+
            TABLE_GRAMPRODUCTS_HAS_DANIE+"."+COLUMN_PRODUKTY_IDDANIA + " WHERE "+ TABLE_DANIA+"."+COLUMN_TYPDANIA + " = ?" + " AND "+ TABLE_GRAM+"."+CULUMN_GRAMTDATA + " = ?";


    public static final String SELECT_DISTINCT_DATA = "SELECT DISTINCT "+CULUMN_GRAMTDATA+ " FROM "+ TABLE_GRAM + " ORDER BY "+ CULUMN_GRAMTDATA;

    public static final String SELECT_DATA_DESC = "SELECT DISTINCT "+CULUMN_GRAMTDATA+ " FROM "+ TABLE_GRAM + " ORDER BY "+ CULUMN_GRAMTDATA + " DESC LIMIT ?";


    public static final String QUERY_GET_ALL_PRODUCTS = "SELECT * FROM "+ TABLE_PRODUKT;


    public static final String QUERY_INSERT_PRODUKT = " INSERT INTO "+TABLE_PRODUKT+"("+ COLUMN_IDPRODUKTY +","+COLUMN_NAZWAPRODUKTOW+","+COLUMN_KCAL
            +","+COLUMN_BIALKO+","+COLUMN_WEGLOWODANY+","+COLUMN_BLONNIK+","+COLUMN_TLUSZCZE+") VALUES (?,?,?,?,?,?,?)";


    //SELECT idprodukty FROM bazadieta.produkty order by idprodukty DESC limit 1;
    public static final String LAST_ID_PRODDUKTY = "SELECT "+ COLUMN_IDPRODUKTY + " FROM " +TABLE_PRODUKT + " ORDER BY " + COLUMN_IDPRODUKTY + " DESC LIMIT 1";
    public static final String LAST_ID_DANIA = "SELECT "+ COLUMN_IDDANIA + " FROM " +TABLE_DANIA + " ORDER BY " + COLUMN_IDDANIA+ " DESC LIMIT 1";
    public static final String LAST_ID_GRAM = "SELECT "+ COLUMN_IDGRAM + " FROM " +TABLE_GRAM + " ORDER BY " + COLUMN_IDGRAM+ " DESC LIMIT 1";


    public static final String GET_IDS_GRAM = "SELECT "+ COLUMN_IDGRAM + " FROM " +TABLE_GRAM + " ORDER BY " + COLUMN_IDGRAM+ " DESC LIMIT ?";

    public static final String QUERY_ALL_PRODUCTS_BY_ID_DANIA = "SELECT * FROM "+TABLE_PRODUKT+" INNER JOIN "+TABLE_DANIA_HAS_PRODUKTY + " ON "+TABLE_PRODUKT+"."+
            COLUMN_IDPRODUKTY+ " = " + TABLE_DANIA_HAS_PRODUKTY + "." + COLUMN_PRODUKTY_IDPODUKTY + " WHERE " + TABLE_DANIA_HAS_PRODUKTY +"." +COLUMN_PRODUKTY_IDDANIA
            + " = ?";
    public static final String QUERY_ALL_PRODUCTS_NAMES_BY_ID_DANIA = "SELECT "+COLUMN_NAZWAPRODUKTOW +" FROM "+TABLE_PRODUKT+" INNER JOIN "+TABLE_DANIA_HAS_PRODUKTY + " ON "+TABLE_PRODUKT+"."+
            COLUMN_IDPRODUKTY+ " = " + TABLE_DANIA_HAS_PRODUKTY + "." + COLUMN_PRODUKTY_IDPODUKTY + " WHERE " + TABLE_DANIA_HAS_PRODUKTY +"." +COLUMN_PRODUKTY_IDDANIA
            + " = ?";

    public static final String QUERY_ADD_PRODUCTS_TO_DANIE = "INSERT INTO "+TABLE_DANIA_HAS_PRODUKTY + "("+COLUMN_PRODUKTY_IDPODUKTY+","+ COLUMN_PRODUKTY_IDDANIA +")" +
        " VALUES (?, ?)";

    // to jest to zapytanie
    public static final String QUERY_ADD_GRAMPRODUKCTS_TO_DANIA = "INSERT INTO "+TABLE_GRAMPRODUCTS_HAS_DANIE + "("+COLUMN_GRAMPRODUKTY_IDPODUKTY+","+ COLUMN_PRODUKTY_IDDANIA +")" +
        " VALUES (?, ?)";
    public static final String QUERY_ALL_PRODUKTS_NAMES ="SELECT "+ COLUMN_NAZWAPRODUKTOW + " FROM "+ TABLE_PRODUKT;
    public static final String QUERY_ALL_DANIA_NAMES ="SELECT "+ COLUMN_NAZWADANIA + " FROM "+ TABLE_DANIA;

    public static final String QUERY_GET_ID_DANIA_TO_TYPE = "SELECT "+COLUMN_IDDANIA +" FROM "+ TABLE_DANIA + " WHERE " + COLUMN_TYPDANIA + " = ?";
    public static final String QUERY_GET_NAME_DANIA_TO_TYPE = "SELECT "+COLUMN_NAZWADANIA +" FROM "+ TABLE_DANIA + " WHERE " + COLUMN_TYPDANIA + " = ?";


    public static final String QUERY_INSERT_INTO_DANIA = "INSERT INTO "+TABLE_DANIA+ "("+COLUMN_IDDANIA + ","+ COLUMN_NAZWADANIA+ ","+COLUMN_TYPDANIA +") VALUES (?,?,?)";
    public static final String QUERY_INSERT_INTO_POSILKI = "INSERT INTO "+TABLE_POSILKI+ "("+COLUMN_IDPOSILKI + ","+ COLUMN_DATAPOSILKU
            + ","+COLUMN_ILEGRAM +") VALUES (?,"+CZAS+",?)";

    public static final String QUERY_ADD_GRAMY = "INSERT INTO "+TABLE_GRAM + " VALUES (?,?,?,?,?,?,?,?)";
    public static final String QUERY_GET_NAZWADANIA_TO_ID = "SELECT "+COLUMN_NAZWADANIA + " FROM "+ TABLE_DANIA + " WHERE "+ COLUMN_IDDANIA + " = ?";




    private Connection con;
    private PreparedStatement insertIntoProdukt;

    private PreparedStatement allProductsByIdDania;
    private PreparedStatement addProductsToDanie;
    private PreparedStatement addGramProductsToDanie;
    private PreparedStatement insertIntoDania;
    private PreparedStatement getAllProductsNamesByIdDania;
    private PreparedStatement getIdDaniaToType;
    private PreparedStatement getNazwaDaniaToType;
    private PreparedStatement insetIntoPosilki;

    private PreparedStatement addGram;

    private PreparedStatement getNameDaniaToID;

    private PreparedStatement getIdsGram;

    private PreparedStatement getAll;
    private PreparedStatement getAllSums;

    private PreparedStatement getData;



    public boolean open(){
        try{
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            insertIntoProdukt = con.prepareStatement(QUERY_INSERT_PRODUKT);
            allProductsByIdDania = con.prepareStatement(QUERY_ALL_PRODUCTS_BY_ID_DANIA);
            addProductsToDanie = con.prepareStatement(QUERY_ADD_PRODUCTS_TO_DANIE);
            addGramProductsToDanie = con.prepareStatement(QUERY_ADD_GRAMPRODUKCTS_TO_DANIA);
            insertIntoDania = con.prepareStatement(QUERY_INSERT_INTO_DANIA);
            getAllProductsNamesByIdDania = con.prepareStatement(QUERY_ALL_PRODUCTS_NAMES_BY_ID_DANIA);
            getIdDaniaToType = con.prepareStatement(QUERY_GET_ID_DANIA_TO_TYPE);
            getNazwaDaniaToType = con.prepareStatement(QUERY_GET_NAME_DANIA_TO_TYPE);
            insetIntoPosilki = con.prepareStatement(QUERY_INSERT_INTO_POSILKI);
            addGram = con.prepareStatement(QUERY_ADD_GRAMY);
            getNameDaniaToID = con.prepareStatement(QUERY_GET_NAZWADANIA_TO_ID);
            getIdsGram = con.prepareStatement(GET_IDS_GRAM);
            getAll = con.prepareStatement(QUERY_SELECT_ALL_DATA_FROM_DAY);
            getAllSums = con.prepareStatement(QUERY_SELECT_ALL_SUMS_FROM_DAY);
            getData = con.prepareStatement(SELECT_DATA_DESC);




            return true;
        }catch (SQLException e){
            System.out.println("Nie można się połączyć z bazą danych " + e.getMessage());
            return false;
        }
    }
    public void close(){
        try{
            if(insertIntoProdukt != null){
                insertIntoProdukt.close();
            }
            if(allProductsByIdDania != null){
                allProductsByIdDania.close();
            }
            if(addProductsToDanie != null){
                addProductsToDanie.close();
            }
            if(insertIntoDania != null){
                insertIntoDania.close();
            }
            if(getAllProductsNamesByIdDania != null){
                getAllProductsNamesByIdDania.close();
            }
            if(getIdDaniaToType != null){
                getIdDaniaToType.close();
            }
            if(getNazwaDaniaToType != null){
                getNazwaDaniaToType.close();
            }
            if(insetIntoPosilki != null){
                insetIntoPosilki.close();
            }
            if(addGram != null){
                addGram.close();
            }
            if(addGramProductsToDanie != null){
                addGramProductsToDanie.close();
            }
            if(getNameDaniaToID != null){
                getNameDaniaToID.close();
            }
            if(getIdsGram != null){
                getIdsGram.close();
            }
            if(getAll != null){
                getAll.close();
            }
            if(getAllSums != null){
                getAllSums.close();
            }
            if(getData != null){
                getData.close();
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
    public int pobierzOstatnieIDProduktu(){
        int ostatnieID =0;
        try(Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(LAST_ID_PRODDUKTY)){
            while (result.next()){
                ostatnieID = result.getInt(1);
                ostatnieID++;
            }
        }catch (SQLException e) {
            System.out.println("Nie można pobrać ostaniego id "+ e.getMessage());
        }
        return ostatnieID;
    }
    public int pobierzOstatnieIDDania(){
        int ostatnieID =0;
        try(Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(LAST_ID_DANIA)){
            while (result.next()){
                ostatnieID = result.getInt(1);
                ostatnieID++;
            }
        }catch (SQLException e) {
            System.out.println("Nie można pobrać ostaniego id "+ e.getMessage());
        }
        return ostatnieID;
    }
    public int pobierzOstatnieIDGram(){
        int ostatnieID =0;
        try(Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(LAST_ID_GRAM)){
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
        int id = pobierzOstatnieIDProduktu();
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
    public void insertIntoDanie(String name, TypPosilku typPosilku){
        int id = pobierzOstatnieIDDania();
        try {

            insertIntoDania.setInt(1, id);
            insertIntoDania.setString(2, name);
            insertIntoDania.setString(3, String.valueOf(typPosilku));

            insertIntoDania.executeUpdate();
        }catch (SQLException e){
            System.out.println("Nie można dodać dania do bazy "+e.getMessage() );
        }
    }
    public void insertIntoGramaturaPosilku(int ileGram, Produkt produkt){
        int id = pobierzOstatnieIDGram();
        MenadzerDania menadzerDania = new MenadzerDania();
        Produkt produktZObliczonymMakro = menadzerDania.obliczMakro(ileGram, produkt);




        LocalDate date = LocalDate.now();
            try {
                addGram.setInt(1, id);
                addGram.setString(2, produktZObliczonymMakro.getNazwa());
                addGram.setDouble(3, produktZObliczonymMakro.getKcal());
                addGram.setDouble(4, produktZObliczonymMakro.getBialko());
                addGram.setDouble(5, produktZObliczonymMakro.getWeglowodany());
                addGram.setDouble(6, produktZObliczonymMakro.getBlonnik());
                addGram.setDouble(7, produktZObliczonymMakro.getTluszcze());
                addGram.setString(8, String.valueOf(date));
                addGram.executeUpdate();
            }catch (SQLException e){
                System.out.println("Nie można dodać gramatury posilku do bazy "+e.getMessage() );
            }


    }

    public List<Produkt> wyswietlWszystkieProduktyZDanegoDania(int id) {
        try {
            allProductsByIdDania.setInt(1, id);
            ResultSet result = allProductsByIdDania.executeQuery();
            List<Produkt> wszyskieProdukty = new ArrayList<>();
            while (result.next()) {

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
        } catch (SQLException e) {
            System.out.println("Nie można wyświetlić produktów z danego dania");
            return null;
        }
    }
        public String wyswietlDanieODanymID(int id){
        try{
            getNameDaniaToID.setInt(1, id);
            ResultSet result =  getNameDaniaToID.executeQuery();
            String wynik ="";
            while (result.next()){
                wynik = result.getString(1);
            }


            return wynik;
        }catch (SQLException e ){
            System.out.println("Nie można wyświetlić produktów z danego dania");
            return null;
        }
    }
    public List<String> wyswietlWszystkieNazwyProduktowZDanegoDania(int id){
        try{
            getAllProductsNamesByIdDania.setInt(1, id);
            ResultSet result = getAllProductsNamesByIdDania.executeQuery();
            List<String> wszyskieNazwy = new ArrayList<>();
            while (result.next()){
               String nazwa = result.getString(1);
                wszyskieNazwy.add(nazwa);
            }
            return wszyskieNazwy;
        }catch (SQLException e ){
            System.out.println("Nie można wyświetlić nazw produktów z danego dania");
            return null;
        }
    }
    public List<Integer> wyswietlWszystkieDodaneIdDoGram(int ile){
        try{
            getIdsGram.setInt(1, ile);
            ResultSet result = getIdsGram.executeQuery();
            List<Integer> wszyskieId = new ArrayList<>();
            while (result.next()){
               int id = result.getInt(1);
                wszyskieId.add(id);
            }
            return wszyskieId;
        }catch (SQLException e ){
            System.out.println("Nie można wyświetlić nazw id dodanych gram produktow");
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

    public void dodajListeProduktowDoDania(List<Integer> idProduktu, int idDania){
        for (Integer integer : idProduktu) {
            dodajProduktyDoDania(integer, idDania);
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

    public void dodajListeGramProduktowDoDania(List<Integer> idGramProduktu, int idDania){
        for (Integer integer : idGramProduktu) {
            dodajGramatureProduktowDoDania(integer, idDania);
        }
    }


    public void dodajGramatureProduktowDoDania(int idGramProduktu, int idDania){
        try {
            addGramProductsToDanie.setInt(1, idGramProduktu);
            addGramProductsToDanie.setInt(2, idDania);
            addGramProductsToDanie.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem z dodanie id gram produktów i id dania "+e.getMessage() );
        }
    }


    public List<Integer> wyswietlIdDaniaDanegoTypu(TypPosilku typPosilku){

        try {
            getIdDaniaToType.setString(1, String.valueOf(typPosilku));
            ResultSet result = getIdDaniaToType.executeQuery();
            List<Integer> idDan = new ArrayList<>();
            while (result.next()){
                int idDania =result.getInt(1);
                idDan.add(idDania);
            }
            return idDan;
        }catch (SQLException e ){
            System.out.println("Nie można pobrać id dań "+ e.getMessage());
            return null;
        }
    }
    public List<String> wyswietlNazweDaniaDanegoTypu(TypPosilku typPosilku){

        try {
            getNazwaDaniaToType.setString(1, String.valueOf(typPosilku));
            ResultSet result = getNazwaDaniaToType.executeQuery();
            List<String> nazwyDan = new ArrayList<>();
            while (result.next()){
                String idDania =result.getString(1);
                nazwyDan.add(idDania);
            }
            return nazwyDan;
        }catch (SQLException e ){
            System.out.println("Nie można pobrać nazwy dań "+ e.getMessage());
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

    public List<String> wyswietlDaty(){
       List<String> daty = new ArrayList<>();
        try(Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(SELECT_DISTINCT_DATA)){
            while (result.next()){
                String data = result.getString(1);
                daty.add(data);
            }
            return daty;
        }catch (SQLException e) {
            System.out.println("Nie można pobrać ostaniego id "+ e.getMessage());
            return null;
        }

    }

    public String wyswietlWybranaDate(int data){
            String wybranaData = "";
        try {
            getData.setInt(1,data);
            ResultSet result = getData.executeQuery();
            while (result.next()){
                 wybranaData =result.getString(1);
            }
            return wybranaData;
        }catch (SQLException e ){
            System.out.println("Nie można wybranej daty "+ e.getMessage());
            return null;
        }
    }




    public List<String> pobierzDanePosilku(TypPosilku typPosilku, LocalDate data){
        // najgorsza metoda jaką napisałem xd (do poprawy)

        // sume mozna zapisac jako kolejne zapytanie z wartosciamy tyle tylko ze suma
        int licznik =0;
        int produkt =0;
        List<String> wyniki =new ArrayList<>();
        List<String> wszystko =new ArrayList<>();
        StringBuilder buliderDlaTypuIDania = new StringBuilder();
        try{
           getAll.setString(1, String.valueOf(typPosilku));
           getAll.setString(2, String.valueOf(data));
           ResultSet result = getAll.executeQuery();
            while (result.next()){
                licznik++;
                String typDania = result.getString(1);
                String nazwaDania = result.getString(2);
                String nazwaProduktu = result.getString(3);
                double kcal = result.getDouble(4);
                double bialko = result.getDouble(5);
                double wegle = result.getDouble(6);
                double blonnik = result.getDouble(7);
                double tluszcze = result.getDouble(8);
                typDania = typDania.toUpperCase().charAt(0) + typDania.substring(1).toLowerCase();
                if(licznik ==1){
                    buliderDlaTypuIDania.append(typDania);
                    buliderDlaTypuIDania.append(": ");
                    buliderDlaTypuIDania.append(nazwaDania);
                    buliderDlaTypuIDania.append(" ");
                }
                StringBuilder stringBuilder = new StringBuilder();
                produkt++;
                stringBuilder.append("[");
                stringBuilder.append(produkt);
                stringBuilder.append("] - ");
                stringBuilder.append(nazwaProduktu);
                stringBuilder.append(": ");
                stringBuilder.append("kcal:");
                stringBuilder.append(kcal);
                stringBuilder.append(",");
                stringBuilder.append(" białko:");
                stringBuilder.append(bialko);
                stringBuilder.append(",");
                stringBuilder.append(" węglowodany:");
                stringBuilder.append(wegle);
                stringBuilder.append(",");
                stringBuilder.append(" błonnik:");
                stringBuilder.append(blonnik);
                stringBuilder.append(",");
                stringBuilder.append(" tłuszcze:");
                stringBuilder.append(tluszcze);
                wyniki.add(String.valueOf(stringBuilder));
            }
            wszystko.add(String.valueOf(buliderDlaTypuIDania));
            wszystko.addAll(wyniki);
            wszystko.add(pobierzSumy(typPosilku, data));
        }catch (SQLException e) {
            System.out.println("Nie można pobrać ostaniego id "+ e.getMessage());
        }
        return wszystko;
    }

        public String pobierzSumy(TypPosilku typPosilku, LocalDate data){
        double sumKcal = 0;
        double sumBialko = 0;
        double sumWegle = 0;
        double sumBlonnik = 0;
        double sumTluszcze = 0;
        try{
           getAllSums.setString(1, String.valueOf(typPosilku));
           getAllSums.setString(2, String.valueOf(data));
           ResultSet result = getAllSums.executeQuery();
            while (result.next()){
                 sumKcal = result.getDouble(1);
                 sumBialko = result.getDouble(2);
                 sumWegle = result.getDouble(3);
                 sumBlonnik = result.getDouble(4);
                 sumTluszcze = result.getDouble(5);

            }
            return "Łącznie: kcal:" + sumKcal+ ", białko: "+sumBialko+ ", węglowodany: "+ sumWegle + ", błonnik: "+sumBlonnik + ", tłuszcze: "+sumTluszcze;
        }catch (SQLException e) {
            System.out.println("Nie można pobrać ostaniego id "+ e.getMessage());
            return null;
        }

    }








}
