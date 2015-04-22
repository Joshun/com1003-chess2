package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

/**
 * uk.ac.sheffield.aca14jmo.Queen.java
 *
 * Class to represent a queen piece
 */

public class Queen extends MoveUntilBlocked {
	public Queen(int ix, int iy, int c, Board b) {
		super(PieceCode.QUEEN, ix, iy, c, b);
	}

	public ArrayList availableMoves() {
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		ArrayList<Move> horizontalVerticalMoves = getHorizontalVertical();
		ArrayList<Move> diagonalMoves = getDiagonal();
		possibleMoves.addAll(horizontalVerticalMoves);
		possibleMoves.addAll(diagonalMoves);

		if (possibleMoves.isEmpty()) {
			return null;
		}
		else {
			return possibleMoves;
		}
	}
}
