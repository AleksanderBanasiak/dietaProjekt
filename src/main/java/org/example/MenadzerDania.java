package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenadzerDania {
    public void tworzenieDania(MenadzerProduktow menadzerProduktow, Scanner scanner) {
        MenadzerPlikow menadzerPlikow = new MenadzerPlikow();
        System.out.println("-".repeat(24));
        System.out.print("Jak ma się nazywać danie: ");
        String nazwaPosilku = scanner.nextLine();
        boolean flaga = menadzerPlikow.sprawdzCzyIstniejeTakiPlik(nazwaPosilku);
        if (!flaga) {
            System.out.println("Takie danie juz istnieje!");
        } else {
            menuTypuPosilku();
            System.out.print("Do jakiego posiłku chcesz dodać to danie?: ");
            int jakiTyp = Integer.parseInt(scanner.nextLine());
            while (jakiTyp <= 0 || jakiTyp > TypPosilku.values().length){
                System.out.println("Podałeś złą wartość!");
                System.out.print("Podaj jeszcze raz: ");
                jakiTyp = Integer.parseInt(scanner.nextLine());

            }

            TypPosilku typ = typPosilku(jakiTyp);
            List<Produkt> produkts = wybierzProduktyDoDania(menadzerProduktow);
            Dania noweDanie = new Dania(typ, nazwaPosilku, produkts);
            menadzerPlikow.stworzPlikZDaniem(noweDanie);
        }
    }

        public List<Produkt> wybierzProduktyDoDania(MenadzerProduktow menadzerProduktow){
            //tworzenie Listy Dania składanjącego się z obiektów produktów

            Scanner scanner = new Scanner(System.in);
            menadzerProduktow.wyswietlWszystkieProdukty();
            System.out.print("Które produkty chcesz dodac: ");
            String wybraneProdukty = scanner.nextLine();
            String[] splitWybraneProdukty = wybraneProdukty.split(",");
            int[] tablicaWybranychProduktow = new int[splitWybraneProdukty.length];
            for (int i = 0; i < splitWybraneProdukty.length; i++) {
                tablicaWybranychProduktow[i] = (Integer.parseInt(splitWybraneProdukty[i]) - 1);
            }
            List<Produkt> listaWybranychProduktow = new ArrayList<>();
            List<Produkt> produkty = menadzerProduktow.getProdukty();
            int ilosc = produkty.size();
            for (int i = 0; i < tablicaWybranychProduktow.length; i++) {
                if (tablicaWybranychProduktow[i] < 0 || tablicaWybranychProduktow[i] >= ilosc) {
                    System.out.println("Podałeś wartość z poza zakresu!");
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
    public List<Dania> dodajDanieZPlikuDoListy() throws FileNotFoundException {
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        List<Dania> danias = new ArrayList<>();
        //File fileNazwy = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/");
        File fileNazwy = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/");
        String[] nazwyPlikow  = fileNazwy.list();
        for (int i = 0; i < nazwyPlikow.length; i++) {
            String s = nazwyPlikow[i];
            List<Produkt> listaDoPosilku = new ArrayList<>();
            //File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/" + s);
            File file = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/" + s);
            Scanner scanner = new Scanner(file);
            String nazwaPosilku= scanner.nextLine();
            TypPosilku t = TypPosilku.valueOf(scanner.nextLine());
            menadzerProduktow.dodajDoListyProduktyZPliku(listaDoPosilku, file, 2);
            danias.add(new Dania(t, nazwaPosilku, listaDoPosilku));
        }
        return danias;


    }
    public void wypiszDanie(Dania dania){
        System.out.print(dania.getNazwaDania());
        System.out.print("(");
        String[] nazwy =new String[ dania.getSkladDania().size()];
        for (int i = 0; i <  dania.getSkladDania().size(); i++) {
            nazwy[i] = dania.getSkladDania().get(i).getNazwa();
            if(i != dania.getSkladDania().size() -1){
                System.out.print(nazwy[i]+ ",");
            }else {
                System.out.print(nazwy[i]+")");
            }
        }
        System.out.println();

    }
    public void wypiszDanieWPosilku(Dania dania){
        System.out.print(dania.getNazwaDania());
        String[] nazwy =new String[ dania.getSkladDania().size()];
        for (int i = 0; i <  dania.getSkladDania().size(); i++) {
            nazwy[i] = dania.getSkladDania().get(i).getNazwa();
            System.out.println(nazwy[i]);
        }
        System.out.println();

    }
    public List<Dania> stworzListeZDaniamiDanegoTypu(TypPosilku typPosilku) throws FileNotFoundException {
        MenadzerDania menadzerDania = new MenadzerDania();
        List<Dania> danias = menadzerDania.dodajDanieZPlikuDoListy();
        List<Dania> danias1 = new ArrayList<>();
        TypPosilku wybranyTyp;
        for (int i = 0; i < danias.size(); i++) {
            wybranyTyp = danias.get(i).getTypPosilku();
            if (wybranyTyp == typPosilku) {
                danias1.add(danias.get(i));
            }
        }
        return danias1;
    }



    public void wyswietlDaniaZListy(List<Dania> lista) throws FileNotFoundException {
        MenadzerDania menadzerDania = new MenadzerDania();
        int licznik=0;
        for (int i = 0; i < lista.size(); i++) {
                 licznik++;
                 System.out.print("["+licznik+"] - ");
                 menadzerDania.wypiszDanie(lista.get(i));
        }
    }

    public Produkt obliczMarkoZCalegoDania(List<Produkt> lista){
        double caleKcal =0;
        double caleBialko=0;
        double caleWegle=0;
        double caleBlonnik=0;
        double caleTluszcze=0;

        for (int i = 0; i < lista.size(); i++) {
            caleKcal += lista.get(i).getKcal();
            caleBialko += lista.get(i).getBialko();
            caleWegle += lista.get(i).getWeglowodany();
            caleBlonnik += lista.get(i).getBlonnik();
            caleTluszcze += lista.get(i).getTluszcze();
        }



        return new Produkt("Makroskładniki dania: ",caleKcal,caleBialko,caleWegle,caleBlonnik,caleTluszcze);
    }





    public Produkt obliczMakro(int ileGram, Produkt produkt){

        double kcal = Math.round((produkt.getKcal() * ileGram) / 100);
        double bailko = Math.round((produkt.getBialko() * ileGram) / 100);
        double wegle = Math.round((produkt.getWeglowodany() * ileGram) / 100);
        double blonnik = Math.round((produkt.getBlonnik() * ileGram) / 100);
        double tluszcze = Math.round((produkt.getTluszcze() * ileGram) / 100);
        System.out.println(kcal);

       return new Produkt(produkt.getNazwa(), kcal, bailko, wegle, blonnik, tluszcze);

    }
    public TypPosilku typPosilku(int typ){
        switch (typ) {
            case 1 -> {
                return TypPosilku.SNIADANIE;
            }
            case 2 -> {
                return TypPosilku.DRUGIE_SNIADANIE;
            }
            case 3 -> {
                return TypPosilku.OBIAD;
            }
            case 4 -> {
                return TypPosilku.KOLACJA;
            }
            case 5 -> {
                return TypPosilku.DODATKOWE_DANIE;
            }
        }
        return null;
    }
    public void menuTypuPosilku(){
        System.out.println("[1] - Śniadanie");
        System.out.println("[2] - Drugie śniadanie");
        System.out.println("[3] - Obiad");
        System.out.println("[4] - Kolacja");
        System.out.println("[5] - Dodatkowe danie");
    }

    }







