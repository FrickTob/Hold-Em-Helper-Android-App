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
 */
public class Stats2Player {

	//Formatting must be checked before function call
	public double getOdds(@NonNull String cardString) {


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

	public double winningOddsGivenFlop(PokerHand hand, PokerCard flop1, PokerCard flop2, PokerCard flop3) {
		ArrayList<PokerCard> remainingCards = new ArrayList<>();
		for (int i = 0; i < AllCards.NUMCARDS; i++)
			remainingCards.add(AllCards.getCard(i));
		remainingCards.remove(hand.getCard1());
		remainingCards.remove(hand.getCard2());
		remainingCards.remove(flop1);
		remainingCards.remove(flop2);
		remainingCards.remove(flop3);

		float sumOfAverages = 0;
		// int numTables = 0;
		int numCards = remainingCards.size();


		ExecutorService executor = Executors.newFixedThreadPool(5);
		flopOddsThread[] oddsThreads = setUpThreads(hand, flop1, flop2, flop3, remainingCards);
		for (flopOddsThread currThread : oddsThreads)
			executor.execute(currThread);

		executor.shutdown();
		while (!executor.isTerminated()) { }

		for (flopOddsThread currThread : oddsThreads) {
			sumOfAverages += currThread.getOdds();
		}

//		for (int turnIndex = 0; turnIndex < numCards; turnIndex++) {
//			sumOfAverages += winningOddsGivenTurn(hand, flop1, flop2, flop3, remainingCards.get(turnIndex));
//		}
		return sumOfAverages / numCards;
	}

	public double winningOddsGivenTurn(PokerHand hand, PokerCard flop1, PokerCard flop2, PokerCard flop3, PokerCard turn) {
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

	public double winningOddsGivenRiver(PokerHand hand, PokerTable table) {
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


	//winningOddsGivenFlop takes a long time to execute, so these threads break up the computation for faster run time
	class flopOddsThread extends Thread {
		private PokerHand hand;
		private PokerCard flop1;
		private PokerCard flop2;
		private PokerCard flop3;
		private PokerCard[] cards;
		private double oddsSum = 0;

		public flopOddsThread(PokerHand hand, PokerCard flop1, PokerCard flop2, PokerCard flop3, PokerCard[] cards) {
			this.hand = hand;
			this.flop1 = flop1;
			this.flop2 = flop2;
			this.flop3 = flop3;
			this.cards = cards;
		}

		@Override
		public void run() {
			for (PokerCard card : cards)
				oddsSum += winningOddsGivenTurn(hand, flop1, flop2, flop3, card);
		}

		public double getOdds() {
			return oddsSum;
		}
	}

	// Split the 45 remaining cards
	public flopOddsThread[] setUpThreads(PokerHand hand, PokerCard flop1, PokerCard flop2, PokerCard flop3, ArrayList<PokerCard> cards) {

		flopOddsThread[] oddsThreads = new flopOddsThread[5];
		PokerCard[] thread1Cards = new PokerCard[2];
		for (int i = 0; i < 2; i++) {
			thread1Cards[i] = cards.get(i);
		}
		PokerCard[] thread2Cards = new PokerCard[5];
		for (int i = 0; i < 5; i++) {
			int j = 2;
			thread2Cards[i] = cards.get(j);
			j++;
		}
		PokerCard[] thread3Cards = new PokerCard[9];
		for (int i = 0; i < 9; i++) {
			int j = 7;
			thread3Cards[i] = cards.get(j);
			j++;
		}
		PokerCard[] thread4Cards = new PokerCard[13];
		for (int i = 0; i < 13; i++) {
			int j = 16;
			thread4Cards[i] = cards.get(j);
			j++;
		}
		PokerCard[] thread5Cards = new PokerCard[16];
		for (int i = 0; i < 16; i++) {
			int j = 29;
			thread5Cards[i] = cards.get(j);
			j++;
		}
		oddsThreads[0] = new flopOddsThread(hand, flop1, flop2, flop3, thread1Cards);
		oddsThreads[1] = new flopOddsThread(hand, flop1, flop2, flop3, thread2Cards);
		oddsThreads[2] = new flopOddsThread(hand, flop1, flop2, flop3, thread3Cards);
		oddsThreads[3] = new flopOddsThread(hand, flop1, flop2, flop3, thread4Cards);
		oddsThreads[4] = new flopOddsThread(hand, flop1, flop2, flop3, thread5Cards);

		return oddsThreads;
	}
}

