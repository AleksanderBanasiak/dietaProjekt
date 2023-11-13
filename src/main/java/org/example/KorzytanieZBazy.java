package org.example;

import java.util.List;

public class KorzytanieZBazy {

    public static void main(String[] args) {
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        BazaDanych bazaDanych = new BazaDanych();
        if(!bazaDanych.open()){
            System.out.println("Nie można otworzyć bazy");
            return;
        }


        List<Produkt> produkty = bazaDanych.wszystkieProdukty("produkty");
        if(produkty ==null){
            System.out.println("Nie ma produktow do wyswietlenia");
            return;
        }

        for (Produkt produkt : produkty){
            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
        }




        bazaDanych.close();

    }

}
