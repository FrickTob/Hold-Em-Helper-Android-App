package com.example.mypokerassistant.PokerParts;

import com.example.mypokerassistant.PokerParts.PokerCard;
import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerHandSuitless;

import java.util.ArrayList;

/**
 * All possible starting hands are listed here in order for use in finding ranks
 * @author tobyf
 */
public class HandRankList {

    private HandRankList() {}

    public static final ArrayList<PokerHandSuitless> HAND_RANK_ARRAY = new ArrayList<PokerHandSuitless>()

    {
        {
            // 1. Pair of Aces
            add(new PokerHandSuitless('A','A',false, .31));
            // 2. Pair of Kinds
            add(new PokerHandSuitless('K','K',false, .26));
            // 3. Pair of Queens
            add(new PokerHandSuitless('Q','Q',false, .22));
            // 4. Ace King Suited
            add(new PokerHandSuitless('A','K',true, .2020));
            // 5. Pair of Jacks
            add(new PokerHandSuitless('J','J',false, .191));
            // 6. Ace Queen Suited
            add(new PokerHandSuitless('A','Q',true, .187));
            // 7. King Queen Suited
            add(new PokerHandSuitless('K','Q',true, .181));
            // 8. Ace Jack Suited
            add(new PokerHandSuitless('A','J',true, .175));
            // 9. King Jack Suited
            add(new PokerHandSuitless('K','J',true, .171));
            // 10. Pair of Tens
            add(new PokerHandSuitless('0','0',false, .168));
            // 11. Ace King Offsuit
            add(new PokerHandSuitless('A', 'K', false, .167));
            // 12. Ace Ten Suited
            add(new PokerHandSuitless('A','0', true, .166));
            // 13. Queen Jack Suited
            add(new PokerHandSuitless('Q','J', true, .166));
            // 14. King Ten Suited
            add(new PokerHandSuitless('K','0', true, .161));
            // 15. Queen Ten Suited
            add(new PokerHandSuitless('Q', '0', true, .158));
            // 16. Jack Ten Suited
            add(new PokerHandSuitless('J', '0', true, .158));
            // 17. Pair of Nines
            add(new PokerHandSuitless('9', '9', false, .153));
            // 18. Ace Queen Offsuit
            add(new PokerHandSuitless('A', 'Q', false, .149));
            // 19. Ace Nine Suited
            add(new PokerHandSuitless('A', '9', true, .146));
            // 20. King Queen Offsuit
            add(new PokerHandSuitless('K', 'Q', false, .144));
            // 21. Pair of Eights
            add(new PokerHandSuitless('8', '8', false, .142));
            // 22. King Nine Suited
            add(new PokerHandSuitless('K', '9', true, .142));
            // 23. Ten Nine Suited
            add(new PokerHandSuitless('0', '9', true, .141));
            // 24. Ace Eight Suited
            add(new PokerHandSuitless('A', '8', true, .139));
            // 25. Queen Nine Suited
            add(new PokerHandSuitless('Q', '9', true, .138));
            // 26. Jack Nine Suited
            add(new PokerHandSuitless('J', '9', true, .138));
            // 27. Ace Jack Offsuit
            add(new PokerHandSuitless('A', 'J', false, .135));
            // 28. Ace Five Suited
            add(new PokerHandSuitless('A', '5', true, .134));
            // 29. Pair of Sevens
            add(new PokerHandSuitless('7', '7', false, .134));
            // 30. Ace Seven Suited
            add(new PokerHandSuitless('A', '7', true, .134));
            // 31. King Jack Offsuit
            add(new PokerHandSuitless('K', 'J', false, .132));
            // 32. Ace Four Suited
            add(new PokerHandSuitless('A', '4', true, .132));
            // 33. Ace Three Suited
            add(new PokerHandSuitless('A', '3', true, .131));
            // 34. Ace Six Suited
            add(new PokerHandSuitless('A', '6', true, .13));
            // 35. Queen Jack Offsuit
            add(new PokerHandSuitless('Q', 'J', false, .129));
            // 36. Pair of Sixes
            add(new PokerHandSuitless('6', '6', false, .128));
            // 37. King Eight Suited
            add(new PokerHandSuitless('K', '8', true, .128));
            // 38. Ten Eight Suited
            add(new PokerHandSuitless('0', '8', true, .127));
            // 39. Ace Two Suited
            add(new PokerHandSuitless('A', '2', true, .127));
            // 40. Nine Eight Suited
            add(new PokerHandSuitless('9', '8', true, .126));
            // 41. Jack Eight Suited
            add(new PokerHandSuitless('J', '8', true, .125));
            // 42. Ace Ten Offsuit
            add(new PokerHandSuitless('A', '0', false, .124));
            // 43. Queen Eight Suited
            add(new PokerHandSuitless('Q', '8', true, .124));
            // 44. King Seven Suited
            add(new PokerHandSuitless('K', '7', true, .122));
            // 45. King Ten Offsuit
            add(new PokerHandSuitless('K', '0', false, .122));
            // 46. Pair of Fives
            add(new PokerHandSuitless('5', '5', false, .122));
            // 47. Jack Ten Offsuit
            add(new PokerHandSuitless('J', '0', false, .121));
            // 48. Seven Eight Suited
            add(new PokerHandSuitless('7', '8', true, .12));
            // 49. Queen Ten Suited
            add(new PokerHandSuitless('Q', '0', true, .12));
            // 50. Pair of Fours
            add(new PokerHandSuitless('4', '4', false, .119));
            // 51. Pair of Threes
            add(new PokerHandSuitless('3', '3', false, .119));
            // 52. Pair of Twos
            add(new PokerHandSuitless('2', '2', false, .119));
            // 53. King Six Suited
            add(new PokerHandSuitless('K', '6', true, .118));
            // 54. Nine Seven Suited
            add(new PokerHandSuitless('9', '7', true, .117));
            // 55. King Five Suited
            add(new PokerHandSuitless('K', '5', true, .116));
            // 56. Seven Six Suited
            add(new PokerHandSuitless('7', '6', true, .115));
            // 57. Ten Seven Suited
            add(new PokerHandSuitless('0', '7', true, .115));
            // 58. King Four Suited
            add(new PokerHandSuitless('K', '4', true, .114));
            // 59. King Three Suited
            add(new PokerHandSuitless('K', '3', true, .113));
            // 60. King Two Suited
            add(new PokerHandSuitless('K', '2', true, .113));
            // 61. Queen Seven Suited
            add(new PokerHandSuitless('Q', '7', true, .112));
            // 62. Eight Six Suited
            add(new PokerHandSuitless('8', '6', true, .112));
            // 63. Six Five Suited
            add(new PokerHandSuitless('6', '5', true, .111));
            // 64. Jack Seven Suited
            add(new PokerHandSuitless('J', '7', true, .111));
            // 65. Five Four Suited
            add(new PokerHandSuitless('5', '4', true, .109));
            // 66. Queen Six Suited
            add(new PokerHandSuitless('Q', '6', true, .109));
            // 67. Seven Five Suited
            add(new PokerHandSuitless('7', '5', true, .107));
            // 68. Nine Six Suited
            add(new PokerHandSuitless('9', '6', true, .107));
            // 69. Queen Five Suited
            add(new PokerHandSuitless('Q', '5', true, .106));
            // 70. Six Four Suited
            add(new PokerHandSuitless('6', '4', true, .104));
            // 71. Queen Four Suited
            add(new PokerHandSuitless('Q', '4', true, .104));
            // 72. Queen Three Suited
            add(new PokerHandSuitless('Q', '3', true, .104));
            // 73. Ten Nine OffSuit
            add(new PokerHandSuitless('0', '9', false, .104));
            // 74. Ten Six Suited
            add(new PokerHandSuitless('0', '6', true, .103));
            // 75. Queen Two Suited
            add(new PokerHandSuitless('Q', '2', true, .103));
            // 76. Ace Nine OffSuit
            add(new PokerHandSuitless('A', '9', false, .102));
            // 77. Five Three Suited
            add(new PokerHandSuitless('5', '3', true, .102));
            // 78. Eight Five Suited
            add(new PokerHandSuitless('8', '5', true, .101));
            // 79. Jack Six Suited
            add(new PokerHandSuitless('J', '6', true, .101));
            // 80. Jack Nine OffSuit
            add(new PokerHandSuitless('J', '9', false, .1));
            // 81. King Nine OffSuit
            add(new PokerHandSuitless('K', '9', false, .099));
            // 82. Jack Five Suited
            add(new PokerHandSuitless('J', '5', true, .099));
            // 83. Queen Nine OffSuit
            add(new PokerHandSuitless('Q', '9', false, .098));
            // 84. Four Three Suited
            add(new PokerHandSuitless('4', '3', true, .098));
            // 85. Seven Four Suited
            add(new PokerHandSuitless('7', '4', true, .097));
            // 86. Jack Four Suited
            add(new PokerHandSuitless('J', '4', true, .097));
            // 87. Jack Three Suited
            add(new PokerHandSuitless('J', '3', true, .096));
            // 88. Nine Five Suited
            add(new PokerHandSuitless('9', '5', true, .096));
            // 89. Jack Two Suited
            add(new PokerHandSuitless('J', '2', true, .095));
            // 90. Six Three Suited
            add(new PokerHandSuitless('6', '3', true, .095));
            // 91. Ace Eight OffSuit
            add(new PokerHandSuitless('A', '8', false, .094));
            // 92. Five Two Suited
            add(new PokerHandSuitless('5', '2', true, .093));
            // 93. Ten Five Suited
            add(new PokerHandSuitless('0', '5', true, .092));
            // 94. Eight Four Suited
            add(new PokerHandSuitless('8', '4', true, .091));
            // 95. Ten Four Suited
            add(new PokerHandSuitless('0', '4', true, .091));
            // 96. Ten Three Suited
            add(new PokerHandSuitless('0', '3', true, .091));
            // 97. Four Two Suited
            add(new PokerHandSuitless('4', '2', true, .09));
            // 98. Ten Two Suited
            add(new PokerHandSuitless('0', '2', true, .09));
            // 99. Nine Eight OffSuit
            add(new PokerHandSuitless('9', '8', false, .09));
            // 100. Ten Eight OffSuit
            add(new PokerHandSuitless('0', '8', false, .089));
            // 101. Ace Five OffSuit
            add(new PokerHandSuitless('A', '5', false, .089));
            // 102. Ace Seven OffSuit
            add(new PokerHandSuitless('A', '7', false, .088));
            // 103. Seven Three Suited
            add(new PokerHandSuitless('7', '3', true, .088));
            // 104. Ace Four OffSuit
            add(new PokerHandSuitless('A', '4', false, .087));
            // 105. Three Two Suited
            add(new PokerHandSuitless('3', '2', true, .087));
            // 106. Nine Four Suited
            add(new PokerHandSuitless('9', '4', true, .087));
            // 107. Nine Three Suited
            add(new PokerHandSuitless('9', '3', true, .085));
            // 108. Jack Eight OffSuit
            add(new PokerHandSuitless('J', '8', false, .085));
            // 109. Ace Three OffSuit
            add(new PokerHandSuitless('A', '3', false, .085));
            // 110. Six Two Suited
            add(new PokerHandSuitless('6', '2', true, .085));
            // 111. Nine Two Suited
            add(new PokerHandSuitless('9', '2', true, .085));
            // 112. King Eight OffSuit
            add(new PokerHandSuitless('K', '8', false, .085));
            // 113. Ace Six OffSuit
            add(new PokerHandSuitless('A', '6', false, .084));
            // 114. Eight Seven OffSuit
            add(new PokerHandSuitless('8', '7', false, .084));
            // 115. Queen Eight OffSuit
            add(new PokerHandSuitless('Q', '8', false, .083));
            // 116. Eight Three Suited
            add(new PokerHandSuitless('8', '3', true, .082));
            // 117. Ace Two OffSuit
            add(new PokerHandSuitless('A', '2', false, .082));
            // 118. Eight Two Suited
            add(new PokerHandSuitless('8', '2', true, .081));
            // 119. Nine Seven OffSuit
            add(new PokerHandSuitless('9', '7', false, .08));
            // 120. Seven Two Suited
            add(new PokerHandSuitless('7', '2', true, .079));
            // 121. Seven Six OffSuit
            add(new PokerHandSuitless('7', '6', false, .079));
            // 122. King Seven OffSuit
            add(new PokerHandSuitless('K', '7', false, .079));
            // 123. Six Five OffSuit
            add(new PokerHandSuitless('6', '5', false, .076));
            // 124. Ten Seven OffSuit
            add(new PokerHandSuitless('0', '7', false, .075));
            // 125. King Six OffSuit
            add(new PokerHandSuitless('K', '6', false, .075));
            // 126. Eight Six OffSuit
            add(new PokerHandSuitless('8', '6', false, .074));
            // 127. Five Four OffSuit
            add(new PokerHandSuitless('5', '4', false, .074));
            // 128. King Five OffSuit
            add(new PokerHandSuitless('K', '5', false, .071));
            // 129. Jack Seven OffSuit
            add(new PokerHandSuitless('J', '7', false, .071));
            // 130. Seven Five OffSuit
            add(new PokerHandSuitless('7', '5', false, .07));
            // 131. Queen Seven OffSuit
            add(new PokerHandSuitless('Q', '7', false, .07));
            // 132. King Four OffSuit
            add(new PokerHandSuitless('K', '4', false, .07));
            // 133. King Three OffSuit
            add(new PokerHandSuitless('K', '3', false, .069));
            // 134. King Two OffSuit
            add(new PokerHandSuitless('K', '2', false, .068));
            // 135. Nine Six OffSuit
            add(new PokerHandSuitless('9','6',false, .068));
            // 136. Six Four OffSuit
            add(new PokerHandSuitless('6', '4', false, .068));
            // 137. Queen Six OffSuit
            add(new PokerHandSuitless('Q', '6', false, .066));
            // 138. Five Three OffSuit
            add(new PokerHandSuitless('5', '3', false, .066));
            // 139. Eight Five OffSuit
            add(new PokerHandSuitless('8', '5', false, .063));
            // 140. Ten Six OffSuit
            add(new PokerHandSuitless('0', '6', false, .063));
            // 141. Queen Five OffSuit
            add(new PokerHandSuitless('Q', '5', false, .063));
            // 142. Four Three OffSuit
            add(new PokerHandSuitless('4', '3', false, .062));
            // 143. Queen Four OffSuit
            add(new PokerHandSuitless('Q', '4', false, .061));
            // 144. Queen Three OffSuit
            add(new PokerHandSuitless('Q', '3', false, .061));
            // 145. Queen Two OffSuit
            add(new PokerHandSuitless('Q', '2', false, .06));
            // 146. Seven Four OffSuit
            add(new PokerHandSuitless('7', '4', false, .06));
            // 147. Jack Six OffSuit
            add(new PokerHandSuitless('J', '6', false, .059));
            // 148. Six Three OffSuit
            add(new PokerHandSuitless('6', '3', false, .057));
            // 149. Jack Five OffSuit
            add(new PokerHandSuitless('J', '5', false, .056));
            // 150. Nine Five OffSuit
            add(new PokerHandSuitless('9', '5', false, .056));
            // 151. Five Two OffSuit
            add(new PokerHandSuitless('5', '2', false, .056));
            // 152. Jack Four OffSuit
            add(new PokerHandSuitless('J', '4', false, .055));
            // 153. Jack Three OffSuit
            add(new PokerHandSuitless('J', '3', false, .054));
            // 154. Four Two OffSuit
            add(new PokerHandSuitless('4', '2', false, .054));
            // 155. Jack Two OffSuit
            add(new PokerHandSuitless('J', '2', false, .053));
            // 156. Eight Four OffSuit
            add(new PokerHandSuitless('8', '4', false, .053));
            // 157. Ten Five OffSuit
            add(new PokerHandSuitless('0', '5', false, .052));
            // 158. Ten Four OffSuit
            add(new PokerHandSuitless('0', '4', false, .05));
            // 159. Three Two OffSuit
            add(new PokerHandSuitless('3', '2', false, .05));
            // 160. Ten Three OffSuit
            add(new PokerHandSuitless('0', '3', false, .05));
            // 161. Seven Three OffSuit
            add(new PokerHandSuitless('7', '3', false, .049));
            // 162. Ten Two OffSuit
            add(new PokerHandSuitless('0', '2', false, .049));
            // 163. Six Two OffSuit
            add(new PokerHandSuitless('6', '2', false, .047));
            // 164. Nine Four OffSuit
            add(new PokerHandSuitless('9', '4', false, .047));
            // 165. Nine Three OffSuit
            add(new PokerHandSuitless('9', '3', false, .045));
            // 166. Nine Two OffSuit
            add(new PokerHandSuitless('9', '2', false, .045));
            // 167. Eight Three OffSuit
            add(new PokerHandSuitless('8', '3', false, .043));
            // 168. Eight Two OffSuit
            add(new PokerHandSuitless('8', '2', false, .042));
            // 169. Two Seven OffSuit
            add(new PokerHandSuitless('2', '7', false, .04));


        }};

    public static final int NUM_HANDS = HAND_RANK_ARRAY.size();

    /**
     * Search for hand in list for rank of that hand
     * @param hand hand to find
     * @return rank of hand
     */
    public static int getRank(PokerHandSuitless hand) {
        String handString = hand.getString();
        for (int i = 0; i < NUM_HANDS; i++) {
            PokerHandSuitless arrayHand = HAND_RANK_ARRAY.get(i);
            String arrayHandString = arrayHand.getString();
            if (arrayHandString.equals(handString))
                return i + 1;
        }
        return -1;
    }

    /**
     * Get win percent associated with a given rank
     * @param rank rank of hand you want to find win percentage of
     * @return win percent of hand at rank
     */
    public static double getWinPercent(int rank) {
        return HAND_RANK_ARRAY.get(rank - 1).getWinPercent();
    }
 }
