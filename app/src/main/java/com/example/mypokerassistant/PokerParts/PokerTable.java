package com.example.mypokerassistant.PokerParts;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class to represent a poker table with data fields to hold five PokerCards
 * @author FrickTob
 *
 */
public class PokerTable {
	private PokerCard flop1;
	private PokerCard flop2;
	private PokerCard flop3;
	private PokerCard turn;
	private PokerCard river;
	
	public PokerTable(PokerCard flop1, PokerCard flop2, PokerCard flop3, PokerCard turn, PokerCard river) {
		this.flop1 = flop1;
		this.flop2 = flop2;
		this.flop3 = flop3;
		this.turn = turn;
		this.river = river;
	}

	/**
	 * Constructor to generate random table given a poker hand
	 * @param hand hand of player which restricts what cards can appear on table
	 */
	public PokerTable(PokerHand hand) {
		Random r = new Random();
		ArrayList<PokerCard> remainingCards = new ArrayList<>();
		for(int i = 0; i < AllCards.NUMCARDS; i++)
			remainingCards.add(AllCards.getCard(i));
		
		remainingCards.remove(hand.getCard1());
		remainingCards.remove(hand.getCard2());
		
		int numCardsRemaining = AllCards.NUMCARDS - 2;
		
		PokerCard currCard = remainingCards.get(r.nextInt(numCardsRemaining--));
		this.flop1 = currCard;
		remainingCards.remove(currCard);
		
		currCard = remainingCards.get(r.nextInt(numCardsRemaining--));
		this.flop2 = currCard;
		remainingCards.remove(currCard);
		
		currCard = remainingCards.get(r.nextInt(numCardsRemaining--));
		this.flop3 = currCard;
		remainingCards.remove(currCard);
		
		currCard = remainingCards.get(r.nextInt(numCardsRemaining--));
		this.turn = currCard;
		remainingCards.remove(currCard);
		
		currCard = remainingCards.get(r.nextInt(numCardsRemaining));
		this.river = currCard;
		remainingCards.remove(currCard);
	}

	public static PokerCard convertToCard(int valueNum, int suitNum) {
		char value = 'X';
		char suit = 'X';
		switch (valueNum) {
		case 1: value = 'A';
				break;
		case 2: value = '2';
				break;
		case 3: value = '3';
				break;
		case 4: value = '4';
				break;
		case 5: value = '5';
				break;
		case 6: value = '6';
				break;
		case 7: value = '7';
				break;
		case 8: value = '8';
		 		break;
		case 9: value = '9';
				break;
		case 10: value = '0';
				 break;
		case 11: value = 'J';
				 break;
		case 12: value = 'Q';
				 break;
		case 13: value = 'K';
				 break;
		}
		switch (suitNum) {
		case 1: suit = 'C';
				break;
		case 2: suit = 'D';
				break;
		case 3: suit = 'H';
				break;
		case 4: suit = 'S';
				break;
		}
		if(value == 'X') System.out.println("Invalid Value Input: Must be a number between 1 and 13");
		if(suit == 'X') System.out.println("Invalid Suit Input: Must be a number between 1 and 4");
		return new PokerCard(value, suit);
	}

	public void setFlop1(PokerCard flop1) {
		this.flop1 = flop1;
	}
	
	public PokerCard getFlop1() {
		return flop1;
	}
	
	public void setFlop2(PokerCard flop2) {
		this.flop2 = flop2;
	}
	
	public PokerCard getFlop2() { return flop2; }
	
	public void setFlop3(PokerCard flop3) {
		this.flop3 = flop3;
	}
	
	public PokerCard getFlop3() {
		return flop3;
	}

	public PokerCard getTurn() {
		return turn;
	}

	public void setTurn(PokerCard turn) {
		this.turn = turn;
	}

	public PokerCard getRiver() {
		return river;
	}

	public void setRiver(PokerCard river) {
		this.river = river;
	}
	
	public void printCommunity() {
		System.out.println(flop1.getCard() + flop2.getCard() + flop3.getCard() + turn.getCard() + river.getCard());
	}
}
