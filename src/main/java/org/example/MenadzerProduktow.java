package org.example;

import java.util.Scanner;

public class MenadzerProduktow {
    ZapytaniaDoBazy zapytaniaDoBazy = new ZapytaniaDoBazy();

    public void tworzenieProduktu(Scanner scanner){
        if(!zapytaniaDoBazy.open()){
            System.out.println("Nie można otworzyć bazy danych");
            return;
        }
        System.out.print("Jak nazywa się twój produkt?: ");
        String nazwa = scanner.nextLine();
        boolean flaga = zapytaniaDoBazy.sprawdzCzyJestWBazieTakiProdukt(nazwa, zapytaniaDoBazy);
        if(!flaga){
            System.out.println("Taki produkt juz istnieje!");
        }else {
        System.out.print("Ile kalorii na 100g ma twój produkt?: ");
        double kcal = Double.parseDouble(scanner.nextLine());
        System.out.print("Ile białka na 100g ma twój produkt?: ");
        double bialko = Double.parseDouble(scanner.nextLine());
        System.out.print("Ile węglowodanów na 100g ma twój produkt?: ");
        double weglowodany = Double.parseDouble(scanner.nextLine());
        System.out.print("Ile błonnika na 100g ma twój produkt?: ");
        double blonnik = Double.parseDouble(scanner.nextLine());
        System.out.print("Ile tłuszczy na 100g ma twój produkt?: ");
        double tluszcze = Double.parseDouble(scanner.nextLine());
        if(kcal <0 || bialko < 0 || weglowodany <0 || blonnik <0 || tluszcze <0){
            System.out.println("Takiego produktu nie można dodać ponieważ jeden z makroskładników jest mniejszy od zera!");
        }else {
            zapytaniaDoBazy.insertIntoProdukt(nazwa, kcal, bialko, weglowodany, blonnik, tluszcze);
        }
        }
    }
    public String wypiszProdukt(Produkt produkt){
        return "["+produkt.getId()+"] - "+ produkt.getNazwa()+ ": kcal:"+ produkt.getKcal()+ ", białko: "+produkt.getBialko()
                + ", węglowodany: " + produkt.getWeglowodany()+ ", błonnik: "+produkt.getBlonnik()
                + ", tłuszcze: "+produkt.getTluszcze();
    }
}
