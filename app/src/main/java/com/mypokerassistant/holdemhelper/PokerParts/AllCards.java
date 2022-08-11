package com.mypokerassistant.holdemhelper.PokerParts;

import java.util.ArrayList;

/**
 * All possible poker cards are listed here to be used in generating winning odds statistics
 * @author FrickTob
 */
public class AllCards {
	
	private AllCards() {}

	public static final ArrayList<PokerCard> ALLCARDS = new ArrayList<PokerCard>()

	{
		{
			add(new PokerCard('2','C')); // 0
			add(new PokerCard('3','C')); // 1
			add(new PokerCard('4','C')); // 2
			add(new PokerCard('5','C')); // 3
			add(new PokerCard('6','C')); // 4
			add(new PokerCard('7','C')); // 5
			add(new PokerCard('8','C')); // 6
			add(new PokerCard('9','C')); // 7
			add(new PokerCard('0','C')); // 8
			add(new PokerCard('J','C')); // 9
			add(new PokerCard('Q','C')); // 10
			add(new PokerCard('K','C')); // 11
			add(new PokerCard('A','C')); // 12
			add(new PokerCard('2','D')); // 13
			add(new PokerCard('3','D')); // 14
			add(new PokerCard('4','D')); // 15
			add(new PokerCard('5','D')); // 16
			add(new PokerCard('6','D')); // 17
			add(new PokerCard('7','D')); // 18
			add(new PokerCard('8','D')); // 19
			add(new PokerCard('9','D')); // 20
			add(new PokerCard('0','D')); // 21
			add(new PokerCard('J','D')); // 22
			add(new PokerCard('Q','D')); // 23
			add(new PokerCard('K','D')); // 24
			add(new PokerCard('A','D')); // 25
			add(new PokerCard('2','H')); // 26
			add(new PokerCard('3','H')); // 27
			add(new PokerCard('4','H')); // 28
			add(new PokerCard('5','H')); // 29
			add(new PokerCard('6','H')); // 30
			add(new PokerCard('7','H')); // 31
			add(new PokerCard('8','H')); // 32
			add(new PokerCard('9','H')); // 33
			add(new PokerCard('0','H')); // 34
			add(new PokerCard('J','H')); // 35
			add(new PokerCard('Q','H')); // 36
			add(new PokerCard('K','H')); // 37
			add(new PokerCard('A','H')); // 38
			add(new PokerCard('2','S')); // 39
			add(new PokerCard('3','S')); // 40
			add(new PokerCard('4','S')); // 41
			add(new PokerCard('5','S')); // 42
			add(new PokerCard('6','S')); // 43
			add(new PokerCard('7','S')); // 44
			add(new PokerCard('8','S')); // 45
			add(new PokerCard('9','S')); // 46
			add(new PokerCard('0','S')); // 47
			add(new PokerCard('J','S')); // 48
			add(new PokerCard('Q','S')); // 49
			add(new PokerCard('K','S')); // 50
			add(new PokerCard('A','S')); // 51
	}};
	
	public static PokerCard getCard(char value, char suit) {
		if(suit == 'C') {
			switch (value) {
			case '2' : return ALLCARDS.get(0);
			case '3' : return ALLCARDS.get(1);
			case '4' : return ALLCARDS.get(2);
			case '5' : return ALLCARDS.get(3);
			case '6' : return ALLCARDS.get(4);
			case '7' : return ALLCARDS.get(5);
			case '8' : return ALLCARDS.get(6);
			case '9' : return ALLCARDS.get(7);
			case '0' : return ALLCARDS.get(8);
			case 'J' : return ALLCARDS.get(9);
			case 'Q' : return ALLCARDS.get(10);
			case 'K' : return ALLCARDS.get(11);
			case 'A' : return ALLCARDS.get(12);
			}
		}
		if(suit == 'D') {
			switch (value) {
			case '2' : return ALLCARDS.get(13);
			case '3' : return ALLCARDS.get(14);
			case '4' : return ALLCARDS.get(15);
			case '5' : return ALLCARDS.get(16);
			case '6' : return ALLCARDS.get(17);
			case '7' : return ALLCARDS.get(18);
			case '8' : return ALLCARDS.get(19);
			case '9' : return ALLCARDS.get(20);
			case '0' : return ALLCARDS.get(21);
			case 'J' : return ALLCARDS.get(22);
			case 'Q' : return ALLCARDS.get(23);
			case 'K' : return ALLCARDS.get(24);
			case 'A' : return ALLCARDS.get(25);
			}
		}
		if(suit == 'H') {
			switch (value) {
			case '2' : return ALLCARDS.get(26);
			case '3' : return ALLCARDS.get(27);
			case '4' : return ALLCARDS.get(28);
			case '5' : return ALLCARDS.get(29);
			case '6' : return ALLCARDS.get(30);
			case '7' : return ALLCARDS.get(31);
			case '8' : return ALLCARDS.get(32);
			case '9' : return ALLCARDS.get(33);
			case '0' : return ALLCARDS.get(34);
			case 'J' : return ALLCARDS.get(35);
			case 'Q' : return ALLCARDS.get(36);
			case 'K' : return ALLCARDS.get(37);
			case 'A' : return ALLCARDS.get(38);
			}
		}
		if(suit == 'S') {
			switch (value) {
			case '2' : return ALLCARDS.get(39);
			case '3' : return ALLCARDS.get(40);
			case '4' : return ALLCARDS.get(41);
			case '5' : return ALLCARDS.get(42);
			case '6' : return ALLCARDS.get(43);
			case '7' : return ALLCARDS.get(44);
			case '8' : return ALLCARDS.get(45);
			case '9' : return ALLCARDS.get(46);
			case '0' : return ALLCARDS.get(47);
			case 'J' : return ALLCARDS.get(48);
			case 'Q' : return ALLCARDS.get(49);
			case 'K' : return ALLCARDS.get(50);
			case 'A' : return ALLCARDS.get(51);
			}
		}
		return null;
	}
	public static PokerCard getCard(int cardIndex) {
		return ALLCARDS.get(cardIndex);
	}
	
	public static final int NUMCARDS = ALLCARDS.size();
}
