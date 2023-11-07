package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenadzerPosilku{
    public void tworzeniePosilku(MenadzerProduktow menadzerProduktow, Scanner scanner) {
        MenadzerPlikow menadzerPlikow = new MenadzerPlikow();
        System.out.println("Jak ma się nazywać posiłek: ");
        String nazwaPosilku = scanner.nextLine();
        boolean flaga = menadzerPlikow.sprawdzCzyIstniejeTakiPlik(nazwaPosilku);
        if (!flaga) {
            System.out.println("Taki posilek juz istnieje!");
        } else {
            menuTypuPosilku();
            System.out.println("Do jakiego dania chcesz dodać ten posiłek?: ");
            int jakiTyp = Integer.parseInt(scanner.nextLine());
            TypPosilku typ = typPosilku(jakiTyp);
            List<Produkt> produkts = wybierzProduktyDoPosilku(menadzerProduktow);
            new Posilek(typ, nazwaPosilku, produkts);
           // MenadzerPlikow menadzerPlikow = new MenadzerPlikow();
            menadzerPlikow.swtorzPlikZPosilkiem(typ, nazwaPosilku, produkts);
        }
    }

        public List<Produkt> wybierzProduktyDoPosilku (MenadzerProduktow menadzerProduktow){
            //tworzenie Listy Dania składanjącego się z obiektów produktów

            Scanner scanner = new Scanner(System.in);
            System.out.println("Który produkt chcesz dodac: ");
            menadzerProduktow.wyswietlWszystkieProdukty();
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
    public List<Posilek> dodajPosilkiZPlikuDoListy() throws FileNotFoundException {
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        List<Posilek> posilki = new ArrayList<>();
        //File fileNazwy = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/");
        File fileNazwy = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/");
        String[] nazwyPlikow  = fileNazwy.list();
        for (int i = 0; i < nazwyPlikow.length; i++) {
            String s = nazwyPlikow[i];
            List<Produkt> listaDoPosilku = new ArrayList<>();
           // File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/" + s);
            File file = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/" + s);
            Scanner scanner = new Scanner(file);
            String nazwaPosilku= scanner.nextLine();
            TypPosilku t = TypPosilku.valueOf(scanner.nextLine());
            menadzerProduktow.dodajDoListyProduktyZPliku(listaDoPosilku, file, 2);
            posilki.add(new Posilek(t, nazwaPosilku, listaDoPosilku));
        }
        return posilki;


    }
    public void wypiszPosilek(Posilek posilek){
        System.out.println("Nazwa posilku: "+ posilek.getNazwaPosilku());
        System.out.println("jest to:" + posilek.getTypPosilku());
        System.out.println("Nazwa produktow z jakich sie sklada to: ");
        String[] nazwy =new String[ posilek.getSkladPosiku().size()];
        for (int i = 0; i <  posilek.getSkladPosiku().size(); i++) {
            nazwy[i] = posilek.getSkladPosiku().get(i).getNazwa();
            System.out.print(nazwy[i]+ " ");
        }
        System.out.println();
    }
    public Produkt obliczMakro(int ileGram, Produkt produkt){

        double kcal = (produkt.getKcal() * ileGram) / 100;
        double bailko = (produkt.getBialko() * ileGram) / 100;
        double wegle = (produkt.getWeglowodany() * ileGram) / 100;
        double blonnik = (produkt.getBlonnik() * ileGram) / 100;
        double tluszcze = (produkt.getTluszcze() * ileGram) / 100;

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
        }
        return null;
    }
    public void menuTypuPosilku(){
        System.out.println("[1] - Śniadanie");
        System.out.println("[2] - Drugie śniadanie");
        System.out.println("[3] - Obiad");
        System.out.println("[4] - Kolacja");
    }

    }







