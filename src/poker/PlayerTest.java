package poker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testBet() {
		Player player = new Player("TestPlayer", 10);
		
		int bet = player.bet(20);
		
		assertEquals(bet, 10);
		assertEquals(player.getBet(), 10);
		assertEquals(player.getMoney(), 0);
		assertTrue(player.isAllIn());
	}
	
	@Test
	void testReceiveCard() {
		Player player = new Player("TestPlayer", 10);
		player.setHand(new Hand());
		Card card = new Card(Suit.DIAMONDS, Rank.ACE);
		Hand playerHand = player.getHand();
		ArrayList<Card> playerHandCards = playerHand.getHand();
		
		player.receiveCard(card);
		
		assertEquals(playerHandCards.get(0), card);
		
	}

}
