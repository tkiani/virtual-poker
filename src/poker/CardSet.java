package poker;

import java.util.ArrayList;

public class CardSet {
	private ArrayList<Card> cards = new ArrayList<Card>();

	public CardSet(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Card> getCardSet(){
		return cards;
	}
	
	public ArrayList<Card> getSuit(Suit suit) {
		ArrayList<Card> suitedCards = new ArrayList<Card>();
		for (Card card : this.cards) {
			if (card.getSuit() == suit) {
				suitedCards.add(card);
			}
		}
		return suitedCards;
	}

	public ArrayList<Card> getRank(Rank rank) {
		ArrayList<Card> rankedCards = new ArrayList<Card>();
		for (Card card : this.cards) {
			if (card.getRank() == rank) {
				rankedCards.add(card);
			}
		}
		return rankedCards;
	}

	public ArrayList<Card> removeByRank(Rank rank, int count) {
		int remaining = count;
		ArrayList<Card> unrankedCards = new ArrayList<Card>();
		for (Card card : this.cards) {
			if (card.getRank().equals(rank) && remaining > 0) {
				remaining--;
			} else {
				unrankedCards.add(card);
			}
		}
		return unrankedCards;
	}
}