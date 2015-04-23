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
	private static RandomPlayer whitePlayer;
	private static HumanPlayer blackPlayer;
	private static Pieces whitePieces;
	private static Pieces blackPieces;

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

	public static void main(String[] args) {
		processArgs(args);
		input = new Scanner(System.in);
		board = new Board();
		screen = new TextDisplay();

		whitePieces = new Pieces(board, PieceCode.WHITE);
		blackPieces = new Pieces(board, PieceCode.BLACK);

		whitePlayer = new RandomPlayer("White", whitePieces, board, null);
		blackPlayer = new HumanPlayer("Black", blackPieces, board, null, input);

		whitePlayer.setOpponent(blackPlayer);
		blackPlayer.setOpponent(whitePlayer);

		screen.showPiecesOnBoard(board.getData());

		while (true) {
			if (whitePlayer.makeMove()) {
				System.out.println("White has won!");
				break;
			}
			screen.showPiecesOnBoard(board.getData());
			if (blackPlayer.makeMove()) {
				System.out.println("Black has won!");
				break;
			}
			screen.showPiecesOnBoard(board.getData());
		}

	}

}
