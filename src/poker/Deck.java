package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();

	/**
	 * Create a deck of cards with 4 Suits and 13 Ranks for each suit.
	 */
	public Deck() {
		cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(suit, rank);
				cards.add(card);
			}
		}
	}

	
	/**
	 * Shuffle the deck of cards randomly before passing the cards to players
	 */
	public void shuffle() {
		cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(suit, rank);
				cards.add(card);
			}
		}

		//Shuffle the collection(array List) of cards
		Collections.shuffle(this.cards);
	}

	/**
	 * Passes the card to player
	 * @return Card that is being passed
	 */
	public Card deal() {
		Card topCard = this.cards.get(0);
		this.cards.remove(0);
		return topCard;
	}
}