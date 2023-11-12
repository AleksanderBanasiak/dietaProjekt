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
    public List<Produkt> getProdukty() {
        return produkty;
    }
    public void tworzenieProduktu(Scanner scanner) throws IOException {
        MenadzerPlikow menadzerPlikow = new MenadzerPlikow();
        System.out.print("Jak nazywa się twój produkt?: ");
        String nazwa = scanner.nextLine();
        boolean flaga = menadzerPlikow.sprawdzCzyJestWPilku(nazwa, file);
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



            Produkt nowyProdukt = new Produkt(nazwa, kcal, bialko, weglowodany, blonnik, tluszcze);
            menadzerPlikow.zapiszDoPliku(nowyProdukt, file);
            produkty.add(nowyProdukt);
        }
        }
    }
    public void dodajProduktyZPlikuDoListy() throws FileNotFoundException {
        dodajDoListyProduktyZPliku(produkty, file, 0);
    }
    public void dodajDoListyProduktyZPliku(List <Produkt> produkty, File file1, int ilePominac) throws FileNotFoundException {
        Scanner scanner = new Scanner(file1);
        boolean flaga = true;
        while (scanner.hasNext()) {
            if(flaga) {
                for (int i = 0; i < ilePominac; i++) {
                    scanner.nextLine();
                }
                flaga = false;
            }
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
        int i=0;
        for (Produkt produkt : produkty) {
            i++;
            System.out.println("["+i+"] - "+  wypiszProdukt(produkt));
        }
    }
    public String wypiszProdukt(Produkt produkt){
        return produkt.getNazwa().toUpperCase()+ ": kcal:"+ produkt.getKcal()+ ", białko: "+produkt.getBialko()
                + ", węglowodany: " + produkt.getWeglowodany()+ ", błonnik: "+produkt.getBlonnik()
                + ", tłuszcze: "+produkt.getTluszcze();
    }
}
