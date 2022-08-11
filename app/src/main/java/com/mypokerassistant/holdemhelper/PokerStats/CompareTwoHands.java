package com.mypokerassistant.holdemhelper.PokerStats;

import com.mypokerassistant.holdemhelper.PokerParts.PokerHand;
import com.mypokerassistant.holdemhelper.PokerParts.PokerTable;

import java.util.ArrayList;

/**
 * Contains methods to determine the winner between two given poker hands
 * @author FrickTob
 *
 */
public class CompareTwoHands {

	private CompareTwoHands() {}
	
	public static int determineWinner(PokerTable table, PokerHand hand1, PokerHand hand2) { // 1 means hand1 won 2 means hand2 won 0 means tie
		OneHandAnalysis oneHandAnalysis1 = new OneHandAnalysis(hand1, table);
		OneHandAnalysis oneHandAnalysis2 = new OneHandAnalysis(hand2, table);
		int hand1Priority = oneHandAnalysis1.getHandPriority();
		int hand2Priority = oneHandAnalysis2.getHandPriority();

		if(hand1Priority < hand2Priority) return 1;
		if(hand1Priority > hand2Priority) return 2;
		else {// tie so do tieBreaker depending on the priority
			int tieBreaker;
			switch (hand1Priority) {
				case 1 :
					tieBreaker = straightFlushTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				case 2 :
					tieBreaker = fourOfaKindTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				case 3 :
					tieBreaker = fullHouseTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				case 4 :
					tieBreaker = flushTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				case 5 :
					tieBreaker = straightTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				case 6 :
					tieBreaker = threeOfaKindTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				case 7 :
					tieBreaker = twoPairTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				case 8 :
					tieBreaker = onePairTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				case 9 :
					tieBreaker = highCardTieBreaker(oneHandAnalysis1, oneHandAnalysis2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
			}
		}
		return 0;
	}
	
//	Tie Break Methods
//	Highest Return int Wins the Hand
	public static int straightFlushTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) {
		char hand1Highest = oneHandAnalysis1.isStraightFlush();
		char hand2Highest = oneHandAnalysis2.isStraightFlush();
		if(hand1Highest == hand2Highest) return 0;
		return hand1Highest > hand2Highest ? 1 : 2;
	}
	public static int fourOfaKindTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) {
		char hand1Quads = oneHandAnalysis1.isFourofaKind();
		char hand2Quads = oneHandAnalysis2.isFourofaKind();
		return hand1Quads > hand2Quads ? 1 : 2;
	}
	public static int fullHouseTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) {
		char[] hand1TripleAndPair = oneHandAnalysis1.isFullHouse();
		char[] hand2TripleAndPair = oneHandAnalysis2.isFullHouse();
		if(hand1TripleAndPair[0] == hand2TripleAndPair[0]) { //Same triple so look at the pairs
			if(hand1TripleAndPair[1] == hand2TripleAndPair[1]) return 0; //Same pair too so tie
			return hand1TripleAndPair[1] > hand2TripleAndPair[1] ? 1 : 2; //Return higher pair
		}
		return hand1TripleAndPair[0] > hand2TripleAndPair[0] ? 1 : 2; // Return higher triple
	}
	public static int flushTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) { //NEEDS WORK IF HIGHEST CARD IS SAME NOT NECESSARILY A TIE
		char[] hand1Flush = oneHandAnalysis1.isFlush();
		char[] hand2Flush = oneHandAnalysis2.isFlush();
		for(int i = 0; i < 5; i++) {
			if(hand1Flush[i] != hand2Flush[i])
				return hand1Flush[i] > hand2Flush[i] ? 1 : 2;
		}
		return 0;
	}
	public static int straightTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) {
		char hand1Highest = oneHandAnalysis1.isStraight();
		char hand2Highest = oneHandAnalysis2.isStraight();
		if(hand1Highest == hand2Highest) return 0;
		return hand1Highest > hand2Highest ? 1 : 2;
	}
	public static int threeOfaKindTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) {
		char hand1Triple = oneHandAnalysis1.isThreeOfaKind();
		char hand2Triple = oneHandAnalysis2.isThreeOfaKind();
		return hand1Triple > hand2Triple ? 1 : 2;
	}
	public static int twoPairTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) {
		char[] hand1Pairs = oneHandAnalysis1.isTwoPair();
		char[] hand2Pairs = oneHandAnalysis2.isTwoPair();
		if(hand1Pairs[1] == hand2Pairs[1]) { // if top pair is same, look at bottom pair
			if(hand1Pairs[0] == hand2Pairs[0]) { // if bottom pair is same, look at high card
				char hand1HighCard = twoPairHighCard(hand1Pairs, oneHandAnalysis1);
				char hand2HighCard = twoPairHighCard(hand2Pairs, oneHandAnalysis2);
				if(hand1HighCard == hand2HighCard)
					return 0;
				return hand1HighCard > hand2HighCard ? 1 : 2;
			}
			else
				return hand1Pairs[0] > hand2Pairs[0] ? 1 : 2;	
		}
		else
			return hand1Pairs[1] > hand2Pairs[1] ? 1 : 2;
	}
	
	//
	private static char twoPairHighCard(char[] pairs, OneHandAnalysis oneHandAnalysis) {
		String[] cardArray = oneHandAnalysis.getCardArray();
		for(int i = cardArray.length - 1; i >= 0; i--)
			if(cardArray[i].length() == 1)  // highest single
				return OneHandAnalysis.cardArrayIndexToValue(i);

		return 'X'; // should never be reached
	}
	
	public static int onePairTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) {
		if(oneHandAnalysis1.isOnePair() == oneHandAnalysis2.isOnePair()) {
			char pair = oneHandAnalysis1.isOnePair();
				char[] top3Hand1 = onePairTop3(oneHandAnalysis1);
				char[] top3Hand2 = onePairTop3(oneHandAnalysis2);
				for(int i = 0; i < 3; i++) {
					if(top3Hand1[i] != top3Hand2[i])
						return top3Hand1[i] > top3Hand2[i] ? 1 : 2;
				}
				return 0;
			}
		return oneHandAnalysis1.isOnePair() > oneHandAnalysis2.isOnePair() ? 1 : 2;
	}
	
	private static char[] onePairTop3(OneHandAnalysis hand) {
		char[] top3 = new char[3];
		int top3Index = 0;
		String[] cardArray = hand.getCardArray();
		for(int i = cardArray.length - 1; i >= 0; i--) {
			if(cardArray[i].length() == 1) {
				top3[top3Index] = OneHandAnalysis.cardArrayIndexToValue(i);
				top3Index++;
			}
			if(top3Index == 3)
				return top3;
		}
		return top3;
	}
	
	public static int highCardTieBreaker(OneHandAnalysis oneHandAnalysis1, OneHandAnalysis oneHandAnalysis2) {
		String[] cardArray1 = oneHandAnalysis1.getCardArray();
		String[] cardArray2 = oneHandAnalysis2.getCardArray();
		char highCardHand1 = 'X';
		char highCardHand2 = 'X';
		for(int i = cardArray1.length - 1; i >= 0; i--) {
			if(cardArray1[i].length() == 1) {
				highCardHand1 = OneHandAnalysis.cardArrayIndexToValue(i);
				break;
			}
		}
		for(int i = cardArray2.length - 1; i >= 0; i--) {
			if(cardArray2[i].length() == 1) {
				highCardHand2 = OneHandAnalysis.cardArrayIndexToValue(i);
				break;
			}
		}
		if(highCardHand1 == highCardHand2)
			return 0;
		return highCardHand1 > highCardHand2 ? 1 : 2;
	}
}
