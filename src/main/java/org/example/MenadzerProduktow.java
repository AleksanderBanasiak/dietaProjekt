package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenadzerProduktow {

    private List<Produkt> produkty = new ArrayList<>();
    File file = new File("produkty.txt");




    public void tworzenieProduktu(Scanner scanner) throws IOException {
        MenadzerPlikow menadzerPlikow = new MenadzerPlikow();

        System.out.println("Jak nazywa się twój produkt?");
        String nazwa = scanner.nextLine();
        boolean flaga = menadzerPlikow.sprawdzCzyJestWPilku(nazwa, file);
        if(!flaga){
            System.out.println("Taki produkt juz istnieje!");
        }else {
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
        menadzerPlikow.zapiszDoPliku(nowyProdukt, file);
        produkty.add(nowyProdukt);
        }
    }
    public void dodajProduktyZPlikuDoListy() throws FileNotFoundException {

        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()){
            String nazwa = scanner.nextLine();
            double kcal = Double.parseDouble(scanner.nextLine());
            double bialko = Double.parseDouble(scanner.nextLine());
            double weglowodany = Double.parseDouble(scanner.nextLine());
            double blonnik = Double.parseDouble(scanner.nextLine());
            double tluszcze = Double.parseDouble(scanner.nextLine());
            Produkt nowyProdukt = new Produkt(nazwa, kcal, bialko, weglowodany, blonnik, tluszcze);
            produkty.add(nowyProdukt);



        }





    }
    public void wyswietlWszystkieProdukty(){
        for (Produkt produkt : produkty) {
            System.out.println(wypiszProdukt(produkt));
        }
    }
    public static String wypiszProdukt(Produkt produkt){
        return "["+produkt.getId() +"] - "+produkt.getNazwa().toUpperCase()+ ": kcal:"+ produkt.getKcal()+ ", białko: "+produkt.getBialko()
                + ", węglowodany: " + produkt.getWeglowodany()+ ", błonnik: "+produkt.getBlonnik()
                + ", tłuszcze: "+produkt.getTluszcze();
    }



}
