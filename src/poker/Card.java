package poker;

public class Card implements Comparable<Card>{
	
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}
	
	public @Override String toString() {
		return rank + " of " + suit;
				}

	@Override
	public int compareTo(Card o) {
		return(rank.compareTo(o.getRank()));
	}
	
	public String imageToString() {
		return "card=" + this;
	}
}

