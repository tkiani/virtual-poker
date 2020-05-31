package poker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CardSetTest {

	@Test
	void testGetSuit() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		cards.add(card1);
		cards.add(card2);
		
		CardSet cardSet = new CardSet(cards);
		ArrayList<Card> clubs = cardSet.getSuit(Suit.CLUBS);
		
		assertEquals(clubs.get(0), card1);
	}
	
	@Test
	void testGetRank() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		cards.add(card1);
		cards.add(card2);
		
		CardSet cardSet = new CardSet(cards);
		ArrayList<Card> clubs = cardSet.getRank(Rank.EIGHT);
		
		assertEquals(clubs.get(0), card2);
	}

	@Test
	void testRemoveByRank() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		Card card3 = new Card(Suit.DIAMONDS, Rank.ACE);
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		
		CardSet cardSet = new CardSet(cards);
		ArrayList<Card> clubs = cardSet.removeByRank(Rank.ACE, 2);
		
		assertEquals(clubs.get(0), card2);
	}

	@Test
	void shuffleRemove() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.DIAMONDS, Rank.ACE);
		Card card3 = new Card(Suit.HEARTS, Rank.ACE);
		Card card4 = new Card(Suit.SPADES, Rank.FOUR);
		Card card5 = new Card(Suit.CLUBS, Rank.ACE);
		Card card6 = new Card(Suit.DIAMONDS, Rank.ACE);
		Card card7 = new Card(Suit.HEARTS, Rank.ACE);
		Card card8 = new Card(Suit.HEARTS, Rank.FIVE);
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		cards.add(card6);
		cards.add(card7);
		cards.add(card8);
		
		CardSet cardSet = new CardSet(cards);
		ArrayList<Card> clubs = cardSet.removeByRank(Rank.ACE, 2);
		clubs = cardSet.removeByRank(Rank.FOUR, 4);
		assertEquals(clubs.get(0), card1);
	}
	
	@Test
	void AddRank1() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		Card card3 = new Card(Suit.SPADES, Rank.EIGHT);
		Card card4 = new Card(Suit.CLUBS, Rank.EIGHT);
		Card card5 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		Card card6 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		Card card7 = new Card(Suit.DIAMONDS, Rank.NINE);
		Card card8 = new Card(Suit.SPADES, Rank.NINE);
		Card card9 = new Card(Suit.CLUBS, Rank.NINE);
		Card card10 = new Card(Suit.DIAMONDS, Rank.NINE);
		Card card11 = new Card(Suit.DIAMONDS, Rank.NINE);
		
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		cards.add(card6);
		cards.add(card7);
		cards.add(card8);
		cards.add(card9);
		cards.add(card10);
		cards.add(card11);
		
		
		CardSet cardSet = new CardSet(cards);
		ArrayList<Card> clubs = cardSet.getRank(Rank.EIGHT);
		//check to make sure all the rank 8 cards are added to the clubs list
		assertEquals(clubs.size(),5);
		assertEquals(clubs.get(0), card2);
		
	}
	
}
