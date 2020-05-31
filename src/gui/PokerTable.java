package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import poker.Card;
import poker.ComputerPlayer;
import poker.Game;
import poker.Player;

public class PokerTable {

	private static JFrame frame;
	private static Game pokerGame;

	static ArrayList<Card> communityCards = new ArrayList<Card>();
	static ArrayList<Card> playerHandArray = new ArrayList<Card>();
	static ArrayList<Card> computerHandArray = new ArrayList<Card>();

	static int playersMoney = 0;
	static int computersMoney = 0;

	static boolean isShowdown = false;
	static boolean fold = false;
	static boolean call = false;
	static boolean raise = false;

	static int potAmount = 0;

	static String playerName;

	static JPanel panel = new JPanel();

	static JButton checkButton = new JButton("Check");
	static JButton callButton = new JButton("Call");
	static JButton raiseButton = new JButton("Raise");
	static JButton foldButton = new JButton("Fold");

	static int[] choices = new int[1];

	static ActionListener callButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			choices[0] = 1;
		}
	};

	static ActionListener raiseButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			choices[0] = 2;
		}
	};

	static ActionListener foldButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			choices[0] = 3;
		}
	};

	static Player player;
	static ComputerPlayer computer;

	static DefaultListModel events = new DefaultListModel();
	static JList list = new JList(events);

	static String currentMessage = "";

	/**
	 * Launch the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {

		playerName = (String) JOptionPane.showInputDialog(frame, "Input Player Name:", "Input Name",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		int startingMoney = Integer.parseInt(JOptionPane.showInputDialog("Enter a starting amount of money:"));

		player = new Player(playerName, startingMoney);
		computer = new ComputerPlayer("Computer", startingMoney);

		pokerGame = new Game(player, computer);

		generateFrame(playerName);
		
		playersMoney = player.getMoney();
		computersMoney = computer.getMoney();

		generateFrame(playerName);

		pokerGame.startGame();

		checkForWinner();

	}

	/**
	 * run the game until there is a winner
	 */
	private static void checkForWinner() {
		while (!pokerGame.checkForWinner()) {

			isShowdown = false;
			events.clear();
			events.add(0, "Round: New Round!");
			displayMessage("Round: New Round!");

			communityCards = new ArrayList<Card>();
			
			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();

			pokerGame.playRound();
			pokerGame.doBlinds();

			playerHandArray = pokerGame.playPreflop();

			generateFrame(playerName);

			pokerGame.playBettingRound(pokerGame.isPreflop());

			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();

			roundFlop();

			roundTurn();

			roundRiver();

			roundShowdown();
			
			pokerGame.doRoundWinner();

			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();
			
			

		}
	}

	/**
	 * Initialize game flop round
	 */
	private static void roundFlop() {
		if (!pokerGame.isWinner()) {

			if (events.size() == 3) {
				events.remove(0);
			}
			events.add(events.size(), "Round: FLOP!");
			displayMessage("Round: FLOP!");

			communityCards = pokerGame.playFlop();
			// display the community cards
			generateFrame(playerName);

			pokerGame.playBettingRound(pokerGame.isPreflop());

			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();
		}
	}

	private static void roundTurn() {
		if (!pokerGame.isWinner()) {
			// Play Turn
			if (events.size() == 3) {
				events.remove(0);
			}
			events.add(events.size(), "Round: TURN!");
			displayMessage("Round: TURN!");
			communityCards = pokerGame.playTurn();

			// display the community cards
			generateFrame(playerName);

			// call playBetting Round in TURN
			pokerGame.playBettingRound(pokerGame.isPreflop());

			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();
		}
	}

	/**
	 * play river
	 */
	private static void roundRiver() {
		if (!pokerGame.isWinner()) {
			// Play River
			if (events.size() == 3) {
				events.remove(0);
			}
			events.add(events.size(), "Round: RIVER!");
			displayMessage("Round: RIVER!");

			communityCards = pokerGame.playRiver();

			// display the community cards
			generateFrame(playerName);

			pokerGame.playBettingRound(pokerGame.isPreflop());

			playersMoney = player.getMoney();
			computersMoney = computer.getMoney();

		}
	}

	/**
	 * play showdown round
	 */
	private static void roundShowdown() {
		if (!pokerGame.isWinner()) {
			// play showdown
			if (events.size() == 3) {
				events.remove(0);
			}
			events.add(events.size(), "Round: SHOWDOWN!");
			isShowdown = true;
			displayMessage("Round: SHOWDOWN!");
			computerHandArray = computer.getHand().getHand();
			generateFrame(playerName);
			pokerGame.playShowdown();
		}
	}

	/**
	 * Takes current message into history.
	 * 
	 * @param message
	 */
	public static void updateCurrentMessage(String message) {
		if (events.getSize() == 3) {
			events.remove(0);
		}
		events.add(events.getSize(), message);

		currentMessage = message;
	}

	public static void generateFrame(String playerName) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerTable window = new PokerTable(playerName);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PokerTable(String playerName) {
		initialize(playerName);
	}

	public static void displayMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	public static int getUserInput(String message) {
		int playerStrategy = Integer.parseInt(JOptionPane.showInputDialog(message));
		return playerStrategy;
	}

	public static int getUserInputCheckRaise() {
		choices[0] = 0;
		callButton.removeActionListener(callButtonListener);
		foldButton.removeActionListener(foldButtonListener);
		checkButton.addActionListener(callButtonListener);
		raiseButton.addActionListener(raiseButtonListener);
		return choices[0];
	}

	public static int getUserInputCallRaiseFold() {
		choices[0] = 0;
		checkButton.removeActionListener(callButtonListener);
		callButton.addActionListener(callButtonListener);
		raiseButton.addActionListener(raiseButtonListener);
		foldButton.addActionListener(foldButtonListener);
		return choices[0];
	}

	public static String getPlayerName() {
		return playerName;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String playerName) {

		String playersFirstCard = "backOfCard";
		String playersSecondCard = "backOfCard";

		String computerFirstCard = "backOfCard";
		String computerSecondCard = "backOfCard";

		String communityCard1 = "";
		String communityCard2 = "";
		String communityCard3 = "";
		String communityCard4 = "";
		String communityCard5 = "";

		playersMoney = player.getMoney();
		computersMoney = computer.getMoney();
		potAmount = pokerGame.getCurrentPot();

		frame = new JFrame();
		frame.setBounds(100, 100, 957, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(4, 1, 0, 0));

		panel.add(checkButton);
		panel.add(callButton);
		panel.add(raiseButton);
		panel.add(foldButton);

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);

		list.setBounds(51, 621, 740, 59);
		layeredPane.add(list);

		JLabel lblNewLabel_222 = new JLabel("");
		lblNewLabel_222.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		lblNewLabel_222.setBounds(51, 443, 289, 167);
		layeredPane.add(lblNewLabel_222);

		JLabel titleLabel = new JLabel("VIRTUAL POKER");
		titleLabel.setForeground(Color.GRAY);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
		titleLabel.setBounds(273, 21, 295, 40);
		layeredPane.add(titleLabel);

		JLabel playerMoneyLabel = new JLabel("Player Money");
		playerMoneyLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		playerMoneyLabel.setBounds(212, 455, 95, 14);
		layeredPane.add(playerMoneyLabel);

		JLabel playerMoneyValue = new JLabel(String.valueOf(playersMoney));
		playerMoneyValue.setBounds(232, 486, 46, 14);
		layeredPane.add(playerMoneyValue);

		JLabel lblNewLabel_32 = new JLabel("Player Name");
		lblNewLabel_32.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_32.setBounds(76, 455, 88, 14);
		layeredPane.add(lblNewLabel_32);

		JLabel lblNewLabel_4 = new JLabel(playerName);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(76, 486, 75, 14);
		layeredPane.add(lblNewLabel_4);

		JLabel cardBox = new JLabel("");
		cardBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		cardBox.setBounds(501, 443, 289, 167);
		layeredPane.add(cardBox);

		JLabel computerMoneyLabel = new JLabel("Computer Money");
		computerMoneyLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		computerMoneyLabel.setBounds(650, 455, 125, 14);
		layeredPane.add(computerMoneyLabel);

		JLabel computerMoneyValue = new JLabel(String.valueOf(computersMoney));
		computerMoneyValue.setBounds(692, 486, 46, 14);
		layeredPane.add(computerMoneyValue);

		JLabel computerNameLabel = new JLabel("Computer Name");
		computerNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		computerNameLabel.setBounds(526, 455, 110, 14);
		layeredPane.add(computerNameLabel);

		JLabel computerName = new JLabel("Computer");
		computerName.setHorizontalAlignment(SwingConstants.CENTER);
		computerName.setBounds(536, 486, 75, 14);
		layeredPane.add(computerName);

		if (!isShowdown) {
			JLabel computerFirstCardLabel = new JLabel("ComputerCard1");
			computerFirstCardLabel.setBounds(526, 511, 75, 98);
			computerFirstCardLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
			Image computerFirstCardImage = new ImageIcon(this.getClass().getResource("/" + computerFirstCard + ".jpg"))
					.getImage();
			computerFirstCardLabel.setIcon(new ImageIcon(computerFirstCardImage));
			layeredPane.add(computerFirstCardLabel);

			JLabel computerSecondCardLabel = new JLabel("ComputerCard2");
			computerSecondCardLabel.setBounds(670, 511, 75, 98);
			computerSecondCardLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
			Image computerSecondCardImage = new ImageIcon(
					this.getClass().getResource("/" + computerSecondCard + ".jpg")).getImage();
			computerSecondCardLabel.setIcon(new ImageIcon(computerSecondCardImage));
			layeredPane.add(computerSecondCardLabel);
		}

		if (isShowdown) {

			computerFirstCard = computerHandArray.get(0).imageToString();
			computerSecondCard = computerHandArray.get(1).imageToString();

			JLabel computerFirstCardLabel = new JLabel("ComputerCard1");
			computerFirstCardLabel.setBounds(526, 511, 75, 98);
			Image imgCard46 = new ImageIcon(this.getClass().getResource("/" + computerFirstCard + ".png")).getImage();
			computerFirstCardLabel.setIcon(new ImageIcon(imgCard46));
			layeredPane.add(computerFirstCardLabel);

			JLabel computerSecondCardLabel = new JLabel("ComputerCard2");
			computerSecondCardLabel.setBounds(670, 511, 75, 98);
			Image imgCard56 = new ImageIcon(this.getClass().getResource("/" + computerSecondCard + ".png")).getImage();
			computerSecondCardLabel.setIcon(new ImageIcon(imgCard56));
			layeredPane.add(computerSecondCardLabel);
		}

		if (playerHandArray.size() == 2) {
			playersFirstCard = playerHandArray.get(0).imageToString();
			playersSecondCard = playerHandArray.get(1).imageToString();

			JLabel playerFirstCardLabel = new JLabel("PlayerCard1");
			playerFirstCardLabel.setBounds(75, 511, 75, 98);
			Image imgCard4 = new ImageIcon(this.getClass().getResource("/" + playersFirstCard + ".png")).getImage();
			playerFirstCardLabel.setIcon(new ImageIcon(imgCard4));
			layeredPane.add(playerFirstCardLabel);

			JLabel playerSecondCardLabel = new JLabel("PlayerCard2");
			playerSecondCardLabel.setBounds(212, 511, 75, 98);
			Image imgCard5 = new ImageIcon(this.getClass().getResource("/" + playersSecondCard + ".png")).getImage();
			playerSecondCardLabel.setIcon(new ImageIcon(imgCard5));
			layeredPane.add(playerSecondCardLabel);
		}

		if (communityCards.size() != 0) {

			communityCard1 = communityCards.get(0).imageToString();
			communityCard2 = communityCards.get(1).imageToString();
			communityCard3 = communityCards.get(2).imageToString();

			JLabel communityFirstCardLabel = new JLabel("CommunityCard1");
			communityFirstCardLabel.setBounds(197, 226, 75, 98);
			Image communityFirstCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard1 + ".png"))
					.getImage();
			communityFirstCardLabel.setIcon(new ImageIcon(communityFirstCardImage));
			layeredPane.add(communityFirstCardLabel);

			JLabel communitySecondCardLabel = new JLabel("CommunityCard2");
			communitySecondCardLabel.setBounds(292, 226, 75, 98);
			Image communitySecondCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard2 + ".png"))
					.getImage();
			communitySecondCardLabel.setIcon(new ImageIcon(communitySecondCardImage));
			layeredPane.add(communitySecondCardLabel);

			JLabel communityThirdCardLabel = new JLabel("CommunityCard3");
			communityThirdCardLabel.setBounds(385, 226, 75, 98);
			Image communityThirdCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard3 + ".png"))
					.getImage();
			communityThirdCardLabel.setIcon(new ImageIcon(communityThirdCardImage));
			layeredPane.add(communityThirdCardLabel);
		}

		if (communityCards.size() >= 4) {

			communityCard4 = communityCards.get(3).imageToString();

			JLabel communityFourthCardLabel = new JLabel("");
			communityFourthCardLabel.setBounds(481, 226, 75, 98);
			Image communityFourthCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard4 + ".png"))
					.getImage();
			communityFourthCardLabel.setIcon(new ImageIcon(communityFourthCardImage));
			layeredPane.add(communityFourthCardLabel);
		}

		if (communityCards.size() == 5) {

			communityCard5 = communityCards.get(4).imageToString();

			JLabel communityFifthCardLabel = new JLabel("");
			communityFifthCardLabel.setBounds(584, 226, 75, 98);
			Image communityFifthCardImage = new ImageIcon(this.getClass().getResource("/" + communityCard5 + ".png"))
					.getImage();
			communityFifthCardLabel.setIcon(new ImageIcon(communityFifthCardImage));
			layeredPane.add(communityFifthCardLabel);

		}

		JLabel pokerTableLabel = new JLabel("");
		pokerTableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokerTableLabel.setBounds(51, 107, 731, 352);
		Image pokerTableImage = new ImageIcon(this.getClass().getResource("/poker_table.png")).getImage();
		pokerTableLabel.setIcon(new ImageIcon(pokerTableImage));
		layeredPane.add(pokerTableLabel);

		JLabel potBorder = new JLabel("");
		potBorder.setBackground(Color.LIGHT_GRAY);
		potBorder.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		potBorder.setBounds(375, 443, 83, 71);
		layeredPane.add(potBorder);

		JLabel potLabel = new JLabel("POT");
		potLabel.setForeground(Color.RED);
		potLabel.setHorizontalAlignment(SwingConstants.CENTER);
		potLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		potLabel.setFont(new Font("Ti28s New Roman", Font.BOLD, 15));
		potLabel.setBounds(393, 455, 46, 14);
		layeredPane.add(potLabel);

		JLabel potAmountLabel = new JLabel(String.valueOf(potAmount));
		potAmountLabel.setForeground(Color.RED);
		potAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		potAmountLabel.setBounds(403, 486, 26, 14);
		layeredPane.add(potAmountLabel);
	}

}
