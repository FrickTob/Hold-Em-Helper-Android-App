package com.example.mypokerassistant.PokerStats;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerTable;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Contains methods to analyze a hand so that it may be compared to other hands
 * to determine which hand is stronger.
 * @author tobyf
 */
public class AnalyzeHand {

	private String handAndTable;
	private int handPriority; // ranking of hand
	private ArrayList<Character[]> cards;
	private ArrayList<Character> values;
	
	@SuppressLint("NewApi")
	public AnalyzeHand(@NonNull PokerHand hand, @NonNull PokerTable table) {
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
		char highestValInStraight = isStraight();
		if(isStraight() == 'X') return 'X';  // not straight so can't be straight flush
		if(isFlush()[0] == 'X') return 'X'; // not flush so can't be straight flush

		// Check for straight flush
		for(int i = 6; i >= 4; i--) { // start at end to ensure highest straight flush
			char flushSuit = cards.get(i)[1];
			if(cards.get(i)[0] == cards.get(i - 1)[0] + 1 && cards.get(i - 1)[1] == flushSuit
					&& cards.get(i - 1)[0] == cards.get(i - 2)[0] + 1 && cards.get(i - 2)[1] == flushSuit
					&& cards.get(i - 2)[0] == cards.get(i - 3)[0] + 1 && cards.get(i - 3)[1] == flushSuit
					&& cards.get(i - 3)[0] == cards.get(i - 4)[0] + 1 && cards.get(i - 4)[1] == flushSuit) // check consecutive and same suit
				return cards.get(i)[0]; // return highest value for tie breaking

			// Check for A-5 straight flush
			if(cards.get(i)[0] == '>') {
				int Athru5cardsFound = 1;
				for(int j = 0; j < 7; j++) { // scan array for rest of A-5 straight flush
					if(cards.get(j)[0] == '2' && cards.get(j)[1] == flushSuit)
						Athru5cardsFound++; // found the 2
					if(cards.get(j)[0] == '3' && cards.get(j)[1] == flushSuit)
						Athru5cardsFound++; // found the 3
					if(cards.get(j)[0] == '4' && cards.get(j)[1] == flushSuit)
						Athru5cardsFound++; // found the 4
					if(cards.get(j)[0] == '5' && cards.get(j)[1] == flushSuit)
						Athru5cardsFound++; // found the 5
				}
				if(Athru5cardsFound == 5)
					return '5'; // return 5 if A-5 straight flush is found
			}
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

		for(int i = 0; i < cards.size() - 4; i++) {
			if(cards.get(i)[0] - cards.get(i + 1)[0] == -1 // consecutive numbers
					&& cards.get(i + 1)[0] - cards.get(i + 2)[0] == -1
					&& cards.get(i + 2)[0] - cards.get(i + 3)[0] == -1
					&& cards.get(i + 3)[0] - cards.get(i + 4)[0] == -1)
				return cards.get(i + 4)[0]; // return highest for tiebreaking purposes
		}
		if(cards.get(0)[0] == '2'
				&& cards.get(6)[0] == '>'
				&& cards.get(1)[0] == '3'
				&& cards.get(2)[0] == '4'
				&& cards.get(3)[0] == '5') //a-5 straight check
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

	// Getter Methods
	public ArrayList<Character[]> getCards() {
		return cards;
	}

	public ArrayList<Character> getValues() {
		return values;
	}

	public int getHandPriority() {
		return handPriority;
	}
	
}

