package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * uk.ac.sheffield.aca14jmo.HumanPlayer.java
 *
 * Class to represent a human player, containing all they need to interact with the game
 */

public class HumanPlayer extends Player {
	private Scanner input;
	private String name;
	private boolean hasWon;
	public HumanPlayer(String n, Pieces p, Board b, Player o, Scanner input) {
		super(n, p, b, o);
		this.input = input;
		this.name = n;
		this.hasWon = false;
	}

	private int letterToNumber(char c) {
		char upperCaseChar = Character.toUpperCase(c);
		return (Integer.valueOf(upperCaseChar) - 65) + 1;
	}

	public void displayReferenceError(String prompt) {
		System.out.println("Invalid reference, try again.");
		System.out.print(prompt);
	}

	public int[] readCoordinates(String prompt) {
		System.out.print(prompt);

		String inputText = "";
		char c1 = 0;
		char c2 = 0;
		boolean inputHasErrors;


		do {
			inputHasErrors = false;

			// If user presses Control-D, then exit normally
			try {
				inputText = input.nextLine();
			}
			catch (java.util.NoSuchElementException e) {
				System.out.println("\nExiting...");
				System.exit(0);
			}

			if (inputText.length() != 2) {
				displayReferenceError(prompt);
				inputHasErrors = true;
			}
			else {
				c1 = inputText.charAt(0);
				c2 = inputText.charAt(1);

				if (!Character.isAlphabetic(c1) || !Character.isDigit(c2)) {
					displayReferenceError(prompt);
					inputHasErrors = true;
				}
			}

		} while (inputHasErrors);

		return new int[]{letterToNumber(c1)-1, Integer.parseInt(Character.toString(c2))-1};
	}

	public boolean makeMove() {
		System.out.println();
		System.out.println("It is " + name + "\'s turn.");
		int[] startCoords = readCoordinates("Start: ");
		int[] endCoords = readCoordinates("End: ");

		int startX = startCoords[0];
		int startY = startCoords[1];
		int endX = endCoords[0];
		int endY = endCoords[1];

		if (!processMove(startX, startY, endX, endY)) {
			System.out.println("Invalid move!");
			return makeMove();
		}
		return hasWon;
	}

	public boolean processMove(int startX, int startY, int endX, int endY) {
		Board board = getBoard();
		Pieces pieces = getPieces();
		int colour = pieces.getPiece(0).getColour();

		Piece currentPiece = null;
		Move newMove = null;
		if (board.outOfRange(startX, startY) || board.outOfRange(endX, endY)) {
			return false;
		}
		else if (board.occupied(startX, startY)) {
			currentPiece = board.getPiece(startX, startY);

			boolean capture = board.occupied(endX, endY) && board.getPiece(endX, endY).getColour() != colour;

			if (capture) {
				newMove = new Move(currentPiece, startX, startY, endX, endY, true);
			}
			else {
				newMove = new Move(currentPiece, startX, startY, endX, endY, false);
			}

			if (currentPiece.getColour() != colour) {
				return false;
			}
			else {
				DebugLog.println("Players move: " + newMove);
				ArrayList<Move> possibleMoves = currentPiece.availableMoves();
				DebugLog.println("Possible moves: " + possibleMoves);
				if (possibleMoves != null && possibleMoves.contains(newMove)) {
					if (capture && board.getPiece(endX, endY) instanceof King) {
						hasWon = true;
					}
					newMove.execute(getOpponent());
					return true;
				}

			}
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Board b = new Board();
	}

}
