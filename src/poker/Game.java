package poker;

import java.util.ArrayList;

import gui.PokerTable;

public class Game {

	public int smallBlind = 5;
	public int startingMoney = 100;

	Player player = new Player("Player", startingMoney);
	ComputerPlayer computer = new ComputerPlayer("Computer", startingMoney);
	ArrayList<Player> players = new ArrayList<Player>();

	Hand playerHand = player.getHand();
	Hand computerHand = computer.getHand();

	boolean isPlayerTurn = true;
	ArrayList<Card> communityCards = new ArrayList<Card>();
	ArrayList<Card> playerHandArray = new ArrayList<Card>();
	ArrayList<Card> computerHandArray = new ArrayList<Card>();

	boolean isPreflop = true;

	boolean playerActed = false;
	boolean computerActed = false;

	boolean isShowdown = false;

	int maxBet = getMaxBet();

	Player winner = null;

	Deck deck = new Deck();

	public Game(Player player, ComputerPlayer computer) {
		this.player = player;
		this.computer = computer;
	}

	public Game(int smallBlind, int startingMoney, ArrayList<Player> players) {
		this.smallBlind = smallBlind;
		this.startingMoney = startingMoney;
		this.players = players;
	}

	public void startGame() {
		players.add(player);
		players.add(computer);
		player.setHand(new Hand());
		computer.setHand(new Hand());
	}

