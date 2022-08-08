package com.example.mypokerassistant.PokerStats;

import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerTable;

import java.util.ArrayList;

/**
 * Contains methods to determine the winner between two given poker hands
 * @author tobyf
 *
 */
public class CompareTwoHands {

	
	
	private CompareTwoHands() {}
	
	public static int determineWinner(PokerTable table, PokerHand hand1, PokerHand hand2) { // 1 means hand1 won 2 means hand2 won 0 means tie
		AnalyzeHand analyzeHand1 = new AnalyzeHand(hand1, table);
		AnalyzeHand analyzeHand2 = new AnalyzeHand(hand2, table);
		int hand1Priority = analyzeHand1.getHandPriority();
		int hand2Priority = analyzeHand2.getHandPriority();
		if(hand1Priority < hand2Priority)
			return 1;
		if(hand1Priority > hand2Priority)
			return 2;
		else {// tie so do tieBreaker depending on the priority
			switch (hand1Priority) {
				case 1 : {
					int tieBreaker = straightFlushTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
				case 2 : {
					int tieBreaker = fourOfaKindTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
				case 3 : {
					int tieBreaker = fullHouseTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
				case 4 : {
					int tieBreaker = flushTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
				case 5 : {
					int tieBreaker = straightTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
				case 6 : {
					int tieBreaker = threeOfaKindTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
				case 7 : {
					int tieBreaker = twoPairTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
				case 8 : {
					int tieBreaker = onePairTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
				case 9 : {
					int tieBreaker = highCardTieBreaker(analyzeHand1, analyzeHand2);
					if(tieBreaker == 0) return 0;
					return tieBreaker == 1 ? 1 : 2;
				}
			}
		}
		return 0;
	}
	
//	Tie Break Methods
//	Highest Return int Wins the Hand
	public static int straightFlushTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		char hand1Highest = analyzeHand1.isStraightFlush();
		char hand2Highest = analyzeHand2.isStraightFlush();
		if(hand1Highest == hand2Highest) return 0;
		return hand1Highest > hand2Highest ? 1 : 2;
	}
	public static int fourOfaKindTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		char hand1Quads = analyzeHand1.isFourofaKind();
		char hand2Quads = analyzeHand2.isFourofaKind();
		return hand1Quads > hand2Quads ? 1 : 2;
	}
	public static int fullHouseTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		char[] hand1TripleAndPair = analyzeHand1.isFullHouse();
		char[] hand2TripleAndPair = analyzeHand2.isFullHouse();
		if(hand1TripleAndPair[0] == hand2TripleAndPair[0]) { //Same triple so look at the pairs
			if(hand1TripleAndPair[1] == hand2TripleAndPair[1]) return 0; //Same pair too so tie
			return hand1TripleAndPair[1] > hand2TripleAndPair[1] ? 1 : 2; //Return higher pair
		}
		return hand1TripleAndPair[0] > hand2TripleAndPair[0] ? 1 : 2; // Return higher triple
	}
	public static int flushTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) { //NEEDS WORK IF HIGHEST CARD IS SAME NOT NECESSARILY A TIE
		char[] hand1Flush = analyzeHand1.isFlush();
		char[] hand2Flush = analyzeHand2.isFlush();
		for(int i = 0; i < 5; i++) {
			if(hand1Flush[i] != hand2Flush[i])
				return hand1Flush[i] > hand2Flush[i] ? 1 : 2;
		}
		return 0;
	}
	public static int straightTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		char hand1Highest = analyzeHand1.isStraight();
		char hand2Highest = analyzeHand2.isStraight();
		if(hand1Highest == hand2Highest) return 0;
		return hand1Highest > hand2Highest ? 1 : 2;
	}
	public static int threeOfaKindTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		char hand1Triple = analyzeHand1.isThreeOfaKind();
		char hand2Triple = analyzeHand2.isThreeOfaKind();
		return hand1Triple > hand2Triple ? 1 : 2;
	}
	public static int twoPairTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		char[] hand1Pairs = analyzeHand1.isTwoPair();
		char[] hand2Pairs = analyzeHand2.isTwoPair();
		if(hand1Pairs[1] == hand2Pairs[1]) { // if top pair is same, look at bottom pair
			if(hand1Pairs[0] == hand2Pairs[0]) {
				char hand1HighCard = twoPairHighCard(hand1Pairs, analyzeHand1, analyzeHand2);
				char hand2HighCard = twoPairHighCard(hand2Pairs, analyzeHand1, analyzeHand2);
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
	private static char twoPairHighCard(char[] pairs, AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		ArrayList<Character> values = analyzeHand1.getValues();
		int highPairIndex = values.indexOf(pairs[1]);
		int lowPairIndex = values.indexOf(pairs[0]);
		
		if(highPairIndex == 5)
			if(lowPairIndex == 3)
				return values.get(2);
			else
				return values.get(4);
		return values.get(6);
	}
	
	public static int onePairTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		if(analyzeHand1.isOnePair() == analyzeHand2.isOnePair()) {
			char pair = analyzeHand1.isOnePair();
			int pairInHand1Index = analyzeHand1.getValues().indexOf(pair);
			int pairInHand2Index = analyzeHand1.getValues().indexOf(pair);
				char[] top3Hand1 = onePairTop3(pairInHand1Index, analyzeHand1);
				char[] top3Hand2 = onePairTop3(pairInHand2Index, analyzeHand2);
				for(int i = 0; i < 3; i++) {
					if(top3Hand1[i] != top3Hand2[i])
						return top3Hand1[i] > top3Hand2[i] ? 1 : 2;
				}
				return 0;
			}
		return analyzeHand1.isOnePair() > analyzeHand2.isOnePair() ? 1 : 2;
	}
	
	private static char[] onePairTop3(int pairIndex, AnalyzeHand hand) {
		char[] top3 = new char[3];
			if(pairIndex >= 0 && pairIndex <= 2) {
				top3[0] = hand.getValues().get(6);
				top3[1] = hand.getValues().get(5);
				top3[2] = hand.getValues().get(4);
			}
			if(pairIndex == 3) {
				top3[0] = hand.getValues().get(6);
				top3[1] = hand.getValues().get(5);
				top3[2] = hand.getValues().get(2);
			}
			if(pairIndex == 4) {
				top3[0] = hand.getValues().get(6);
				top3[1] = hand.getValues().get(3);
				top3[2] = hand.getValues().get(2);
			}
			if(pairIndex == 5) {
				top3[0] = hand.getValues().get(4);
				top3[1] = hand.getValues().get(3);
				top3[2] = hand.getValues().get(2);
			}
		return top3;
			
	}
	
	public static int highCardTieBreaker(AnalyzeHand analyzeHand1, AnalyzeHand analyzeHand2) {
		char highCardHand1 = analyzeHand1.getValues().get(6);
		char highCardHand2 = analyzeHand2.getValues().get(6);
		if(highCardHand1 == highCardHand2)
			return 0;
		return highCardHand1 > highCardHand2 ? 1 : 2;
	}
}
