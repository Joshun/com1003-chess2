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
	private static HumanPlayer whitePlayer;
	private static RandomPlayer blackPlayer;
	private static Pieces whitePieces;
	private static Pieces blackPieces;
	private static HumanPlayerState humanPlayerState;
	private static GraphicalDisplay display;

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

	public static void processMoves(int playerX, int playerY) {
		if (humanPlayerState.getClickState() == ClickState.CLICK_END) {
			System.out.println("Attempting move...");
			System.out.println(humanPlayerState);
			int playerStartX = humanPlayerState.getStartX();
			int playerStartY = humanPlayerState.getStartY();
			int playerEndX = humanPlayerState.getEndX();
			int playerEndY = humanPlayerState.getEndY();

			whitePlayer.makeMove(playerStartX, playerStartY, playerEndX, playerEndY);

			if (!humanPlayerState.getSuccessful()) {
				System.out.println("Move failed.");
				humanPlayerState.reset();
				return;
			}
			display.showPiecesOnBoard(board.getData());
			humanPlayerState.reset();
			blackPlayer.makeMove();
			display.showPiecesOnBoard(board.getData());
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
		blackPlayer = new RandomPlayer("Black", blackPieces, board, null);

		whitePlayer.setOpponent(blackPlayer);
		blackPlayer.setOpponent(whitePlayer);

		humanPlayerState = whitePlayer.getHumanPlayerState();

		screen.showPiecesOnBoard(board.getData());


		display = new GraphicalDisplay(humanPlayerState);
		display.showPiecesOnBoard(board.getData());

	}

}
