package com.example.mypokerassistant.PokerParts;

public class PokerHandSuitless {
    private char card1;
    private char card2;
    private boolean suited;

    public PokerHandSuitless(char card1, char card2, boolean suited) {
        this.card1 = card1;
        this.card2 = card2;
        this.suited = suited;
    }

    public String getString() {
        return "" + card1 + card2 + suited;
    }
}
