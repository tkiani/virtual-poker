package poker;

import java.util.ArrayList;

import poker.Hand.HandRank;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, int money) {
		super(name, money);
	}

	public PlayerChoice simulateCheckRaise(boolean isPreflop, ArrayList<Card> communityCards) {
		Hand computerHand = this.getHand();
		computerHand.determineHighestRank(communityCards);
		Rank highCard = computerHand.getHigh();
		HandRank handRank = computerHand.getHighestRank();
		if (isPreflop) {
			if (handRank == HandRank.HIGH_CARD) {
				return PlayerChoice.CHECK;
			} else {
				if (highCard == Rank.THREE || highCard == Rank.FOUR || highCard == Rank.FIVE) {
					return PlayerChoice.CHECK;
				} else {
					if(this.getBet() <= 20) {
						return PlayerChoice.RAISE;
					}
					return PlayerChoice.CHECK;
				}
			}
		} else {
			if (handRank == HandRank.HIGH_CARD) {
				return PlayerChoice.CHECK;
			} else if (handRank == HandRank.PAIR) {
				return PlayerChoice.CHECK;
			} else {
				if(this.getBet() <= 20) {
					return PlayerChoice.RAISE;
				}
				else {
					return PlayerChoice.CHECK;
				}
			}
		}
	}

	public PlayerChoice simulateCallRaiseFold(boolean isPreflop, ArrayList<Card> communityCards) {
		Hand computerHand = this.getHand();
		computerHand.determineHighestRank(communityCards);
		Rank highCard = computerHand.getHigh();
		HandRank handRank = computerHand.getHighestRank();
		if (isPreflop) {
			if (handRank == HandRank.HIGH_CARD) {
				if (highCard == Rank.THREE || highCard == Rank.FOUR || highCard == Rank.FIVE) {
					return PlayerChoice.FOLD;
				}
				else {
					return PlayerChoice.CALL;
				}
			} else {
				if (highCard == Rank.TWO || highCard == Rank.THREE || highCard == Rank.FOUR) {
					return PlayerChoice.CALL;
				} else {
					if(this.getBet() <= 20) {
						return PlayerChoice.RAISE;
					}
					else {
						return PlayerChoice.CHECK;
					}
				}
			}
		} else {
			if (handRank == HandRank.HIGH_CARD) {
				return PlayerChoice.FOLD;
			} else if (handRank == HandRank.PAIR) {
				return PlayerChoice.CALL;
			} else {
				if(this.getBet() <= 20) {
					return PlayerChoice.RAISE;
				}
				else {
					return PlayerChoice.CHECK;
				}
			}
		}
	}
}
