package com.example.mypokerassistant.PokerStats;

import com.example.mypokerassistant.PokerParts.AllCards;
import com.example.mypokerassistant.PokerParts.PokerCard;
import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerTable;

import java.util.ArrayList;

public class Stats2Player {

	//Formatting must be checked before function call
	public float getOdds(String cardString) {

		// Sort user input string into values and suits //
		Character[] values = new Character[cardString.length() / 2];
		for(int i = 0; i < cardString.length(); i += 2) {
			values[i] = Character.toUpperCase(cardString.charAt(i)); // case insensitive user input
		}
		Character[] suits = new Character[cardString.length() / 2];
		for(int i = 1; i < cardString.length(); i += 2) {
			suits[i] = Character.toUpperCase(cardString.charAt(i)); // case insensitive user input
		}

		// Create Cards for Analysis //
		PokerCard handCard1 = new PokerCard(values[0], suits[0]);
		PokerCard handCard2 = new PokerCard(values[1], suits[1]);
		PokerHand hand = new PokerHand(handCard1, handCard2);
		PokerCard flop1 = new PokerCard(values[2], suits[2]);
		PokerCard flop2 = new PokerCard(values[3], suits[3]);
		PokerCard flop3 = new PokerCard(values[4], suits[4]);

		if (cardString.length() == 10)
			return winningOddsGivenFlop(hand, flop1, flop2, flop3);

		PokerCard turn = new PokerCard(values[5], suits[5]);

		if (cardString.length() == 12)
			return winningOddsGivenTurn(hand, flop1, flop2, flop3, turn);

		PokerCard river = new PokerCard(values[6], suits[6]);
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