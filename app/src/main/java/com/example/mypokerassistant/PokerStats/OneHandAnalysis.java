package com.example.mypokerassistant.PokerStats;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerTable;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Contains methods to analyze a poker hand so that it may be compared to other hands
 * to determine which hand is stronger.
 * @author tobyf
 */
public class OneHandAnalysis {

	private String handAndTable;
	private int handPriority; // ranking of hand
	private ArrayList<Character[]> cards;
	private ArrayList<Character> values;

	@SuppressLint("NewApi")
	public OneHandAnalysis(@NonNull PokerHand hand, @NonNull PokerTable table) {
		handAndTable = "" + hand.getCard1().getCard() + hand.getCard2().getCard()
				+ table.getFlop1().getCard() + table.getFlop2().getCard() + table.getFlop3().getCard()
				+ table.getTurn().getCard() + table.getRiver().getCard();
		cards = new ArrayList<>();
		int j = 1;
		for(int i = 0; i < 13; i += 2) {
			Character[] currCard = {handAndTable.charAt(i), handAndTable.charAt(j)};
			cards.add(currCard);
			j+= 2;
		}
		convertCardsToAscendingASCII();
		cards.sort(new Comparator<Character[]>() {
			@Override
			public int compare(Character[] characters, Character[] t1) {
				if(t1[0] == characters[0]) return 0;
				return t1[0] < characters[0] ? 1 : -1;
			}
		});

		values = new ArrayList<>();
		for(int i = 0; i < 7; i++)
			values.add(cards.get(i)[0]);

		handPriority = determineHandStrength();
	}

