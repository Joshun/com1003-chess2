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
	private static boolean endGame = false;

	private static void processArgs(String[] args) {
		for(int i=0; i<args.length; i++) {
			if (args[i].equals("--debug")) {
				System.out.println("Debug mode enabled.");
				DebugLog.enable();
			}
			else {
				System.out.println("Invalid parameter " + args[i]);
				System.out.println("Supported flags: --debug");
				System.exit(1);
			}
		}
	}

	public static void gameEnded(Player p) {
		if (p instanceof HumanPlayer) {
			System.out.println(((HumanPlayer) p).getName() + "has won the game!");
		}
		else {
			System.out.println(((ComputerPlayer)p).getName() + "has won the game!");
		}
		System.exit(0);
	}

	public static boolean makePlayerMove(Player player, int startX, int startY, int endX, int endY) {
		if (player instanceof HumanPlayer) {
			HumanPlayer humanPlayer = (HumanPlayer)player;
			if (humanPlayer.makeMove(startX, startY, endX, endY)) {
				gameEnded(humanPlayer);
			}
			if (humanPlayer.getMoveSuccessful()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (player.makeMove()) {
				gameEnded(player);
			}
			return true;
		}
	}

	public static void makeMove(int startX, int startY, int endX, int endY) {
		if (whiteTurn) {
			if (makePlayerMove(whitePlayer, startX, startY, endX, endY)) {
				System.out.println("White\'s move success!");
				if (! (blackPlayer instanceof HumanPlayer)) {
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
				System.out.println("Black\'s move success!");
				if (! (whitePlayer instanceof HumanPlayer)) {
					makePlayerMove(whitePlayer, 0, 0, 0, 0);
				}
				else {
					whiteTurn = true;
				}
				display.showPiecesOnBoard(board.getData());
			}
		}
	}


	public static void main(String[] args)  throws InterruptedException {
		processArgs(args);
		input = new Scanner(System.in);
		board = new Board();
		screen = new TextDisplay();

		whitePieces = new Pieces(board, PieceCode.WHITE);
		blackPieces = new Pieces(board, PieceCode.BLACK);

		whitePlayer = new HumanPlayer("White", whitePieces, board, null, input);
//		blackPlayer = new HumanPlayer("Black", blackPieces, board, null, input);
		blackPlayer = new AggressivePlayer("Black", blackPieces, board, null);

		whitePlayer.setOpponent(blackPlayer);
		blackPlayer.setOpponent(whitePlayer);

		screen.showPiecesOnBoard(board.getData());


		display = new GraphicalDisplay();
		display.showPiecesOnBoard(board.getData());

	}

}
