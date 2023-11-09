package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public void stworzPlikZDanymDniem(TypPosilku typ, String nazwa, List<Produkt> produkts) throws IOException {

        DateTimeFormatter wyswietlDate = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate teraz = LocalDate.now();
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
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println("-".repeat(8));
                printWriter.println(file.getName());
                printWriter.println("-".repeat(8));
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


    public void stworzPlikZDaniem(TypPosilku typ, String nazwa, List<Produkt> produkts){
        //File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/"+nazwa);
        File file = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/"+nazwa);
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
                    printWriter.println(nazwa.toUpperCase());
                    printWriter.println(typ);
                    for (Produkt produkt : produkts) {
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

