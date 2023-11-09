package org.example;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenadzerPlikow {

    public void zapiszDoPliku(Produkt produkt, File file) {
        if (file.canWrite()) {
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(produkt.getNazwa());
                printWriter.println(produkt.getKcal());
                printWriter.println(produkt.getBialko());
                printWriter.println(produkt.getWeglowodany());
                printWriter.println(produkt.getBlonnik());
                printWriter.println(produkt.getTluszcze());
                printWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void stworzPlikZDanymDniem(Dania dania, int miejsceDodatkowegoPosilku) throws IOException {

        int ileSkipnac =0;
        if(miejsceDodatkowegoPosilku ==0){
            ileSkipnac =  ileSkipnac(dania.getTypPosilku());
        }else {
            ileSkipnac =  ileSkipnacDlaDodatkowegoPosilku(miejsceDodatkowegoPosilku);
        }


        MenadzerDania menadzerDania = new MenadzerDania();
        DateTimeFormatter wyswietlDate = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate teraz = LocalDate.now();
        String dayOfWeek = DayOfWeek.from(teraz).toString().toLowerCase();
        //File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/MojaDieta/" +wyswietlDate.format(teraz));
        File file = new File("/C:/IntelliJNauka/dietaProjekt/src/MojaDieta/" +wyswietlDate.format(teraz));
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (file.canWrite()) {
            try {
                FileWriter fileWriter = new FileWriter(file, false);
                PrintWriter printWriter = new PrintWriter(fileWriter, true);
                printWriter.print(dayOfWeek);
                printWriter.print(" ");
                printWriter.println(file.getName());

                for (int i = 0; i < ileSkipnac; i++) {
                    //printWriter.println();
                }
                Produkt pelneMakro = menadzerDania.obliczMarkoZCalegoDania(dania.getSkladDania());

                if(dania.getTypPosilku().equals(TypPosilku.SNIADANIE)) {
                    printWriter.print("Sniadanie: " + dania.getNazwaDania() + "(");
                    for (int i = 0; i < dania.getSkladDania().size(); i++) {
                            printWriter.print(dania.getSkladDania().get(i).getNazwa());
                        if(i != dania.getSkladDania().size() -1){
                            printWriter.print(",");
                        }
                    }
                    printWriter.println(")");
                    printWriter.print(pelneMakro.getNazwa() + pelneMakro.getKcal() + " kcal, " + pelneMakro.getBialko() + " białka, "+ pelneMakro.getWeglowodany()+ " węgli, "
                    + pelneMakro.getBlonnik() + " błonnika, "+ pelneMakro.getTluszcze() + " tłuszczy");
                }
                else {
                    printWriter.println();
                    printWriter.println();
                }

                // tutaj sie to nadpisuje nie wiem czy to powinno tak dzialac
//                printWriter.println(nazwa.toUpperCase());
//                printWriter.println(typ);
//                for (Produkt produkt : produkts) {
//                    printWriter.println(produkt.getNazwa());
//                    printWriter.println(produkt.getKcal());
//                    printWriter.println(produkt.getBialko());
//                    printWriter.println(produkt.getWeglowodany());
//                    printWriter.println(produkt.getBlonnik());
//                    printWriter.println(produkt.getTluszcze());
                //}
                printWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }



    public int ileSkipnac(TypPosilku typPosilku){ // tutaj powinno byc tez gdzie wybralismy danie dodatkowe

        int ileSkipnac =0;

        switch (typPosilku){
            case SNIADANIE -> ileSkipnac =0;
            case DRUGIE_SNIADANIE ->ileSkipnac =5;
            case OBIAD -> ileSkipnac =6;
            case KOLACJA -> ileSkipnac =2;
            }
        return ileSkipnac;
    }
    public int ileSkipnacDlaDodatkowegoPosilku(int miejsceDodatkowegoPosilku){
        int ileSkipnac =0;
        switch (miejsceDodatkowegoPosilku){
            case 1 -> ileSkipnac = 2;
            case 2 -> ileSkipnac = 2;
            case 3 -> ileSkipnac = 2;
            default -> ileSkipnac = 0;
        }
        return ileSkipnac;
    }



    public void stworzPlikZDaniem(Dania dania){
        //File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/"+nazwa);
        File file = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/"+dania.getNazwaDania());
        if(!file.exists()) {
            try{
                file.createNewFile();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
            if (file.canWrite()) {
                try {
                    FileWriter fileWriter = new FileWriter(file, true);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println(dania.getNazwaDania().toUpperCase());
                    printWriter.println(dania.typPosilku);
                    for (Produkt produkt : dania.getSkladDania()) {
                        printWriter.println(produkt.getNazwa());
                        printWriter.println(produkt.getKcal());
                        printWriter.println(produkt.getBialko());
                        printWriter.println(produkt.getWeglowodany());
                        printWriter.println(produkt.getBlonnik());
                        printWriter.println(produkt.getTluszcze());
                    }
                    printWriter.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
    }


    public boolean sprawdzCzyIstniejeTakiPlik(String nazwa){

        //File fileNazwy = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/");
        File fileNazwy = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/");
        String[] nazwyPlikow  = fileNazwy.list();
        boolean flaga = true;
        for (String s : nazwyPlikow) {
            if (Objects.equals(s, nazwa)) {
                flaga = false;
            }
        }
        return flaga;
    }


    public boolean sprawdzCzyJestWPilku(String nazwa, File file) throws FileNotFoundException {

        int licznik = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            licznik++;
            String linia = scanner.nextLine();
            if (licznik % 6 == 1 && nazwa.equalsIgnoreCase(linia)) {
                return false;
            }
        }
        return true;
    }
}

