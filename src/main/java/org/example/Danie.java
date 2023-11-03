package org.example;

import java.util.List;
import java.util.Scanner;

public class Danie {


    public void dodajDanie(MenadzerProduktow menadzerProduktow){

        Scanner scanner = new Scanner(System.in);
        System.out.println("który produkt chcesz dodac: ");
        menadzerProduktow.wyswietlWszystkieProdukty();
        int wyborProduktu  = Integer.parseInt(scanner.nextLine());
        wyborProduktu--;
        List<Produkt> produkty = menadzerProduktow.getProdukty();

        Produkt p = menadzerProduktow.wybierzProdukt(wyborProduktu);
        System.out.println("Podaj gramature " + p.getNazwa());
        int ileGram = Integer.parseInt(scanner.nextLine());
        //metoda tutaj


        String wynik=menadzerProduktow.wypiszProdukt(produkty.get(wyborProduktu));

        System.out.println(wynik);







    }
    public void obliczMakro(int ileGram, Produkt produkt){

        double kcal = (produkt.getKcal() * ileGram) / 100;
        double bailko = (produkt.getBialko() * ileGram) / 100;
        double wegle = (produkt.getWeglowodany() * ileGram) / 100;
        double blonnik = (produkt.getBlonnik() * ileGram) / 100;
        double tluszcze = (produkt.getTluszcze() * ileGram) / 100;

        //dodanie tutaj obslugi na pliku

    }




}
