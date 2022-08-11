package com.mypokerassistant.holdemhelper.PokerParts;

/**
 * Represents a real poker card with a value and suit
 * @author FrickTob
 */
public class PokerCard  {
	private char value;
	private char suit;
	
	public PokerCard(char value, char suit) {
		this.setValue(value);
		this.setSuit(suit);
	}
	


	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public char getSuit() {
		return suit;
	}

	public void setSuit(char suit) {
		this.suit = suit;
	}
	public String getCard() {
		return "" + value + suit;
	}
	
	public void printCard() {
		 System.out.println("" + value + suit);
	}


	
	@Override
	public boolean equals(Object card2) {
		if(card2 instanceof PokerCard) {
		return this.getValue() == ((PokerCard) card2).getValue() && this.getSuit() == ((PokerCard) card2).getSuit();
		}
		return false;
	}
}
	
