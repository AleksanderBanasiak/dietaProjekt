package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class KorzytanieZBazy {

    public static void main(String[] args) throws SQLException {
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        BazaDanych bazaDanych = new BazaDanych();
        if(!bazaDanych.open()){
            System.out.println("Nie można otworzyć bazy");
            return;
        }
//
//        Scanner scanner = new Scanner(System.in);
//        String cos = scanner.nextLine();
//        List<Produkt> produkty = bazaDanych.wyswietlProdukty(cos);
//                if(produkty ==null){
//            System.out.println("Nie ma produktow do wyswietlenia");
//            return;
//        }
//
//        for (Produkt produkt : produkty){
//            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
//        }


        bazaDanych.insertProdukty("huj");




        bazaDanych.close();

    }

}
