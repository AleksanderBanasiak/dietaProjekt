package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Danie {


    public void kod(MenadzerProduktow menadzerProduktow){
        wyswietlListeZWybranymiProduktami(wybierzProduktyDoDania(menadzerProduktow));
    }


    public List<Produkt> wybierzProduktyDoDania(MenadzerProduktow menadzerProduktow){
        //tworzenie Listy Dania składanjącego się z obiektów produktów

        Scanner scanner = new Scanner(System.in);
            System.out.println("Który produkt chcesz dodac: ");
            menadzerProduktow.wyswietlWszystkieProdukty();
            String wybraneProdukty = scanner.nextLine();
            String[] splitWybraneProdukty = wybraneProdukty.split(",");
            int[] tablicaWybranychProduktow = new int[splitWybraneProdukty.length];
        for (int i = 0; i < splitWybraneProdukty.length; i++) {
            tablicaWybranychProduktow[i] = (Integer.parseInt(splitWybraneProdukty[i])-1);
        }
        List<Produkt> listaWybranychProduktow = new ArrayList<>();
        List<Produkt> produkty = menadzerProduktow.getProdukty();
        int ilosc =produkty.size();
        for (int i = 0; i < tablicaWybranychProduktow.length; i++) {
            if(tablicaWybranychProduktow[i] < 0 || tablicaWybranychProduktow[i] >= ilosc){
                System.out.println("błąd koniec programu");
                System.exit(0);
            }
        }
        for (int i = 0; i < tablicaWybranychProduktow.length; i++) {
                listaWybranychProduktow.add(produkty.get(tablicaWybranychProduktow[i]));
        }
        return listaWybranychProduktow;
    }



    public void wyswietlListeZWybranymiProduktami(List<Produkt> lista){

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getNazwa());
        }

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
