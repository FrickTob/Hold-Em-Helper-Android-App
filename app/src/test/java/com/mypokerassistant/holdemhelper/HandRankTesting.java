package com.mypokerassistant.holdemhelper;

import com.mypokerassistant.holdemhelper.PokerParts.PokerHandSuitless;
import com.mypokerassistant.holdemhelper.PokerParts.HandRankList;

import junit.framework.TestCase;

/**
 * Testing to ensure Hand Rank is obtained correctly
 * @author tobyf
 *
 */
public class HandRankTesting extends TestCase {

    public void testHandRankList() {
        PokerHandSuitless pocketRockets = new PokerHandSuitless('A', 'A', false);
        PokerHandSuitless TwoSevenOffSuit = new PokerHandSuitless('2', '7', false);
        assertEquals(HandRankList.getRank(pocketRockets), 1);
        assertEquals(HandRankList.getWinPercent(1), .31);
        assertEquals(HandRankList.getRank(TwoSevenOffSuit), 169);
        assertEquals(HandRankList.getWinPercent(169), .04);
    }
}
