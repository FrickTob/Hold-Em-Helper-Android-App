package com.example.mypokerassistant;

import com.example.mypokerassistant.PokerParts.PokerCard;
import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerHandSuitless;
import com.example.mypokerassistant.PokerParts.PokerTable;
import com.example.mypokerassistant.PokerStats.AnalyzeHand;
import com.example.mypokerassistant.PokerStats.CompareTwoHands;
import com.example.mypokerassistant.PokerStats.HandRankList;

import junit.framework.TestCase;

/**
 * Testing to ensure logic is correct when comparing two hands
 * @author tobyf
 *
 */
public class HandRankTesting extends TestCase {

    public void testHandRankList() {
        PokerHandSuitless hand = HandRankList.getHand(0);
        PokerHandSuitless handy = new PokerHandSuitless('A', 'A', false);
        assertEquals(hand.getString(), handy.getString());
    }
}