	private void convertCardsToAscendingASCII() {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i)[0] == '0') {
				cards.get(i)[0] = ':';
			}
			else if(cards.get(i)[0] == 'J') {
				cards.get(i)[0] = ';';
			}
			else if(cards.get(i)[0] == 'Q') {
				cards.get(i)[0] = '<';
			}
			else if(cards.get(i)[0] == 'K') {
				cards.get(i)[0] = '=';
			}
			else if(cards.get(i)[0] == 'A') {
				cards.get(i)[0] = '>';
			}
		}
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

		// Create an 13 element array with a string for each value
		// Populate strings with suits of cards of the corresponding value
		String[] values = tallyValuesForStraightFlush();

		// Search through array
		for(int i = values.length - 1; i >= 4; i--) {
			// Find straight
			if (!values[i].equals("")
					&& !values[i-1].equals("")
					&& !values[i-2].equals("")
					&& !values[i-3].equals("")
					&& !values[i-4].equals("")) {

				//Find suit of flush
				char flushSuit;
				if(values[i].length() == 1) { flushSuit = values[i].charAt(0);}
				else if (values[i-1].length() == 1) { flushSuit = values[i-1].charAt(0);}
				else if (values[i-2].length() == 1) { flushSuit = values[i-2].charAt(0);}
				else if (values[i-3].length() == 1) { flushSuit = values[i-3].charAt(0);}
				else { flushSuit = values[i-4].charAt(0);}

				// See if the straight is also a flush
				if(values[i].contains("" + flushSuit)
						&& values[i - 1].contains("" + flushSuit)
						&& values[i-2].contains("" + flushSuit)
						&& values[i-3].contains("" +flushSuit)
						&& values[i-4].contains("" + flushSuit))
					return (char)(i + 50);
			}
		}
		// Check A-5 straight flush
		if(!values[12].equals("") // A
				&& !values[0].equals("") // 2
				&& !values[1].equals("") // 3
				&& !values[2].equals("") // 4
				&& !values[3].equals("")) {// 5
			char flushSuit;
			if(values[12].length() == 1) { flushSuit = values[12].charAt(0);}
			else if (values[0].length() == 1) { flushSuit = values[0].charAt(0);}
			else if (values[1].length() == 1) { flushSuit = values[1].charAt(0);}
			else if (values[2].length() == 1) { flushSuit = values[2].charAt(0);}
			else { flushSuit = values[3].charAt(0);}

			if(values[12].contains("" + flushSuit)
					&& values[0].contains("" + flushSuit)
					&& values[1].contains("" + flushSuit)
					&& values[2].contains("" +flushSuit)
					&& values[3].contains("" + flushSuit))
				return '5';
		}
		return 'X';

	}
	
	public char isFourofaKind() {
		for(int i = 0; i < cards.size() - 3; i++)
			if(cards.get(i)[0] == cards.get(i + 1)[0] && cards.get(i)[0] == cards.get(i + 2)[0]
			&& cards.get(i)[0] == cards.get(i + 3)[0])
				return cards.get(i)[0];
		return 'X';
	}
	
	public char[] isFullHouse() {
		char[] tripleThenPair = {'X', 'X'};
		char triple = isThreeOfaKind();
		if(triple == 'X') return tripleThenPair; //no triple so no full house
		tripleThenPair[0] = triple; // there is a triple so set first element to the triple char and look for a pair
		
		for(int i = 0; i < cards.size() - 2; i++) {
			if(cards.get(i)[0] == cards.get(i + 1)[0]) { // found pair so check to see if it's the triple found above
				if(cards.get(i)[0] == triple) // it is the triple, so skip past it
					i += 2;
				else
					tripleThenPair[1] = cards.get(i)[0]; // it is not the triple, so store and return full house
			}
		}
		return tripleThenPair;
	}
	
	public char[] isFlush() {
		char[] flush = {'X', 'X', 'X', 'X', 'X'}; // Default output if no flush is found
		int[] suitNums = {0, 0, 0, 0}; // numClubs , numDiamonds, numHearts, numSpades

		// Tally suits to to find flush
		for(int i = 0; i < cards.size(); i++) {
			switch (cards.get(i)[1]) {
				case 'C':
					suitNums[0]++;
					break;
				case 'D':
					suitNums[1]++;
					break;
				case 'H':
					suitNums[2]++;
					break;
				case 'S':
					suitNums[3]++;
					break;
			}
		}

		// Find suit of flush if any
		char flushSuit;
		if (suitNums[0] >= 5)       flushSuit = 'C';
		else if (suitNums[1] >= 5)  flushSuit = 'D';
		else if (suitNums[2] >= 5)  flushSuit = 'H';
		else if (suitNums[3] >= 5)  flushSuit = 'S';
		else                        flushSuit = 'X';

		// Return if no flush
		if(flushSuit == 'X')
			return flush;

		// Flush detected, so collect values of cards and return them for tie breaking
		int flushIndex = 0;
		for(int i = cards.size() - 1; i >= 0; i--) {
			if(cards.get(i)[1] == flushSuit) {
				flush[flushIndex] = cards.get(i)[0];
				flushIndex++;
				if(flushIndex >= 5) break;
			}
		}
		return flush;
	}
	
	public char isStraight() {
		// Create 13 element int array for each value
		// Tally number of times the corresponding value appears in the given hand
		int[] values = tallyValuesForStraight();

		for(int i = values.length - 1; i >= 4; i--) {
			if (values[i] >= 1
					&& values[i-1] >= 1
					&& values[i-2] >= 1
					&& values[i-3] >= 1
					&& values[i-4] >= 1) // five in a row
				return (char)(i + 50);
		}
		if(values[12] >= 1
				&& values[0] >= 1
				&& values[1] >= 1
				&& values[2] >= 1
				&& values[3] >= 1) // A-5 straight
			return '5';
		return 'X';
	}
	
	public char isThreeOfaKind() {
		for(int i = 0; i < cards.size() - 2; i++)
			if(cards.get(i)[0] == cards.get(i + 1)[0] && cards.get(i)[0] == cards.get(i + 2)[0])
				return cards.get(i)[0];
		return 'X';
	}
	
	public char[] isTwoPair() {
		char[] pairs = {'X','X'};
		for(int i = 0; i < cards.size() - 1; i++) {
			if(cards.get(i)[0] == cards.get(i + 1)[0] && pairs[0] == 'X')
				pairs[0] = cards.get(i)[0];
			if(cards.get(i)[0] == cards.get(i + 1)[0] && pairs[0] != cards.get(i)[0]) {
				pairs[1] = cards.get(i)[0];
			}
		}
		return pairs;
	}
	
	public char isOnePair() {
		for(int i = 0; i < cards.size() - 1; i++) {
				if(cards.get(i)[0] == cards.get(i + 1)[0])
					return cards.get(i)[0];
		}
		return 'X';
}

	// Getter methods
	public ArrayList<Character[]> getCards() {
		return cards;
	}

	public ArrayList<Character> getValues() {
		return values;
	}

	public int getHandPriority() {
		return handPriority;
	}

	// Tally methods for isStraightFlush() and isStraight() methods
	public String[] tallyValuesForStraightFlush() {
		String[] values = {"","","","","","","","","","","","",""};
		for(int i = 0; i < cards.size(); i++) {
			switch (cards.get(i)[0]) { // add suits of each value to empty string
				case '2':
					values[0]+= cards.get(i)[1];
					break;
				case '3':
					values[1]+= cards.get(i)[1];
					break;
				case '4':
					values[2]+= cards.get(i)[1];
					break;
				case '5':
					values[3]+= cards.get(i)[1];
					break;
				case '6':
					values[4]+= cards.get(i)[1];
					break;
				case '7':
					values[5]+= cards.get(i)[1];
					break;
				case '8':
					values[6]+= cards.get(i)[1];
					break;
				case '9':
					values[7]+= cards.get(i)[1];
					break;
				case ':':
					values[8]+= cards.get(i)[1];
					break;
				case ';':
					values[9]+= cards.get(i)[1];
					break;
				case '<':
					values[10]+= cards.get(i)[1];
					break;
				case '=':
					values[11]+= cards.get(i)[1];
					break;
				case '>':
					values[12]+= cards.get(i)[1];
					break;
			}
		}
		return values;
	}

	public int[] tallyValuesForStraight() {
		int[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int i = 0; i < cards.size(); i++) {
			switch (cards.get(i)[0]) { // grab value and tally values array to look for straights
				case '2':
					values[0]++;
					break;
				case '3':
					values[1]++;
					break;
				case '4':
					values[2]++;
					break;
				case '5':
					values[3]++;
					break;
				case '6':
					values[4]++;
					break;
				case '7':
					values[5]++;
					break;
				case '8':
					values[6]++;
					break;
				case '9':
					values[7]++;
					break;
				case ':':
					values[8]++;
					break;
				case ';':
					values[9]++;
					break;
				case '<':
					values[10]++;
					break;
				case '=':
					values[11]++;
					break;
				case '>':
					values[12]++;
					break;
			}
		}
		return values;
	}

}

