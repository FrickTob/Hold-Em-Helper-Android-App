package com.example.mypokerassistant.PokerParts;

/**
 * Class that represents a hand in poker with a boolean data field to see if the hand is suited
 * rather than specify suit of each card in hand. Used for the Starting Hand Guide section of app.
 * @author FrickTob
 */
public class PokerHandSuitless {
    private char card1;
    private char card2;
    private boolean suited;
    private double winPercent = 0;

    public PokerHandSuitless(String handString) {
        Character[] chars = new Character[handString.length()];
        for(int i = 0; i < handString.length(); i++) {
            chars[i] = handString.charAt(i);
            chars[i] = Character.toUpperCase(chars[i]); // case insensitive user input
        }

        card1 = chars[0]; // value 1
        card2 = chars[2]; // value 2
        suited = chars[1] == chars[3]; // suit 1 and suit 2

    }
    public PokerHandSuitless(char card1, char card2, boolean suited) {
        this.card1 = card1;
        this.card2 = card2;
        this.suited = suited;
    }
    public PokerHandSuitless(char card1, char card2, boolean suited, double winPercent) {
        this.card1 = card1;
        this.card2 = card2;
        this.suited = suited;
        this.winPercent = winPercent;
    }

    public double getWinPercent(){
        return this.winPercent;
    }

    public String getString() {
        // Format Output for Comparison to Listing in PokerHandList
        char tempCard1 = convertToAscendingASCII(card2);
        char tempCard2 = convertToAscendingASCII(card1);

        char highest = tempCard1 > tempCard2 ? card1 : card2;
        char lowest = tempCard1 > tempCard2 ? card2 : card1;

        return "" + highest + lowest + suited;

    }

    public char convertToAscendingASCII(char value) {
        switch (value) {
            case '0': return ':';
            case 'J': return ';';
            case 'Q': return '<';
            case 'K': return '=';
            case 'A': return '>';
            default : return value;
        }
    }
}
