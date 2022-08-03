package com.example.mypokerassistant;

import com.example.mypokerassistant.PokerParts.PokerHandSuitless;
import com.example.mypokerassistant.PokerParts.HandRankList;

import junit.framework.TestCase;

/**
 * Testing to ensure logic is correct when comparing two hands
 * @author tobyf
 *
 */
public class HandRankTesting extends TestCase {

    public void testHandRankList() {
        PokerHandSuitless hand = new PokerHandSuitless('A', 'A', false);
        assertEquals(HandRankList.getRank(hand), 1);
        assertEquals(HandRankList.getWinPercent(1), .31);
    }
}
