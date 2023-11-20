
package org.example;

import org.example.MenadzerProduktow;
import org.example.TypPosilku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenadzerDania {

        ZapytaniaDoBazy zapytaniaDoBazy = new ZapytaniaDoBazy();
    public void tworzenieDania(MenadzerProduktow menadzerProduktow, Scanner scanner) {
        if(!zapytaniaDoBazy.open()){
            System.out.println("Nie można otworzyć bazy danych");
            return;
        }
        System.out.println("-".repeat(24));
        System.out.print("Jak ma się nazywać danie: ");
        String nazwaPosilku = scanner.nextLine();
        boolean flaga = zapytaniaDoBazy.sprawdzCzyJestWBazieTakieDanie(nazwaPosilku, zapytaniaDoBazy);
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
            List<Integer> produkts = wybierzProduktyDoDania(menadzerProduktow);
            zapytaniaDoBazy.insertIntoDanie(nazwaPosilku, typ);
            int idDania = zapytaniaDoBazy.pobierzOstatnieIDDania();
            zapytaniaDoBazy.dodajListeProduktowDoDania(produkts, idDania-1);
        }
    }
        public List<Integer> wybierzProduktyDoDania(MenadzerProduktow menadzerProduktow){
            Scanner scanner = new Scanner(System.in);
            List<Produkt> produkty = zapytaniaDoBazy.wyswietlWyszyskieProdukty();
            for (Produkt produkt : produkty){
            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
        }
            System.out.print("Które produkty chcesz dodac: ");
            String wybraneProdukty = scanner.nextLine();
            String[] splitWybraneProdukty = wybraneProdukty.split(",");
            List<Integer> tablicaWybranychProduktow = new ArrayList<>();
            for (int i = 0; i < splitWybraneProdukty.length; i++) {
                tablicaWybranychProduktow.add((Integer.parseInt(splitWybraneProdukty[i])));
            }
            return tablicaWybranychProduktow;
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


    public List<String> wyswietlDanie(TypPosilku typPosilku){
        if(!zapytaniaDoBazy.open()){
            System.out.println("Nie można otworzyć bazy danych");
        }
        List<Integer> idDan = zapytaniaDoBazy.wyswietlIdDaniaDanegoTypu(typPosilku);
        List<String> nazwyDan = zapytaniaDoBazy.wyswietlNazweDaniaDanegoTypu(typPosilku);
        List<String> wybraneDania = new ArrayList<>();


        for (int i = 0; i < nazwyDan.size(); i++) {
            StringBuilder sb = new StringBuilder();
                sb.append("[");
            int idDania = idDan.get(i);
            sb.append(idDania);
                sb.append("] - ");
                sb.append(nazwyDan.get(i));
                sb.append("(");
            List<String> nazwyProduktow = zapytaniaDoBazy.wyswietlWszystkieNazwyProduktowZDanegoDania(idDania);
            for (int j = 0; j < nazwyProduktow.size(); j++) {
                if(j!=0){
                  sb.append(",").append(nazwyProduktow.get(j));
                }else {
                  sb.append(nazwyProduktow.get(j));
                }
            }
                sb.append(")");
            wybraneDania.add(String.valueOf(sb));
        }
            return wybraneDania;
    }
    public String wyswietlKonkretneDanie(TypPosilku typPosilku, int id){
        if(!zapytaniaDoBazy.open()){
            System.out.println("Nie można otworzyć bazy danych");
        }
        List<Integer> idDan = zapytaniaDoBazy.wyswietlIdDaniaDanegoTypu(typPosilku);
        List<String> nazwyDan = zapytaniaDoBazy.wyswietlNazweDaniaDanegoTypu(typPosilku);
        List<String> wybraneDania = new ArrayList<>();



            StringBuilder sb = new StringBuilder();
                sb.append(zapytaniaDoBazy.wyswietlDanieODanymID(id));
                sb.append("(");
            List<String> nazwyProduktow = zapytaniaDoBazy.wyswietlWszystkieNazwyProduktowZDanegoDania(id);
            for (int j = 0; j < nazwyProduktow.size(); j++) {
                if(j!=0){
                  sb.append(",").append(nazwyProduktow.get(j));
                }else {
                  sb.append(nazwyProduktow.get(j));
                }
            }
                sb.append(")");

        return String.valueOf(sb);
    }


    public Produkt obliczMakro(int ileGram, Produkt produkt) {
        double kcal = Math.round((produkt.getKcal() * ileGram) / 100);
        double bailko = Math.round((produkt.getBialko() * ileGram) / 100);
        double wegle = Math.round((produkt.getWeglowodany() * ileGram) / 100);
        double blonnik = Math.round((produkt.getBlonnik() * ileGram) / 100);
        double tluszcze = Math.round((produkt.getTluszcze() * ileGram) / 100);
        return new Produkt(produkt.getId(), produkt.getNazwa(), kcal, bailko, wegle, blonnik, tluszcze);
    }

}



/*
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

 */













