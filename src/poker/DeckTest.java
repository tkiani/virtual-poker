package poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	void testCreateDeckAndDeal() {
		Deck d = new Deck();
		Card c = d.deal();
		assertEquals(c.getSuit(), Suit.SPADES);
		assertEquals(c.getRank(), Rank.ACE);
	}

}