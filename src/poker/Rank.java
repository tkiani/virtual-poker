package poker;

public enum Rank {
	ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO;
	
	public boolean isOneLower(Rank chosen) {
		for(Rank rank: values()) {
			if(chosen.compareTo(this) == 1) {
				return true;
			}
		}
		return false;
	}
}
