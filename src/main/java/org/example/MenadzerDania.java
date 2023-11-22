
package org.example;

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

            if(produkts != null){
                zapytaniaDoBazy.insertIntoDanie(nazwaPosilku, typ);
                int idDania = zapytaniaDoBazy.pobierzOstatnieIDDania();
                zapytaniaDoBazy.dodajListeProduktowDoDania(produkts, idDania-1);
            }
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
            for (Integer integer : tablicaWybranychProduktow) {
                if (integer > produkty.size() || integer <= 0) {
                    System.out.println("Nie można utworzyć takiego dnia ponieważ wybrano złe produkty");
                    return null;
                }
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
    public Produkt obliczMakro(int ileGram, Produkt produkt) {
        double kcal = Math.round((produkt.getKcal() * ileGram) / 100);
        double bailko = Math.round((produkt.getBialko() * ileGram) / 100);
        double wegle = Math.round((produkt.getWeglowodany() * ileGram) / 100);
        double blonnik = Math.round((produkt.getBlonnik() * ileGram) / 100);
        double tluszcze = Math.round((produkt.getTluszcze() * ileGram) / 100);
        return new Produkt(produkt.getId(), produkt.getNazwa(), kcal, bailko, wegle, blonnik, tluszcze);
    }

}













