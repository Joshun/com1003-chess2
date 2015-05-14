package uk.ac.sheffield.aca14jmo;

import java.util.Scanner;

/**
 * uk.ac.sheffield.aca14jmo.Chess.java
 *
 * Class to control the chess game's core functionality
 */

public class Chess {
	private static Scanner input;
	private static Board board;
	private static TextDisplay screen;
	private static Player whitePlayer;
	private static Player blackPlayer;
	private static Pieces whitePieces;
	private static Pieces blackPieces;
	private static GraphicalDisplay display;
	private static boolean whiteTurn = true;
	private static boolean computerVComputer = false;
	private static boolean whiteIsComputer = false;

	private static void processArgs(String[] args) {
		for(String argument: args) {
			if (argument.equals("--debug") || argument.equals("-d")) {
				System.out.println("Debug mode enabled.");
				DebugLog.enable();
			}
			else {
				System.out.println("Invalid parameter " + argument);
				System.out.println("Supported flags: --debug -d");
				System.exit(1);
			}
		}
	}

	public static Player createPlayer(GameChoice playerChoice, int colour, String name) {
		Player player;
		Pieces playerPieces = new Pieces(board, colour);
		switch (playerChoice) {
			case HUMAN:
				player = new HumanPlayer(name, playerPieces, board, null, input);
				break;
			case AGGRESSIVE_COMPUTER:
				player = new AggressivePlayer(name, playerPieces, board, null);
				break;
			case RANDOM_COMPUTER:
				player = new RandomPlayer(name, playerPieces, board, null);
				break;
			default:
				player = null;
		}
		return player;
	}

	public static void startGame(GameChoice player1Choice, GameChoice player2Choice, int computerDelay) {

		whitePlayer = createPlayer(player1Choice, PieceCode.WHITE, "Player 1");
		blackPlayer = createPlayer(player2Choice, PieceCode.BLACK, "Player 2");
		whitePlayer.setOpponent(blackPlayer);
		blackPlayer.setOpponent(whitePlayer);
		DebugLog.println("created white player with name " + whitePlayer.getName());
		DebugLog.println("created black player with name " + blackPlayer.getName());


		if (whitePlayer instanceof ComputerPlayer) {
			if (blackPlayer instanceof ComputerPlayer) {
				computerVComputer = true;
			}
			else {
				whiteIsComputer = true;
			}
		}

		display = new GraphicalDisplay(whitePlayer.getName(), computerDelay);
		display.showPiecesOnBoard(board.getData());
		if (whiteIsComputer) {
			makeMove(0, 0, 0, 0);
		}
		togglePlayerText(whitePlayer);
	}

	public static void gameEnded(Player p) {
		String msg;
		msg = p.getName() + " has won the game!";
		DebugLog.println(msg);
		display.stopComputerMovement();
		new Dialog(msg, true);
	}

	private static void togglePlayerText(Player player) {
		Player opp = player.getOpponent();
		if (display != null ) display.setStatusText(opp.getName() + "\'s move.");
	}

	public static boolean makePlayerMove(Player player, int startX, int startY, int endX, int endY) {
		Player opp = player.getOpponent();
		if (player instanceof HumanPlayer) {
			DebugLog.println("x: " + startX + " y: " + startY + " x: " + endX + " y: " + endY);
			HumanPlayer humanPlayer = (HumanPlayer)player;
			if (humanPlayer.makeMove(startX, startY, endX, endY)) {
				gameEnded(humanPlayer);
			}
			if (humanPlayer.getMoveSuccessful()) {
				togglePlayerText(player);

				return true;
			}
			else {
				display.setStatusText("Invalid move." + player.getName() + "\'s move.");
				display.resetMarkings();
				return false;
			}
		}
		else {
			if (player.makeMove()) {
				gameEnded(player);
			}
			togglePlayerText(player);
			return true;
		}
	}

	public static void nextComputerMove() {
		makeMove(0, 0, 0, 0);
	}

	public static void makeMove(int startX, int startY, int endX, int endY) {
		if (whiteTurn) {
			if (makePlayerMove(whitePlayer, startX, startY, endX, endY)) {
				DebugLog.println("White\'s move success!");
				if (! (blackPlayer instanceof HumanPlayer) && !computerVComputer) {
					makePlayerMove(blackPlayer, 0, 0, 0, 0);
				}
				else {
					whiteTurn = false;
				}
			}
			display.showPiecesOnBoard(board.getData());
		}
		else {
			if (makePlayerMove(blackPlayer, startX, startY, endX, endY)) {
				DebugLog.println("Black\'s move success!");
				if (! (whitePlayer instanceof HumanPlayer) && ! computerVComputer) {
					makePlayerMove(whitePlayer, 0, 0, 0, 0);
				}
				else {
					whiteTurn = true;
				}
				display.showPiecesOnBoard(board.getData());
			}
		}
	}

	public static boolean getComputerVComputer() {
		return computerVComputer;
	}

	public static void main(String[] args)  throws InterruptedException {
		processArgs(args);
		input = new Scanner(System.in);
		board = new Board();
		screen = new TextDisplay();

		whitePieces = new Pieces(board, PieceCode.WHITE);
		blackPieces = new Pieces(board, PieceCode.BLACK);

		InitialMenu im = new InitialMenu();

	}

}
