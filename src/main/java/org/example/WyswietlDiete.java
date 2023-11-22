package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WyswietlDiete {
    ZapytaniaDoBazy zapytaniaDoBazy = new ZapytaniaDoBazy();
    Scanner scanner = new Scanner(System.in);
    private List<String> dania = new ArrayList<>();


    public void wyswietDiete(){
        String wybranyDzien = wybierzDzien();
        wyswietlDateDlaWybranegoDnia(wybranyDzien);
        wypiszWszystko();
    }
    public String wybierzDzien(){
        if(!zapytaniaDoBazy.open()){
            System.out.println("Nie można otworzyć bazy danych");
            return "";
        }
        List<String> daty = zapytaniaDoBazy.wyswietlDaty();
        for (int i = 0; i < daty.size(); i++) {
            System.out.println("["+(daty.size()-i)+"] - " + daty.get(i));
        }
        System.out.print("Wybierz dzień który chcesz wyświetlić: ");
        int data = Integer.parseInt(scanner.nextLine());

        while (data > daty.size() || data <= 0){
            System.out.println("Podałeś liczbe z poza zakresu");
            System.out.print("Wybierz dzień jeszcze raz: ");
            data = Integer.parseInt(scanner.nextLine());
        }
        return zapytaniaDoBazy.wyswietlWybranaDate(data);
    }
    public void wyswietlDateDlaWybranegoDnia(String dzien){
        List<String> sniadanie = zapytaniaDoBazy.pobierzDanePosilku(TypPosilku.SNIADANIE, LocalDate.parse(dzien));
        List<String> drugieSniadanie = zapytaniaDoBazy.pobierzDanePosilku(TypPosilku.DRUGIE_SNIADANIE, LocalDate.parse(dzien));
        List<String> obiad = zapytaniaDoBazy.pobierzDanePosilku(TypPosilku.OBIAD, LocalDate.parse(dzien));
        List<String> kolacja = zapytaniaDoBazy.pobierzDanePosilku(TypPosilku.KOLACJA, LocalDate.parse(dzien));
        List<String> dodatkoweDania = zapytaniaDoBazy.pobierzDanePosilku(TypPosilku.DODATKOWE_DANIE, LocalDate.parse(dzien));
        String podsumowanie = zapytaniaDoBazy.pobierzSumyZCalegoDnia(LocalDate.parse(dzien));
        sprawdzCzyPuste(sniadanie);
        sprawdzCzyPuste(drugieSniadanie);
        sprawdzCzyPuste(obiad);
        sprawdzCzyPuste(kolacja);
        sprawdzCzyPuste(dodatkoweDania);
        dania.add(podsumowanie);
    }
    public void sprawdzCzyPuste(List<String> lista) {
        if (lista.size() != 2) {
            dania.addAll(lista);
            dania.add("-----------------------------------------------------------------------");
        }
    }
    public void wypiszWszystko(){
        for (int i = 0; i < dania.size(); i++) {
            System.out.println(dania.get(i));
        }
    }
}
