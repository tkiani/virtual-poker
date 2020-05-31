package poker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import poker.Hand.HandRank;

class HandTest {

	@Test
	void testIsRoyalFlush() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card aceOfDiamonds = new Card(Suit.DIAMONDS, Rank.ACE);
		Card kingOfDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
		handCards.add(aceOfDiamonds);
		handCards.add(kingOfDiamonds);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card queenOfDiamonds = new Card(Suit.DIAMONDS, Rank.QUEEN);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card fiveOfSpades = new Card(Suit.SPADES, Rank.FIVE);
		communityCards.add(queenOfDiamonds);
		communityCards.add(fiveOfSpades);
		communityCards.add(sevenOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(tenOfDiamonds);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.ROYAL_FLUSH);
	}
	
	@Test
	void testIsStraightFlush() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card kingOfDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
		handCards.add(nineOfDiamonds);
		handCards.add(kingOfDiamonds);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card queenOfDiamonds = new Card(Suit.DIAMONDS, Rank.QUEEN);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card fiveOfSpades = new Card(Suit.SPADES, Rank.FIVE);
		communityCards.add(queenOfDiamonds);
		communityCards.add(fiveOfSpades);
		communityCards.add(sevenOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(tenOfDiamonds);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.STRAIGHT_FLUSH);
	}
	
	@Test
	void testIsFourOfAKind() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card nineOfSpades = new Card(Suit.SPADES, Rank.NINE);
		handCards.add(nineOfDiamonds);
		handCards.add(nineOfSpades);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card queenOfDiamonds = new Card(Suit.DIAMONDS, Rank.QUEEN);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card nineOfHearts = new Card(Suit.HEARTS, Rank.NINE);
		Card nineOfClubs = new Card(Suit.SPADES, Rank.NINE);
		communityCards.add(queenOfDiamonds);
		communityCards.add(nineOfHearts);
		communityCards.add(nineOfClubs);
		communityCards.add(jackOfDiamonds);
		communityCards.add(tenOfDiamonds);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.FOUR_OF_A_KIND);
	}
	
	@Test
	void testIsFullHouse() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card nineOfSpades = new Card(Suit.SPADES, Rank.NINE);
		handCards.add(nineOfDiamonds);
		handCards.add(nineOfSpades);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card queenOfDiamonds = new Card(Suit.DIAMONDS, Rank.QUEEN);
		Card queenOfClubs = new Card(Suit.CLUBS, Rank.QUEEN);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card nineOfHearts = new Card(Suit.HEARTS, Rank.NINE);
		Card sixOfClubs = new Card(Suit.CLUBS, Rank.SIX);
		communityCards.add(queenOfDiamonds);
		communityCards.add(nineOfHearts);
		communityCards.add(sixOfClubs);
		communityCards.add(queenOfClubs);
		communityCards.add(tenOfDiamonds);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.FULL_HOUSE);
	}
	
	@Test
	void testIsFlush() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		handCards.add(nineOfDiamonds);
		handCards.add(jackOfDiamonds);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card queenOfDiamonds = new Card(Suit.DIAMONDS, Rank.QUEEN);
		Card queenOfClubs = new Card(Suit.CLUBS, Rank.QUEEN);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card twoOfHearts = new Card(Suit.DIAMONDS, Rank.TWO);
		Card sixOfClubs = new Card(Suit.CLUBS, Rank.SIX);
		communityCards.add(queenOfDiamonds);
		communityCards.add(queenOfClubs);
		communityCards.add(tenOfDiamonds);
		communityCards.add(twoOfHearts);
		communityCards.add(sixOfClubs);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.FLUSH);
	}
	
	@Test
	void testIsStraight() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card kingOfDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
		handCards.add(nineOfDiamonds);
		handCards.add(kingOfDiamonds);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card queenOfHearts = new Card(Suit.HEARTS, Rank.QUEEN);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card fiveOfSpades = new Card(Suit.SPADES, Rank.FIVE);
		communityCards.add(queenOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(tenOfDiamonds);
		communityCards.add(sevenOfHearts);
		communityCards.add(fiveOfSpades);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.STRAIGHT);
	}
	
	@Test
	void testIsThreeOfAKind() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card kingOfDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
		handCards.add(nineOfDiamonds);
		handCards.add(kingOfDiamonds);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card kingOfHearts = new Card(Suit.HEARTS, Rank.KING);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card kingOfSpades = new Card(Suit.SPADES, Rank.KING);
		communityCards.add(kingOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(tenOfDiamonds);
		communityCards.add(sevenOfHearts);
		communityCards.add(kingOfSpades);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.THREE_OF_A_KIND);
	}
	
	@Test
	void testIsTwoPair() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card kingOfDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
		handCards.add(nineOfDiamonds);
		handCards.add(kingOfDiamonds);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card kingOfHearts = new Card(Suit.HEARTS, Rank.KING);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card jackOfSpades = new Card(Suit.SPADES, Rank.JACK);
		communityCards.add(kingOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(tenOfDiamonds);
		communityCards.add(sevenOfHearts);
		communityCards.add(jackOfSpades);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.TWO_PAIR);
	}
	
	@Test
	void testIsPair() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card kingOfDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
		handCards.add(nineOfDiamonds);
		handCards.add(kingOfDiamonds);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card kingOfHearts = new Card(Suit.HEARTS, Rank.KING);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card aceOfSpades = new Card(Suit.SPADES, Rank.ACE);
		communityCards.add(kingOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(tenOfDiamonds);
		communityCards.add(sevenOfHearts);
		communityCards.add(aceOfSpades);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.PAIR);
	}

	@Test
	void testIsHighCard() {
		Hand h = new Hand();
		ArrayList<Card> handCards = h.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card fourOfDiamonds = new Card(Suit.DIAMONDS, Rank.FOUR);
		handCards.add(nineOfDiamonds);
		handCards.add(fourOfDiamonds);
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card kingOfHearts = new Card(Suit.HEARTS, Rank.KING);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card aceOfSpades = new Card(Suit.SPADES, Rank.ACE);
		communityCards.add(kingOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(tenOfDiamonds);
		communityCards.add(sevenOfHearts);
		communityCards.add(aceOfSpades);
		
		h.determineHighestRank(communityCards);
		
		assertEquals(h.getHighestRank(), HandRank.HIGH_CARD);
	}
	
	@Test
	void testCompareTo() {
		Hand hand = new Hand();
		ArrayList<Card> handCards = hand.getHand();
		Card nineOfDiamonds = new Card(Suit.DIAMONDS, Rank.NINE);
		Card fourOfDiamonds = new Card(Suit.DIAMONDS, Rank.FOUR);
		handCards.add(nineOfDiamonds);
		handCards.add(fourOfDiamonds);
		
		Hand opponentHand = new Hand();
		ArrayList<Card> opponentHandCards = opponentHand.getHand();
		Card kingOfClubs = new Card(Suit.CLUBS, Rank.KING);
		Card kingOfDiamonds = new Card(Suit.DIAMONDS, Rank.KING);
		opponentHandCards.add(kingOfClubs);
		opponentHandCards.add(kingOfDiamonds);
		
		ArrayList<Card> communityCards = new ArrayList<Card>();
		Card kingOfHearts = new Card(Suit.HEARTS, Rank.KING);
		Card jackOfDiamonds = new Card(Suit.DIAMONDS, Rank.JACK);
		Card aceOfDiamonds = new Card(Suit.DIAMONDS, Rank.ACE);
		Card sevenOfHearts = new Card(Suit.HEARTS, Rank.SEVEN);
		Card fourOfSpades = new Card(Suit.SPADES, Rank.FOUR);
		communityCards.add(kingOfHearts);
		communityCards.add(jackOfDiamonds);
		communityCards.add(aceOfDiamonds);
		communityCards.add(sevenOfHearts);
		communityCards.add(fourOfSpades);
		
		hand.determineHighestRank(communityCards);
		opponentHand.determineHighestRank(communityCards);
		
		assertEquals(hand.compareTo(opponentHand, communityCards), -2);
		
		handCards.clear(); 
		Card aceOfSpades = new Card(Suit.SPADES, Rank.ACE);
		Card aceOfClubs = new Card(Suit.CLUBS, Rank.ACE);
		handCards.add(aceOfSpades);
		handCards.add(aceOfClubs);
		
		hand.determineHighestRank(communityCards);

		assertEquals(hand.compareTo(opponentHand, communityCards), 1);
		
		handCards.clear();
		Card jackOfSpades = new Card(Suit.SPADES, Rank.JACK);
		Card sevenOfSpades = new Card(Suit.SPADES, Rank.SEVEN);
		handCards.add(jackOfSpades);
		handCards.add(sevenOfSpades);
		
		assertEquals(hand.compareTo(opponentHand, communityCards), -1);
		
	}

}