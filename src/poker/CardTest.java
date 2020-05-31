package poker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void testCreateCard() {
		Card c = new Card(Suit.HEARTS, Rank.TWO);
		assertEquals(c.getSuit(), Suit.HEARTS);
		assertEquals(c.getRank(), Rank.TWO);
		assertEquals(c.toString(), "TWO of HEARTS");
	}

	@Test
	void testCreateCardArrayList() {
		Card c = new Card(Suit.HEARTS, Rank.FOUR);
		Card c1 = new Card(Suit.DIAMONDS, Rank.FOUR);
		Card c2 = new Card(Suit.SPADES, Rank.FOUR);
		Card c3 = new Card(Suit.CLUBS, Rank.FOUR);
		Card c4 = new Card(Suit.HEARTS, Rank.FIVE);
		Card c5 = new Card(Suit.HEARTS, Rank.EIGHT);
		Card c6 = new Card(Suit.HEARTS, Rank.FOUR);
		assertEquals(c.getSuit(), Suit.HEARTS);
		assertEquals(c.getRank(), Rank.FOUR);

		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(c);
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		cards.add(c5);
		cards.add(c6);
		for (Card check : cards) {
			assertEquals(check.toString(), check.getRank() + " of " + check.getSuit());
		}

	}
}
