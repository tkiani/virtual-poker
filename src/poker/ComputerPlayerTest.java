package poker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import poker.Player.PlayerChoice;

class ComputerPlayerTest {

	@Test
	void testSimulateCheckRaise() {
		ComputerPlayer computer = new ComputerPlayer("TestComputer", 50);
		computer.setHand(new Hand());
		Card card1 = new Card(Suit.CLUBS, Rank.TWO);
		Card card2 = new Card(Suit.CLUBS, Rank.EIGHT);
		computer.receiveCard(card1);
		computer.receiveCard(card2);
		boolean isPreflop = true;
		ArrayList<Card> communityCards = new ArrayList<Card>();

		PlayerChoice choice = computer.simulateCheckRaise(isPreflop, communityCards);

		assertEquals(choice, PlayerChoice.CHECK);

		Card card3 = new Card(Suit.DIAMONDS, Rank.EIGHT);

		Hand computerHand = computer.getHand();
		ArrayList<Card> handCards = computerHand.getHand();

		handCards.remove(0);
		handCards.add(card3);

		choice = computer.simulateCheckRaise(isPreflop, communityCards);

		assertEquals(choice, PlayerChoice.RAISE);

	}

	@Test
	void testSimulateCallRaiseFold() {
		ComputerPlayer computer = new ComputerPlayer("TestComputer", 50);
		computer.setHand(new Hand());
		Card card1 = new Card(Suit.CLUBS, Rank.TWO);
		Card card2 = new Card(Suit.CLUBS, Rank.EIGHT);
		computer.receiveCard(card1);
		computer.receiveCard(card2);
		boolean isPreflop = false;
		ArrayList<Card> communityCards = new ArrayList<Card>();

		communityCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		communityCards.add(new Card(Suit.SPADES, Rank.FIVE));
		communityCards.add(new Card(Suit.CLUBS, Rank.ACE));

		PlayerChoice choice = computer.simulateCallRaiseFold(isPreflop, communityCards);

		assertEquals(choice, PlayerChoice.CALL);
	}

}
