package org.example;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class KorzytanieZBazy {

    public static void main(String[] args) throws SQLException {
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        BazaDanych bazaDanych = new BazaDanych();
        ZapytaniaDoBazy zapytaniaDoBazy = new ZapytaniaDoBazy();
        if(!zapytaniaDoBazy.open()){
            System.out.println("Nie można otworzyć bazy danych");
            return;
        }


     //   zapytaniaDoBazy.insertIntoPosilek(400);


        //zapytanie 1 wyswietla wszyskie produkty (dziala)
//        List<Produkt> produkty = zapytaniaDoBazy.wyswietlWyszyskieProdukty();
//        if(produkty ==null){
//            System.out.println("Nie ma produktow do wyswietlenia");
//            return;
//        }
//        for (Produkt produkt : produkty){
//            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
//        }

       //  zapytanie 2 dodaje produkt do bazy plus zapytanie o nastepne id
        //  zapytaniaDoBazy.insertIntoProdukt( "test2", 2.2, 12.2, 9.9, 6.6, 5.5);


        // zapytanie 3 zwraca produkty z danego dania B)
//        List<String> produkty = zapytaniaDoBazy.wyswietlWszystkieNazwyProduktowZDanegoDania(1);
//        for (int i = 0; i < produkty.size(); i++) {
//
//            System.out.println(produkty.get(i));
//        }

//        String danie = zapytaniaDoBazy.wyswietlDanieODanymID(1);
//
//        System.out.println(danie);




        TypPosilku typ = TypPosilku.SNIADANIE;
        LocalDate data = LocalDate.now();


        List<String> dania = zapytaniaDoBazy.pobierzDanePosilku(typ, data);
        List<String> daty = zapytaniaDoBazy.wyswietlDaty();

        for (int i = 0; i < daty.size(); i++) {
            System.out.println(daty.get(i));
        }



//        for (int i = 0; i < dania.size(); i++) {
//            System.out.println(dania.get(i));
//        }




//        for (int i = 0; i < produkty.size(); i++) {
//            System.out.println(produkty.get(i).getNazwa());
//        }
//        if(produkty ==null){
//            System.out.println("Nie ma produktow do wyswietlenia");
//            return;
//        }
//        for (Produkt produkt : produkty){
//            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
//        }
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Podaj id1: ");
//        int id1 = scanner.nextInt();
//        System.out.print("Podaj id2: ");
//        int id2 = scanner.nextInt();

      //   zapytanie 4 dodanie do produkty_has_danie id danie i id produku
      //  zapytaniaDoBazy.dodajProduktyDoDania(id1, id2);


//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Podaj nazwe: ");
//        String cos = scanner.nextLine();
//        System.out.print("Podaj kcal: ");
//        double cos2 = scanner.nextDouble();
//        List<Produkt> produkty = zapytaniaDoBazy.podwojneZapytanieSelect(cos, cos2);
//        if(produkty ==null){
//            System.out.println("Nie ma produktow do wyswietlenia");
//            return;
//        }
//        for (Produkt produkt : produkty){
//            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
//        }

//         int w =zapytaniaDoBazy.pobierzOstatnieID();
//        System.out.println(w);




      //  List<String> nazwy = zapytaniaDoBazy.wyswietlWszystkieNazwyProduktow();


//        for (String s : nazwy) {
//            if(Objects.equals(sprawdzNazwe, s)){
//                System.out.println("Taki produkt jest już w bazie");
//            }
//            System.out.println(s);
//        }

//
//        Scanner scanner = new Scanner(System.in);
//        int id1 = scanner.nextInt();
//        int id2 = scanner.nextInt();


        zapytaniaDoBazy.close();

    }



}
