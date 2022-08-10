package com.example.mypokerassistant.PokerStats;

import androidx.annotation.NonNull;

import com.example.mypokerassistant.PokerParts.AllCards;
import com.example.mypokerassistant.PokerParts.PokerCard;
import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Contains methods to calculate winning odds given a poker hand and at least three table cards
 * @author FrickTob
 */
public class PokerStats {

	//Formatting must be checked before function call
	public float getOdds(@NonNull String cardString) {

		// Create Cards for Analysis //
		PokerCard handCard1 = new PokerCard(cardString.charAt(0), cardString.charAt(1));
		PokerCard handCard2 = new PokerCard(cardString.charAt(2), cardString.charAt(3));
		PokerHand hand = new PokerHand(handCard1, handCard2);
		PokerCard flop1 = new PokerCard(cardString.charAt(4), cardString.charAt(5));
		PokerCard flop2 = new PokerCard(cardString.charAt(6), cardString.charAt(7));
		PokerCard flop3 = new PokerCard(cardString.charAt(8), cardString.charAt(9));

		// Find Odds Depending on Number of Cards Given
		if (cardString.length() == 10) return winningOddsGivenFlop(hand, flop1, flop2, flop3);

		PokerCard turn = new PokerCard(cardString.charAt(10), cardString.charAt(11));
		if (cardString.length() == 12) return winningOddsGivenTurn(hand, flop1, flop2, flop3, turn);

		PokerCard river = new PokerCard(cardString.charAt(12), cardString.charAt(13));
		PokerTable table = new PokerTable(flop1, flop2, flop3, turn, river);
		if (cardString.length() == 14) return winningOddsGivenRiver(hand, table);
		else return -1;
	}

	public float winningOddsGivenFlop(PokerHand hand, PokerCard flop1, PokerCard flop2, PokerCard flop3) {
		ArrayList<PokerCard> remainingCards = new ArrayList<>(AllCards.ALLCARDS);

		remainingCards.remove(hand.getCard1());
		remainingCards.remove(hand.getCard2());
		remainingCards.remove(flop1);
		remainingCards.remove(flop2);
		remainingCards.remove(flop3);

		float sumOfAverages = 0;

		int numCards = remainingCards.size();
		for (PokerCard currTurnCard : remainingCards) {
			sumOfAverages += winningOddsGivenTurn(hand, flop1, flop2, flop3, currTurnCard);
		}
		return sumOfAverages / numCards;
	}

	public float winningOddsGivenTurn(PokerHand hand, PokerCard flop1, PokerCard flop2, PokerCard flop3, PokerCard turn) {
		ArrayList<PokerCard> remainingCards = new ArrayList<>(AllCards.ALLCARDS);

		remainingCards.remove(hand.getCard1());
		remainingCards.remove(hand.getCard2());
		remainingCards.remove(flop1);
		remainingCards.remove(flop2);
		remainingCards.remove(flop3);
		remainingCards.remove(turn);


		float sumOfAverages = 0;
		int numCards = remainingCards.size();

		for (PokerCard currRiverCard : remainingCards) {
			PokerTable currTable = new PokerTable(flop1, flop2, flop3, turn, currRiverCard);
			sumOfAverages += winningOddsGivenRiver(hand, currTable);
		}
		return sumOfAverages / numCards;
	}

	public float winningOddsGivenRiver(PokerHand hand, PokerTable table) {
		ArrayList<PokerCard> remainingCards = new ArrayList<>(AllCards.ALLCARDS);

		remainingCards.remove(hand.getCard1());
		remainingCards.remove(hand.getCard2());
		remainingCards.remove(table.getFlop1());
		remainingCards.remove(table.getFlop2());
		remainingCards.remove(table.getFlop3());
		remainingCards.remove(table.getTurn());
		remainingCards.remove(table.getRiver());

		float numWins = 0;
		float numIterations = 0;
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
}