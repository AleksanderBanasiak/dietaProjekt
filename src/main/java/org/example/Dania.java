package org.example;

import java.util.List;

public class Dania {
    TypPosilku typPosilku;
    private String nazwaDania;
    private List<Produkt> skladDania;
    public Dania(TypPosilku typPosilku, String nazwaDania, List<Produkt> skladDania) {
        this.typPosilku = typPosilku;
        this.nazwaDania = nazwaDania;
        this.skladDania = skladDania;
    }
    public String getNazwaDania() {
        return nazwaDania;
    }
    public TypPosilku getTypPosilku() {
        return typPosilku;
    }
    public List<Produkt> getSkladDania() {
        return skladDania;
    }
}
