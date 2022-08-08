package com.example.mypokerassistant.PokerStats;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerTable;

import java.util.ArrayList;

/**
 * Contains methods to analyze a hand so that it may be compared to other hands
 * to determine which hand is stronger.
 * @author tobyf
 */
public class AnalyzeHand {

	private String handAndTable;
	private ArrayList<Character> values;
	private ArrayList<Character> suits;
	private int handPriority; // ranking of hand
	
	@SuppressLint("NewApi")
	public AnalyzeHand(@NonNull PokerHand hand, @NonNull PokerTable table) {
		this.values = new ArrayList<>();
		this.suits = new ArrayList<>();	

		
		handAndTable = "" + hand.getCard1().getCard() + hand.getCard2().getCard() 
				+ table.getFlop1().getCard() + table.getFlop2().getCard() + table.getFlop3().getCard()
				+ table.getTurn().getCard() + table.getRiver().getCard();

		// Add all values and sort them
		values.add(handAndTable.charAt(0));
		values.add(handAndTable.charAt(2));
		values.add(handAndTable.charAt(4));
		values.add(handAndTable.charAt(6));
		values.add(handAndTable.charAt(8));
		values.add(handAndTable.charAt(10));
		values.add(handAndTable.charAt(12));
		convertValuesToAscendingASCII();
		values.sort(null);

		// Add all suits and sort them
		suits.add(handAndTable.charAt(1));
		suits.add(handAndTable.charAt(3));
		suits.add(handAndTable.charAt(5));
		suits.add(handAndTable.charAt(7));
		suits.add(handAndTable.charAt(9));
		suits.add(handAndTable.charAt(11));
		suits.add(handAndTable.charAt(13));
		suits.sort(null);
		
		handPriority = determineHandStrength();
		
	}
	
//	Give Hand a Tier that can be compared to other hands
//	In the event of a tie, tie-breaker methods must be used in compare hands classes
	public int determineHandStrength() {
		if(isStraightFlush() != 'X')
			return 1;
		if(isFourofaKind() != 'X')
			return 2;
		if(isFullHouse()[1] != 'X' && isFullHouse()[0] != 'X') 
			return 3;
		if(isFlush()[0] != 'X') // only need to check first element because no elements are changed if hand is not a flush
			return 4;
		if(isStraight()!= 'X')
			return 5;
		if(isThreeOfaKind() != 'X')
			return 6;
		if(isTwoPair()[0] != 'X' && isTwoPair()[1] != 'X')
			return 7;
		if(isOnePair() != 'X')
			return 8;
		else // high card
			return 9;
	}
	
	public int getHandPriority() {
		return handPriority;
	}
	
//	10, J, Q, K, and A must be converted to :, ;, <, =, and > respectively so that ASCII numbers can be compared more simply
	public void convertValuesToAscendingASCII() {
		for(int i = 0; i < values.size(); i++) {
			if(values.get(i) == '0') {
				values.remove(i);
				values.add(i,':');
			}
			else if(values.get(i) == 'J') {
				values.remove(i);
				values.add(i,';');
			}
			else if(values.get(i) == 'Q') {
				values.remove(i);
				values.add(i,'<');
			}
			else if(values.get(i) == 'K') {
				values.remove(i);
				values.add(i,'=');
			}
			else if(values.get(i) == 'A') {
				values.remove(i);
				values.add(i,'>');
			}
		}
	}
	
//	Hand Type Determiner Methods
	public char isStraightFlush() {
		if(values.get(0) == '2' && suits.get(0) == suits.get(6)) {// starts with two so check last card for wrap around straight
			if(values.get(6) == '>' && suits.get(6) == suits.get(0) // check last card is ace that matches two
					&& values.get(1) == '3' && suits.get(5) == suits.get(0) // second card is three to match two
					&& values.get(2) == '4' && suits.get(4) == suits.get(0) // third is four to match two
					&& values.get(3) == '5' && suits.get(3) == suits.get(0)) // fourth is five to match two
				return '5';
		}
			
		for(int i = 0; i < suits.size() - 4; i++) {
			if(suits.get(i) == suits.get(i + 1) && values.get(i) - values.get(i + 1) == -1
					&& suits.get(i + 1) == suits.get(i + 2) && values.get(i + 1) - values.get(i + 2) == -1
					&& suits.get(i + 2) == suits.get(i + 3) && values.get(i + 2) - values.get(i + 3) == -1
					&& suits.get(i + 3) == suits.get(i + 4) && (values.get(i + 3) - values.get(i + 4) == -1))
				return values.get(i + 4);
		}
		return 'X';
	}
	
