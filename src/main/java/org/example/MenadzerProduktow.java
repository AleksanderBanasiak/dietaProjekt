package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MenadzerProduktow {

    private List<Produkt> produkty = new ArrayList<>();



    public void tworzenieProduktu(Scanner scanner){

        System.out.println("Jak nazywa się twój produkt?");
        String nazwa = scanner.nextLine();
        System.out.println("Ile kalorii na 100g ma twój produkt?");
        double kcal = Double.parseDouble(scanner.nextLine());
        System.out.println("Ile białka na 100g ma twój produkt?");
        double bialko = Double.parseDouble(scanner.nextLine());
        System.out.println("Ile węglowodanów na 100g ma twój produkt?");
        double weglowodany = Double.parseDouble(scanner.nextLine());
        System.out.println("Ile błonnika na 100g ma twój produkt?");
        double blonnik = Double.parseDouble(scanner.nextLine());
        System.out.println("Ile tłuszczy na 100g ma twój produkt?");
        double tluszcze = Double.parseDouble(scanner.nextLine());


        Produkt nowyProdukt = new Produkt(nazwa, kcal, bialko, weglowodany, blonnik, tluszcze);

        produkty.add(nowyProdukt);

    }

    public void wyswietlWszystkieProdukty(){
        for (int i = 0; i < produkty.size(); i++) {
            System.out.println("["+(i+1) + "] - "+ wypiszProdukt(produkty.get(i)));
        }
    }
    public static String wypiszProdukt(Produkt produkt){
        return produkt.nazwa().toUpperCase()+ ": kcal:"+ produkt.kcal()+ ", białko: "+produkt.bialko()
                + ", węglowodany: " + produkt.weglowodany()+ ", błonnik: "+produkt.blonnik()
                + ", tłuszcze: "+produkt.tluszcze();
    }



}
