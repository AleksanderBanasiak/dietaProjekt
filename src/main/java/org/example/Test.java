package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {

      //  MenadzerPosilkow menadzerPosilkow = new MenadzerPosilkow();
      //  menadzerPosilkow.pelenProgram(TypPosilku.SNIADANIE);

        MenadzerPlikow menadzerPlikow = new MenadzerPlikow();
       // menadzerPlikow.stworzPlikZDanymDniem(TypPosilku.SNIADANIE, "platki z mlekiem", );
        String nazwa = "sprawdzam cos";
        File file = new File(nazwa);

        FileWriter fileWriter = new FileWriter(file, false);
        PrintWriter printWriter = new PrintWriter(fileWriter, true);


        printWriter.println("ewe");
        printWriter.println("ewwe");
        printWriter.println("ewewewe");

        printWriter.close();
    }

}








