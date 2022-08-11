package com.mypokerassistant.holdemhelper;

import com.mypokerassistant.holdemhelper.PokerParts.PokerCard;
import com.mypokerassistant.holdemhelper.PokerParts.PokerHand;
import com.mypokerassistant.holdemhelper.PokerParts.PokerTable;
import com.mypokerassistant.holdemhelper.PokerStats.OneHandAnalysis;
import com.mypokerassistant.holdemhelper.PokerStats.CompareTwoHands;

import junit.framework.TestCase;

/**
 * Testing to ensure logic is correct when comparing two hands
 * @author tobyf
 *
 */
public class WinningHandTesting extends TestCase {
    PokerTable KHQS7H5D3C = new PokerTable(new PokerCard('Q', 'S'),
            new PokerCard('K', 'H'),
            new PokerCard('3', 'C'),
            new PokerCard('5', 'D'),
            new PokerCard('7', 'H'));
    PokerTable KHQSJC0D7H = new PokerTable(new PokerCard('Q', 'S'),
            new PokerCard('K', 'H'),
            new PokerCard('J', 'C'),
            new PokerCard('0', 'D'),
            new PokerCard('7', 'H'));
    PokerTable KHQHJH0D7H = new PokerTable(new PokerCard('Q', 'H'),
            new PokerCard('K', 'H'),
            new PokerCard('J', 'H'),
            new PokerCard('0', 'D'),
            new PokerCard('7', 'H'));
    PokerTable KHQHJH0H7H = new PokerTable(new PokerCard('Q', 'H'),
            new PokerCard('K', 'H'),
            new PokerCard('J', 'H'),
            new PokerCard('0', 'H'),
            new PokerCard('7', 'H'));
    PokerTable KCKHJH0H7H = new PokerTable(new PokerCard('K', 'C'),
            new PokerCard('K', 'H'),
            new PokerCard('J', 'H'),
            new PokerCard('0', 'H'),
            new PokerCard('7', 'H'));
    PokerTable KCKHJCJH7H = new PokerTable(new PokerCard('K', 'C'),
            new PokerCard('K', 'H'),
            new PokerCard('J', 'H'),
            new PokerCard('J', 'C'),
            new PokerCard('7', 'H'));
    PokerTable KCQCJC0C5H = new PokerTable(new PokerCard('K', 'C'),
            new PokerCard('Q', 'C'),
            new PokerCard('J', 'C'),
            new PokerCard('0', 'C'),
            new PokerCard('5', 'H'));
    PokerTable straightFlushTable = new PokerTable(new PokerCard('3', 'S'),
            new PokerCard('4', 'S'),
            new PokerCard('5', 'S'),
            new PokerCard('6', 'H'),
            new PokerCard('7', 'H')
            );


    public void testStraightFlush() {
        PokerHand hand1 = new PokerHand(new PokerCard('A', 'C'), new PokerCard('K', 'H'));
        PokerHand hand2 = new PokerHand(new PokerCard('A', 'S'), new PokerCard('2', 'S'));
        PokerHand hand3 = new PokerHand(new PokerCard('6', 'S'), new PokerCard('7', 'S'));

        assertEquals(1, CompareTwoHands.determineWinner(straightFlushTable, hand2, hand1));

        OneHandAnalysis straightFlushHand = new OneHandAnalysis(hand2, straightFlushTable);
        OneHandAnalysis straightFlushHand2 = new OneHandAnalysis(hand3, straightFlushTable);
        assertEquals(1, straightFlushHand.determineHandStrength());
        assertEquals('5', straightFlushHand.isStraightFlush());
        assertEquals('7', straightFlushHand2.isStraightFlush());
        assertEquals(2, CompareTwoHands.determineWinner(straightFlushTable, hand2, hand3));
        assertEquals(1, CompareTwoHands.determineWinner(KCQCJC0C5H, hand1, hand2));
    }

    public void testFourOfaKind() {
        PokerHand hand1 = new PokerHand(new PokerCard('K', 'D'), new PokerCard('K', 'S'));
        PokerHand hand2 = new PokerHand(new PokerCard('J', 'S'), new PokerCard('J', 'D'));

        assertEquals(1, CompareTwoHands.determineWinner(KCKHJCJH7H, hand1, hand2));
        assertEquals(2, CompareTwoHands.determineWinner(KCKHJCJH7H, hand2, hand1));
    }

