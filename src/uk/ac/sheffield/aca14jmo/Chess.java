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
	private static AggressivePlayer whitePlayer;
	private static RandomPlayer blackPlayer;
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

	public static void main(String[] args)  throws InterruptedException {
		processArgs(args);
		input = new Scanner(System.in);
		board = new Board();
		screen = new TextDisplay();

		whitePieces = new Pieces(board, PieceCode.WHITE);
		blackPieces = new Pieces(board, PieceCode.BLACK);

		whitePlayer = new AggressivePlayer("White", whitePieces, board, null);
		blackPlayer = new RandomPlayer("Black", blackPieces, board, null);

		whitePlayer.setOpponent(blackPlayer);
		blackPlayer.setOpponent(whitePlayer);

		screen.showPiecesOnBoard(board.getData());

		GraphicalDisplay display = new GraphicalDisplay();

	}

}