	public int getSmallBlind() {
		return smallBlind;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void playRound() {
		player.resetBet();
		computer.resetBet();
		PokerTable.displayMessage(player.getName() + " has " + player.getMoney() + ".");
		PokerTable.updateCurrentMessage(player.getName() + " has " + player.getMoney() + ".");

		PokerTable.displayMessage(computer.getName() + " has " + computer.getMoney() + ".");
		PokerTable.updateCurrentMessage(computer.getName() + " has " + computer.getMoney() + ".");

		isShowdown = false;
	}

	public boolean isWinner() {
		if (winner == null) {
			return false;
		}
		return true;
	}

	public void doBlinds() {
		if (isPlayerTurn) {
			doSmallBlind(player);
			doBigBlind(computer);
		} else {
			doSmallBlind(computer);
			doBigBlind(player);
		}
	}

	public int doSmallBlind(Player player) {
		player.bet(smallBlind);
		PokerTable.displayMessage(player.getName() + " is the small blind and bets " + smallBlind + ".");
		PokerTable.updateCurrentMessage(player.getName() + " is the small blind and bets " + smallBlind + ".");
		return smallBlind;
	}

	public int doBigBlind(Player player) {
		int bigBlind = smallBlind * 2;
		player.bet(bigBlind);
		PokerTable.displayMessage(player.getName() + " is the big blind and bets " + bigBlind + ".");
		PokerTable.updateCurrentMessage(player.getName() + " is the big blind and bets " + bigBlind + ".");
		return bigBlind;
	}

	public ArrayList<Card> playPreflop() {
		isPreflop = true;
		deck.shuffle();
		dealPlayerCards();
		Hand playerHand = player.getHand();
		playerHandArray = playerHand.getHand();
		return playerHandArray;
	}

	public ArrayList<Card> playerHandArray() {
		playerHandArray = playerHand.getHand();
		return playerHandArray;
	}

	public void dealPlayerCards() {
		player.setHand(new Hand());
		computer.setHand(new Hand());
		player.receiveCard(deck.deal());
		computer.receiveCard(deck.deal());
		player.receiveCard(deck.deal());
		computer.receiveCard(deck.deal());
	}

	public ArrayList<Card> playFlop() {
		isPreflop = false;
		dealFlop();
		return communityCards;
	}

	public void dealFlop() {
		deck.deal();
		deck.deal();
		deck.deal();
		communityCards.clear();
		communityCards.add(deck.deal());
		communityCards.add(deck.deal());
		communityCards.add(deck.deal());
	}

	public ArrayList<Card> playTurn() {
		dealTurn();
		return communityCards;
	}

	public void dealTurn() {
		deck.deal();
		communityCards.add(deck.deal());
	}

	public ArrayList<Card> playRiver() {
		dealRiver();
		return communityCards;
	}

	public void dealRiver() {
		deck.deal();
		communityCards.add(deck.deal());
	}

	public boolean isPreflop() {
		if (isPreflop == true) {
			return true;
		}
		return false;
	}

	public void playBettingRound(boolean isPreflop) {
		maxBet = getMaxBet();
		do {
			playerActed = false;
			computerActed = false;
			playerBettingRound();
			if (winner == null && !(computerActed && playerActed)) {
				computerBettingRound();
			}

		} while (((!playerHasBet(player, maxBet) || !playerHasBet(computer, maxBet)) || !(computerActed && playerActed))
				&& (winner == null) && !player.isAllIn() && !computer.isAllIn());
		PokerTable.displayMessage("Current Pot: " + this.getCurrentPot());
	}

	public void playerBettingRound() {
		if (!player.isAllIn()) {
			if (playerHasBet(player, maxBet)) {
				PokerTable.updateCurrentMessage("Your turn! Check or raise.");
				switch (player.checkRaise()) {
				case CHECK:
					playerActed = true;
					PokerTable.displayMessage(player.getName() + " checks.");
					PokerTable.updateCurrentMessage(player.getName() + " checks.");
					break;
				case RAISE:
					playerActed = true;
					computerActed = false;
					int raiseAmount = player.bet(determineRaiseAmount(askPlayerForBet(player)));
					maxBet += raiseAmount;
					PokerTable.displayMessage(player.getName() + " raises " + raiseAmount + ".");
					PokerTable.updateCurrentMessage(player.getName() + " raises " + raiseAmount + ".");
					break;
				}
			} else {
				PokerTable.updateCurrentMessage("Your turn! Call, raise, or fold.");
				switch (player.callRaiseFold()) {
				case CALL:
					playerActed = true;
					computerActed = true;
					player.bet(maxBet - player.getBet());
					PokerTable.displayMessage(player.getName() + " calls.");
					PokerTable.updateCurrentMessage(player.getName() + " calls.");
					break;
				case RAISE:
					playerActed = true;
					computerActed = false;
					int discrepancy = 0;
					if (player.getBet() > computer.getBet()) {
						discrepancy = player.getBet() - computer.getBet();
					} else if (player.getBet() < computer.getBet()) {
						discrepancy = computer.getBet() - player.getBet();
					}
					int raiseAmount = player.bet(determineRaiseAmount(askPlayerForBet(player)));
					maxBet += raiseAmount - discrepancy;
					PokerTable.displayMessage(player.getName() + " raises " + raiseAmount + ".");
					PokerTable.updateCurrentMessage(player.getName() + " raises " + raiseAmount + ".");
					break;
				case FOLD:
					PokerTable.displayMessage(player.getName() + " folds.");
					PokerTable.updateCurrentMessage(player.getName() + " folds.");
					winner = computer;
					break;

				}
			}
		}
	}

	/**
	 * Initialize computer betting
	 */
	public void computerBettingRound() {
		if (!computer.isAllIn()) {
			if (playerHasBet(computer, maxBet)) {
				switch (computer.simulateCheckRaise(isPreflop, communityCards)) {
				case CHECK:
					computerActed = true;
					PokerTable.displayMessage(computer.getName() + " checks.");
					PokerTable.updateCurrentMessage(computer.getName() + " checks.");
					break;
				case RAISE:
					computerActed = true;
					playerActed = false;
					int raiseAmount = computer.bet(smallBlind * 2);
					maxBet += raiseAmount;
					PokerTable.displayMessage(computer.getName() + " raises " + raiseAmount + ".");
					PokerTable.updateCurrentMessage(computer.getName() + " raises " + raiseAmount + ".");
					break;
				}
			} else {
				switch (computer.simulateCallRaiseFold(isPreflop, communityCards)) {
				case CALL:
					computerActed = true;
					playerActed = true;
					computer.bet(maxBet - computer.getBet());
					PokerTable.displayMessage(computer.getName() + " calls.");
					PokerTable.updateCurrentMessage(computer.getName() + " calls.");
					break;
				case RAISE:
					computerActed = true;
					playerActed = false;
					int raiseAmount = computer.bet(maxBet - computer.getBet() + smallBlind * 2);
					maxBet += raiseAmount;
					if (player.getMoney() == 0) {
						PokerTable.displayMessage(computer.getName() + " calls.");
						PokerTable.updateCurrentMessage(computer.getName() + " calls.");
					} else {
						PokerTable.displayMessage(computer.getName() + " raises " + raiseAmount + ".");
						PokerTable.updateCurrentMessage(computer.getName() + " raises " + raiseAmount + ".");
					}
					break;
				case FOLD:
					PokerTable.displayMessage(computer.getName() + " folds.");
					PokerTable.updateCurrentMessage(computer.getName() + " folds.");
					winner = player;
					break;

				}
			}
		}
	}

	public int determineRaiseAmount(int userInput) {
		int discrepancy = 0;
		discrepancy = computer.getBet() - player.getBet();

		if (userInput > computer.getMoney()) {
			return computer.getMoney() + discrepancy;
		}
		return userInput;
	}

	public void playShowdown() {
		Hand playerHand = player.getHand();
		Hand computerHand = computer.getHand();
		playerHand.determineHighestRank(communityCards);
		computerHand.determineHighestRank(communityCards);
		PokerTable.displayMessage(player.getName() + ": " + player.getHand().getHighestRank());
		PokerTable.updateCurrentMessage(player.getName() + ": " + player.getHand().getHighestRank());
		PokerTable.displayMessage(computer.getName() + ": " + computer.getHand().getHighestRank());
		PokerTable.updateCurrentMessage(computer.getName() + ": " + computer.getHand().getHighestRank());
		int comparison = playerHand.compareTo(computerHand, communityCards);
		if (comparison > 0) {
			winner = player;
		} else if (comparison < 0) {
			winner = computer;
		}
		isShowdown = true;
	}

	public int getMaxBet() {
		int maxBet = 0;

		if (player.getBet() > maxBet) {
			maxBet = player.getBet();
		}
		if (computer.getBet() > maxBet) {
			maxBet = computer.getBet();
		}
		return maxBet;
	}

	public boolean playerHasBet(Player player, int maxBet) {
		if (player.getBet() < maxBet) {
			return false;
		}
		return true;
	}

	public int getCurrentPot() {
		int pot = 0;

		pot += player.getBet();
		pot += computer.getBet();

		return pot;
	}

	public int askPlayerForBet(Player player) {
		int betAmount = PokerTable.getUserInput("How much would you like to bet?");
		return betAmount;
	}

	public void doRoundWinner() {
		if (winner == player) {
			player.setMoney(player.getMoney() + this.getCurrentPot());
		} else if (winner == computer) {
			computer.setMoney(computer.getMoney() + this.getCurrentPot());
		} else {
			player.setMoney(player.getMoney() + player.getBet());
			computer.setMoney(computer.getMoney() + computer.getBet());
		}
		if (winner != null) {
			if (isShowdown) {
				PokerTable.displayMessage(winner.getName() + " wins the hand with a "
						+ winner.getHand().getHighestRank() + " and the pot of " + this.getCurrentPot() + "!");
				PokerTable.updateCurrentMessage(winner.getName() + " wins the hand with a "
						+ winner.getHand().getHighestRank() + " and the pot of " + this.getCurrentPot() + "!");
			} else {
				PokerTable.displayMessage(
						winner.getName() + " wins the hand and the pot of " + this.getCurrentPot() + "!");
				PokerTable.updateCurrentMessage(
						winner.getName() + " wins the hand and the pot of " + this.getCurrentPot() + "!");

			}
		} else {
			PokerTable.displayMessage("Split hand! The pot was split.");
			PokerTable.updateCurrentMessage("Split hand! The pot was split.");
		}
		winner = null;

	}

	public boolean checkForWinner() {
		if (player.getMoney() == 0) {
			PokerTable.displayMessage("The computer wins the game!");
			PokerTable.updateCurrentMessage("The computer wins the game!");
			return true;
		} else if (computer.getMoney() == 0) {
			PokerTable.displayMessage("You win the game!");
			PokerTable.updateCurrentMessage("You win the game!");
			return true;
		}
		return false;
	}
}