    public void testFullHouse() {
        PokerHand hand1 = new PokerHand(new PokerCard('K', 'D'), new PokerCard('J', 'S'));
        PokerHand hand2 = new PokerHand(new PokerCard('K', 'S'), new PokerCard('0', 'D'));
        PokerHand hand3 = new PokerHand(new PokerCard('J', 'C'), new PokerCard('J', 'D'));

        assertEquals(1, CompareTwoHands.determineWinner(KCKHJH0H7H, hand1, hand2));
        assertEquals(2, CompareTwoHands.determineWinner(KCKHJH0H7H, hand2, hand1));
        assertEquals(1, CompareTwoHands.determineWinner(KCKHJH0H7H, hand1, hand3));
    }

    public void testFlush() {
        PokerHand hand1 = new PokerHand(new PokerCard('6', 'H'), new PokerCard('9', 'S'));
        PokerHand hand2 = new PokerHand(new PokerCard('2', 'D'), new PokerCard('A', 'H'));
        PokerHand hand3 = new PokerHand(new PokerCard('2', 'C'), new PokerCard('5', 'C'));

        assertEquals(2, CompareTwoHands.determineWinner(KHQHJH0D7H, hand1, hand2));
        assertEquals(1, CompareTwoHands.determineWinner(KHQHJH0D7H, hand2, hand1));
        assertEquals(0, CompareTwoHands.determineWinner(KHQHJH0H7H, hand1, hand3));
    }

    public void testStraight() {
        PokerHand hand1 = new PokerHand(new PokerCard('7', 'D'), new PokerCard('9', 'S'));
        PokerHand hand2 = new PokerHand(new PokerCard('2', 'D'), new PokerCard('A', 'D'));
        PokerHand hand3 = new PokerHand(new PokerCard('2', 'C'), new PokerCard('A', 'C'));

        assertEquals(2, CompareTwoHands.determineWinner(KHQSJC0D7H, hand1, hand2));
        assertEquals(1, CompareTwoHands.determineWinner(KHQSJC0D7H, hand2, hand1));
        assertEquals(0, CompareTwoHands.determineWinner(KHQSJC0D7H, hand2, hand3));
    }

    public void testHighCard() {
        PokerHand hand1 = new PokerHand(new PokerCard('2', 'H'), new PokerCard('9', 'S'));
        PokerHand hand2 = new PokerHand(new PokerCard('2', 'D'), new PokerCard('J', 'D'));
        PokerHand hand3 = new PokerHand(new PokerCard('2', 'C'), new PokerCard('A', 'C'));

        assertEquals(0, CompareTwoHands.determineWinner(KHQS7H5D3C, hand1, hand2));
        assertEquals(2, CompareTwoHands.determineWinner(KHQS7H5D3C, hand1, hand3));
        assertEquals(1, CompareTwoHands.determineWinner(KHQS7H5D3C, hand3, hand1));
    }

    public void testOnePair() {
        PokerHand hand1 = new PokerHand(new PokerCard('K', 'S'), new PokerCard('4', 'H'));
        PokerHand hand2 = new PokerHand(new PokerCard('Q' ,'H'), new PokerCard('2', 'C'));
        PokerHand hand3 = new PokerHand(new PokerCard('K' ,'C'), new PokerCard('2', 'C'));

        assertEquals(1, CompareTwoHands.determineWinner(KHQS7H5D3C, hand1, hand2));
        assertEquals(2, CompareTwoHands.determineWinner(KHQS7H5D3C, hand2, hand1));
        assertEquals(0, CompareTwoHands.determineWinner(KHQS7H5D3C, hand1, hand3));
    }

    public void testTwoPair() {
        PokerHand hand1 = new PokerHand(new PokerCard('K', 'S'), new PokerCard('Q', 'H'));
        PokerHand hand2 = new PokerHand(new PokerCard('K' ,'C'), new PokerCard('Q', 'C'));

        assertEquals(0, CompareTwoHands.determineWinner(KHQS7H5D3C, hand1, hand2));

    }


    public void testThreeOfaKind() {
        PokerHand hand1 = new PokerHand(new PokerCard('K', 'S'), new PokerCard('K', 'C'));
        PokerHand hand2 = new PokerHand(new PokerCard('Q', 'C'), new PokerCard('Q', 'D'));
        OneHandAnalysis analyze1 = new OneHandAnalysis(hand1, KHQS7H5D3C);
        OneHandAnalysis analyze2 = new OneHandAnalysis(hand2, KHQS7H5D3C);
        assertEquals(1, CompareTwoHands.determineWinner(KHQS7H5D3C, hand1, hand2));
        assertEquals(2, CompareTwoHands.determineWinner(KHQS7H5D3C, hand2, hand1));
    }
}
