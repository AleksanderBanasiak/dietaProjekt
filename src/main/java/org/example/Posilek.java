package org.example;

import org.example.Produkt;

import java.util.List;

public class Posilek {

    private String nazwaPosilku;
    TypPosilku typPosilku;
    private List<Produkt> skladPosiku;

    public Posilek(String nazwaPosilku, TypPosilku typPosilku, List<Produkt> skladPosiku) {
        this.nazwaPosilku = nazwaPosilku;
        this.typPosilku = typPosilku;
        this.skladPosiku = skladPosiku;
    }

    public String getNazwaPosilku() {
        return nazwaPosilku;
    }

    public TypPosilku getTypPosilku() {
        return typPosilku;
    }
}
