package com.example.mypokerassistant.PokerStats;

import com.example.mypokerassistant.PokerParts.PokerCard;
import com.example.mypokerassistant.PokerParts.PokerHand;
import com.example.mypokerassistant.PokerParts.PokerHandSuitless;

import java.util.ArrayList;

public class HandRankList {

    private HandRankList() {}

    public static final ArrayList<PokerHandSuitless> HandRankArray = new ArrayList<PokerHandSuitless>()

    {

        {
            // 1. Pair of Aces
            add(new PokerHandSuitless('A','A',false));
            // 2. Pair of Kinds
            add(new PokerHandSuitless('K','K',false));
            // 3. Pair of Queens
            add(new PokerHandSuitless('Q','Q',false));
            // 4. Ace King Suited
            add(new PokerHandSuitless('A','K',true));
            // 5. Pair of Jacks
            add(new PokerHandSuitless('J','J',false));
            // 6. Ace Queen Suited
            add(new PokerHandSuitless('A','Q',true));
            // 7. King Queen Suited
            add(new PokerHandSuitless('K','Q',true));
            // 8. Ace Jack Suited
            add(new PokerHandSuitless('A','J',true));
            // 9. King Jack Suited
            add(new PokerHandSuitless('K','J',true));
            // 10. Pair of Tens
            add(new PokerHandSuitless('0','0',false));
            // 11. Ace King Offsuit
            add(new PokerHandSuitless('A', 'K', false));
            // 12. Ace Ten Suited
            add(new PokerHandSuitless('A','0', true));
            // 13. Queen Jack Suited
            add(new PokerHandSuitless('Q','J', true));
            // 14. King Ten Suited
            add(new PokerHandSuitless('K','0', true));
            // 15. Queen Ten Suited
            add(new PokerHandSuitless('Q', '0', true));
            // 16. Jack Ten Suited
            add(new PokerHandSuitless('J', '0', true));
            // 17. Pair of Nines
            add(new PokerHandSuitless('9', '9', false));
            // 18. Ace Queen Offsuit
            add(new PokerHandSuitless('A', 'Q', false));
            // 19. Ace Nine Suited
            add(new PokerHandSuitless('A', '9', true));
            // 20. King Queen Offsuit
            add(new PokerHandSuitless('K', 'Q', false));
            // 21. Pair of Eights
            add(new PokerHandSuitless('8', '8', false));
            // 22. King Nine Suited
            add(new PokerHandSuitless('K', '9', true));
            // 23. Ten Nine Suited
            add(new PokerHandSuitless('0', '9', true));
            // 24. Ace Eight Suited
            add(new PokerHandSuitless('A', '8', true));
            // 25. Queen Nine Suited
            add(new PokerHandSuitless('Q', '9', true));
            // 26. Jack Nine Suited
            add(new PokerHandSuitless('J', '9', true));
            // 27. Ace Jack Offsuit
            add(new PokerHandSuitless('A', 'J', false));
            // 28. Ace Five Suited
            add(new PokerHandSuitless('A', '5', true));
            // 29. Pair of Sevens
            add(new PokerHandSuitless('7', '7', false));
            // 30. Ace Seven Suited
            add(new PokerHandSuitless('A', '7', true));
            // 31. King Jack Offsuit
            add(new PokerHandSuitless('K', 'J', false));
            // 32. Ace Four Suited
            add(new PokerHandSuitless('A', '4', true));
            // 33. Ace Three Suited
            add(new PokerHandSuitless('A', '3', true));
            // 34. Ace Six Suited
            add(new PokerHandSuitless('A', '6', true));
            // 35. Queen Jack Offsuit
            add(new PokerHandSuitless('Q', 'J', false));
            // 36. Pair of Sixes
            add(new PokerHandSuitless('6', '6', false));
            // 37. King Eight Suited
            add(new PokerHandSuitless('K', '8', true));
            // 38. Ten Eight Suited
            add(new PokerHandSuitless('0', '8', true));
            // 39. Ace Two Suited
            add(new PokerHandSuitless('A', '2', true));
            // 40. Nine Eight Suited
            add(new PokerHandSuitless('9', '8', true));
            // 41. Jack Eight Suited
            add(new PokerHandSuitless('J', '8', true));
            // 42. Ace Ten Offsuit
            add(new PokerHandSuitless('A', '0', false));
            // 43. Queen Eight Suited
            add(new PokerHandSuitless('Q', '8', true));
            // 44. King Seven Suited
            add(new PokerHandSuitless('K', '7', true));
            // 45. King Ten Offsuit
            add(new PokerHandSuitless('K', '0', false));
            // 46. Pair of Fives
            add(new PokerHandSuitless('5', '5', false));
            // 47. Jack Ten Offsuit
            add(new PokerHandSuitless('J', '0', false));
            // 48. Seven Eight Suited
            add(new PokerHandSuitless('7', '8', true));
            // 49. Queen Ten Suited
            add(new PokerHandSuitless('Q', '0', true));
            // 50. Pair of Fours
            add(new PokerHandSuitless('4', '4', false));
            // 51. Pair of Threes
            add(new PokerHandSuitless('3', '3', false));
            // 52. Pair of Twos
            add(new PokerHandSuitless('2', '2', false));
            // 53. King Six Suited
            add(new PokerHandSuitless('K', '6', true));
            // 54. Nine Seven Suited
            add(new PokerHandSuitless('9', '7', true));
            // 55. King Five Suited
            add(new PokerHandSuitless('K', '5', true));
            // 56. Seven Six Suited
            add(new PokerHandSuitless('7', '6', true));
            // 57. Ten Seven Suited
            add(new PokerHandSuitless('0', '7', true));
            // 58. King Four Suited
            add(new PokerHandSuitless('K', '4', true));
            // 59. King Three Suited
            add(new PokerHandSuitless('K', '3', true));
            // 60. King Two Suited
            add(new PokerHandSuitless('K', '2', true));
        }};

    public static PokerHandSuitless getHand(int index) {
        return HandRankArray.get(index);
    }
//    public static int getRank(PokerHandSuitless hand) {
//
//    }
}