	public char isFourofaKind() {
		for(int i = 0; i < values.size() - 3; i++)
			if(values.get(i) == values.get(i + 1) && values.get(i) == values.get(i + 2)
			&& values.get(i) == values.get(i + 3))
				return values.get(i);
		return 'X';
	}
	
	public char[] isFullHouse() {
		char[] tripleThenPair = {'X', 'X'};
		char triple = isThreeOfaKind();
		if(triple == 'X') return tripleThenPair; //no triple so no full house
		tripleThenPair[0] = triple; // there is a triple so set first element to the triple char and look for a pair
		
		
		int tripleIndex = values.indexOf(triple);
		for(int i = values.size() - 2; i >= 0; i--) {// start at end and scan for pair
			if (tripleIndex + 1 == i) { // triple is last three elements
				i -=2;
			}
			if (tripleIndex + 2 == i) { // triple is elsewhere
				i -= 3;
			}
			else if(values.get(i) == values.get(i + 1)) { // found pair so set second element of array to the pair char and return
				tripleThenPair[1] = values.get(i);
				return tripleThenPair;
			}
		}
		return tripleThenPair;		
	}
	
	public char[] isFlush() {
		char[] flush = {'X', 'X', 'X', 'X', 'X'};
		for(int i = 0; i < suits.size() - 4; i++) {
			if(suits.get(i) == suits.get(i + 1) && suits.get(i) == suits.get(i + 2) 
			&& suits.get(i)  == suits.get(i + 3) && suits.get(i) == suits.get(i + 4)) {
				flush[4] = suits.get(i); // start from the end of flush so the highest cards by value come first for tiebreak purposes
				flush[3] = suits.get(i + 1);
				flush[2] = suits.get(i + 2);
				flush[1] = suits.get(i + 3);
				flush[0] = suits.get(i + 4);
				return flush;
			}
		}
		return flush;
	}
	
	public char isStraight() {
		if(values.get(0) == '2' && values.get(6) == '>' && values.get(1) == '3' && values.get(2) == '4' && values.get(3) == '5')
			return '5';
		
		for(int i = 0; i < values.size() - 4; i++) {
			if(values.get(i) - values.get(i + 1) == -1 // consecutive numbers
					&& values.get(i + 1) - values.get(i + 2) == -1
					&& values.get(i + 2) - values.get(i + 3) == -1 
					&& values.get(i + 3) - values.get(i + 4) == -1)
				return values.get(i + 4); // return highest for tiebreaking purposes
		}
		return 'X';
	}
	
	public char isThreeOfaKind() {
		for(int i = 0; i < values.size() - 2; i++)
			if(values.get(i) == values.get(i + 1) && values.get(i) == values.get(i + 2))
				return values.get(i);
		return 'X';
	}
	
	public char[] isTwoPair() {
		char[] pairs = {'X','X'};
		for(int i = 0; i < values.size() - 1; i++) {
			if(values.get(i) == values.get(i + 1) && pairs[0] == 'X')
				pairs[0] = values.get(i);
			if(values.get(i) == values.get(i + 1) && pairs[0] != values.get(i)) {
				pairs[1] = values.get(i);
			}
		}
		return pairs;
	}
	
	public char isOnePair() {
		for(int i = 0; i < values.size() - 1; i++) {
				if(values.get(i) == values.get(i + 1))
					return values.get(i);
		}
		return 'X';
}
	public ArrayList<Character> getValues() {
		return values;
	}

	


	
}

