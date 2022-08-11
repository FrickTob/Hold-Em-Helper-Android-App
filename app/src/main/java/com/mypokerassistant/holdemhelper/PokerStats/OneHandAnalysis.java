package com.mypokerassistant.holdemhelper.PokerStats;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.mypokerassistant.holdemhelper.PokerParts.PokerHand;
import com.mypokerassistant.holdemhelper.PokerParts.PokerTable;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Contains methods to analyze a poker hand so that it may be compared to other hands
 * to determine which hand is stronger.
 * @author FrickTob
 */
public class OneHandAnalysis {

	private String handAndTable;
	private int handPriority; // ranking of hand
	private String[] cardArray;

	@SuppressLint("NewApi")
	public OneHandAnalysis(@NonNull PokerHand hand, @NonNull PokerTable table) {
		handAndTable = "" + hand.getCard1().getCard() + hand.getCard2().getCard()
				+ table.getFlop1().getCard() + table.getFlop2().getCard() + table.getFlop3().getCard()
				+ table.getTurn().getCard() + table.getRiver().getCard();



		cardArray = new String[13];
		for(int i = 0; i < 13; i++) {
			cardArray[i] = "";
		}
		cardArray = tallyCardArray();
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


	//	Hand Type Determiner Methods
	public char isStraightFlush() {
		if(isStraight() == 'X') return 'X';  // not straight so can't be straight flush
		if(isFlush()[0] == 'X') return 'X'; // not flush so can't be straight flush

		// Search through array
		for(int i = cardArray.length - 1; i >= 4; i--) {
			// Find straight
			if (!cardArray[i].equals("")
					&& !cardArray[i-1].equals("")
					&& !cardArray[i-2].equals("")
					&& !cardArray[i-3].equals("")
					&& !cardArray[i-4].equals("")) {

				//Find suit of flush
				char flushSuit;
				if(cardArray[i].length() == 1) { flushSuit = cardArray[i].charAt(0);}
				else if (cardArray[i-1].length() == 1) { flushSuit = cardArray[i-1].charAt(0);}
				else if (cardArray[i-2].length() == 1) { flushSuit = cardArray[i-2].charAt(0);}
				else if (cardArray[i-3].length() == 1) { flushSuit = cardArray[i-3].charAt(0);}
				else { flushSuit = cardArray[i-4].charAt(0);}

				// See if the straight is also a flush
				if(cardArray[i].contains("" + flushSuit)
						&& cardArray[i - 1].contains("" + flushSuit)
						&& cardArray[i-2].contains("" + flushSuit)
						&& cardArray[i-3].contains("" +flushSuit)
						&& cardArray[i-4].contains("" + flushSuit))
					return cardArrayIndexToValue(i);
			}
		}
		// Check A-5 straight flush
		if(!cardArray[12].equals("") // A
				&& !cardArray[0].equals("") // 2
				&& !cardArray[1].equals("") // 3
				&& !cardArray[2].equals("") // 4
				&& !cardArray[3].equals("")) {// 5
			char flushSuit;
			if(cardArray[12].length() == 1) { flushSuit = cardArray[12].charAt(0);}
			else if (cardArray[0].length() == 1) { flushSuit = cardArray[0].charAt(0);}
			else if (cardArray[1].length() == 1) { flushSuit = cardArray[1].charAt(0);}
			else if (cardArray[2].length() == 1) { flushSuit = cardArray[2].charAt(0);}
			else { flushSuit = cardArray[3].charAt(0);}

			if(cardArray[12].contains("" + flushSuit)
					&& cardArray[0].contains("" + flushSuit)
					&& cardArray[1].contains("" + flushSuit)
					&& cardArray[2].contains("" +flushSuit)
					&& cardArray[3].contains("" + flushSuit))
				return '5';
		}
		return 'X';

	}
	
	public char isFourofaKind() {
		for(int i = cardArray.length - 1; i >= 0; i--) {
			if(cardArray[i].length() >= 4) {
				return cardArrayIndexToValue(i); // convert array index into corresponding character
			}
		}
		return 'X';
	}
	
	public char[] isFullHouse() {
		char[] tripleThenPair = {'X', 'X'};
		char triple = isThreeOfaKind();
		if(triple == 'X') return tripleThenPair; //no triple so no full house
		tripleThenPair[0] = triple; // there is a triple so set first element to the triple char and look for a pair
		
		for(int i = cardArray.length - 1; i >= 0; i--) {
			if(cardArray[i].length() >= 2) { // Found pair so check if it's the triple from above
				if(cardArrayIndexToValue(i) != triple) {
					tripleThenPair[1] = cardArrayIndexToValue(i);
				}
			}
		}
		return tripleThenPair;
	}
	
	public char[] isFlush() {
		char[] flush = {'X', 'X', 'X', 'X', 'X'}; // Default output if no flush is found
		int[] suitNums = {0, 0, 0, 0}; // numClubs , numDiamonds, numHearts, numSpades
		for(String s : cardArray) {
			if(s.contains("" + 'C')) suitNums[0]++;
			if(s.contains("" + 'D')) suitNums[1]++;
			if(s.contains("" + 'H')) suitNums[2]++;
			if(s.contains("" + 'S')) suitNums[3]++;
		}
		char flushSuit = 'X';
		for(int i = 0; i < 4; i++) {
			if(suitNums[i] >= 5) { // found flush
				if(i == 0) flushSuit ='C';
				else if(i == 1) flushSuit ='D';
				else if(i == 2) flushSuit ='H';
				else if(i == 3) flushSuit ='S';
			}
		}
		if(flushSuit == 'X') return flush; // no flush so return

		// flush is found so collect highest values for tie breaking
		int flushIndex = 0;
		for(int i = cardArray.length - 1; i >= 0; i--) {
			if(cardArray[i].contains("" + flushSuit)) {
				flush[flushIndex] = cardArrayIndexToValue(i);
				flushIndex++;
				if(flushIndex >= 5) break;
			}
		}
		return flush;
	}
	
	public char isStraight() {
		for(int i = cardArray.length - 1; i >= 4; i--) {
			// Find straight
			if (!cardArray[i].equals("")
					&& !cardArray[i - 1].equals("")
					&& !cardArray[i - 2].equals("")
					&& !cardArray[i - 3].equals("")
					&& !cardArray[i - 4].equals("")) {
				return cardArrayIndexToValue(i);
			}
		}
		if(!cardArray[12].equals("")
				&& !cardArray[0].equals("")
				&& !cardArray[1].equals("")
				&& !cardArray[2].equals("")
				&& !cardArray[3].equals("")) // A-5 straight
			return '5';
		return 'X';
	}
	
	public char isThreeOfaKind() {
		for(int i = cardArray.length - 1; i >= 0; i--) {
			if(cardArray[i].length() >= 3)
				return cardArrayIndexToValue(i);
		}
		return 'X';
	}
	
	public char[] isTwoPair() {
		char[] pairs = {'X','X'};
		for(int i = cardArray.length - 1; i >= 0; i--) {
			if(cardArray[i].length() >= 2) {
				if(pairs[0] == 'X')
					pairs[0] = cardArrayIndexToValue(i);
				else
					pairs[1] = cardArrayIndexToValue(i);
				if(pairs[1] != 'X') // return if both pairs have been found
					return pairs;
			}
		}
		return pairs; // two pairs were not found
	}
	
	public char isOnePair() {
		for(int i = cardArray.length - 1; i >= 0; i--) {
			if(cardArray[i].length() >= 2) {return cardArrayIndexToValue(i);} // return highest pair
		}
			return 'X';
}


	public int getHandPriority() {
		return handPriority;
	}

	// Tally methods for isStraightFlush() and isStraight() methods
	public String[] tallyCardArray() {
		for(int i = 0; i < handAndTable.length() - 1; i+= 2) {
			switch (handAndTable.charAt(i)) {
				case '2':
					cardArray[0]+= handAndTable.charAt(i + 1);
					break;
				case '3':
					cardArray[1]+= handAndTable.charAt(i + 1);
					break;
				case '4':
					cardArray[2]+= handAndTable.charAt(i + 1);
					break;
				case '5':
					cardArray[3]+= handAndTable.charAt(i + 1);
					break;
				case '6':
					cardArray[4]+= handAndTable.charAt(i + 1);
					break;
				case '7':
					cardArray[5]+= handAndTable.charAt(i + 1);
					break;
				case '8':
					cardArray[6]+= handAndTable.charAt(i + 1);
					break;
				case '9':
					cardArray[7]+= handAndTable.charAt(i + 1);
					break;
				case '0':
					cardArray[8]+= handAndTable.charAt(i + 1);
					break;
				case 'J':
					cardArray[9]+= handAndTable.charAt(i + 1);
					break;
				case 'Q':
					cardArray[10]+= handAndTable.charAt(i + 1);
					break;
				case 'K':
					cardArray[11]+= handAndTable.charAt(i + 1);
					break;
				case 'A':
					cardArray[12]+= handAndTable.charAt(i + 1);
					break;
			}
		}
		return cardArray;
	}

	public static char cardArrayIndexToValue(int i) {
		return (char)(i + 50);
	}

	public static int valueToCardArrayIndex(char val) {
		return val - 50;
	}

	public String[] getCardArray() {
		return cardArray;
	}


}

