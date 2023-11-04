package org.example;

import org.example.Produkt;

import java.util.List;

public class Posilek {

    TypPosilku typPosilku;
    private String nazwaPosilku;
    private List<Produkt> skladPosiku;

    public Posilek( TypPosilku typPosilku,String nazwaPosilku, List<Produkt> skladPosiku) {
        this.typPosilku = typPosilku;
        this.nazwaPosilku = nazwaPosilku;
        this.skladPosiku = skladPosiku;
    }

    public String getNazwaPosilku() {
        return nazwaPosilku;
    }

    public TypPosilku getTypPosilku() {
        return typPosilku;
    }

    public List<Produkt> getSkladPosiku() {
        return skladPosiku;
    }
}
