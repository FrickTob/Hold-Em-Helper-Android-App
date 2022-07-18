package com.example.mypokerassistant.PokerStats;

import com.example.mypokerassistant.PokerParts.AllCards;
import com.example.mypokerassistant.PokerParts.PokerCard;
import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerTable;

import java.util.ArrayList;

public class Stats2Player {

	//Formatting must be checked before function call
	public float getOdds(String cardString) {

		PokerCard handCard1 = new PokerCard(cardString.charAt(0), cardString.charAt(1));
		PokerCard handCard2 = new PokerCard(cardString.charAt(2), cardString.charAt(3));
		PokerHand hand = new PokerHand(handCard1, handCard2);
		PokerCard flop1 = new PokerCard(cardString.charAt(4), cardString.charAt(5));
		PokerCard flop2 = new PokerCard(cardString.charAt(6), cardString.charAt(7));
		PokerCard flop3 = new PokerCard(cardString.charAt(8), cardString.charAt(9));

		if (cardString.length() == 10)
			return winningOddsGivenFlop(hand, flop1, flop2, flop3);

		PokerCard turn = new PokerCard(cardString.charAt(10), cardString.charAt(11));

		if (cardString.length() == 12)
			return winningOddsGivenTurn(hand, flop1, flop2, flop3, turn);

		PokerCard river = new PokerCard(cardString.charAt(12), cardString.charAt(13));
		PokerTable table = new PokerTable(flop1, flop2, flop3, turn, river);

		if (cardString.length() == 14)
			return winningOddsGivenRiver(hand, table);
		else
			return -1;
	}

	public float winningOddsGivenRiver(PokerHand hand, PokerTable table) {
		ArrayList<PokerCard> remainingCards = new ArrayList<>();
		for (int i = 0; i < AllCards.NUMCARDS; i++)
			remainingCards.add(AllCards.getCard(i));
		remainingCards.remove(hand.getCard1());
		remainingCards.remove(hand.getCard2());
		remainingCards.remove(table.getFlop1());
		remainingCards.remove(table.getFlop2());
		remainingCards.remove(table.getFlop3());
		remainingCards.remove(table.getTurn());
		remainingCards.remove(table.getRiver());

		float numWins = 0;
		int numIterations = 0;
		int numCards = remainingCards.size();
		for (int card1Index = 0; card1Index < numCards; card1Index++) {
			for (int card2Index = card1Index + 1; card2Index < numCards; card2Index++) {
				PokerHand currHand = new PokerHand(remainingCards.get(card1Index), remainingCards.get(card2Index));
				if (CompareTwoHands.determineWinner(table, hand, currHand) == 1)// hand beat currHand
					numWins++;
				numIterations++;
			}
		}
		return numWins / numIterations;
	}

	public float winningOddsGivenFlop(PokerHand hand, PokerCard flop1, PokerCard flop2, PokerCard flop3) {
		ArrayList<PokerCard> remainingCards = new ArrayList<>();
		for (int i = 0; i < AllCards.NUMCARDS; i++)
			remainingCards.add(AllCards.getCard(i));
		remainingCards.remove(hand.getCard1());
		remainingCards.remove(hand.getCard2());
		remainingCards.remove(flop1);
		remainingCards.remove(flop2);
		remainingCards.remove(flop3);

		float sumOfAverages = 0;
		int numTables = 0;
		int numCards = remainingCards.size();

		for (int turnIndex = 0; turnIndex < numCards; turnIndex++) {
			sumOfAverages += winningOddsGivenTurn(hand, flop1, flop2, flop3, remainingCards.get(turnIndex));
			numTables++;
		}
		return sumOfAverages / numTables;
	}

	public float winningOddsGivenTurn(PokerHand hand, PokerCard flop1, PokerCard flop2, PokerCard flop3, PokerCard turn) {
		ArrayList<PokerCard> remainingCards = new ArrayList<>();
		for (int i = 0; i < AllCards.NUMCARDS; i++)
			remainingCards.add(AllCards.getCard(i));
		remainingCards.remove(hand.getCard1());
		remainingCards.remove(hand.getCard2());
		remainingCards.remove(flop1);
		remainingCards.remove(flop2);
		remainingCards.remove(flop3);
		remainingCards.remove(turn);


		float sumOfAverages = 0;
		int numTables = 0;
		int numCards = remainingCards.size();

		for (int riverIndex = 0; riverIndex < numCards; riverIndex++) {
			PokerTable currTable = new PokerTable(flop1, flop2, flop3, turn, remainingCards.get(riverIndex));
			sumOfAverages += winningOddsGivenRiver(hand, currTable);
			numTables++;
		}
		return sumOfAverages / numTables;
	}
}