package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

/**
 * uk.ac.sheffield.aca14jmo.Knight.java
 *
 * Class to represent a knight piece
 */

public class Knight extends Piece {
	public Knight(int ix, int iy, int c, Board b)  {
		super(PieceCode.KNIGHT, ix, iy, c, b);
	}

	public ArrayList<Move> availableMoves() {
		int currentX = getX();
		int currentY = getY();
		Board b = getBoard();
		ArrayList<Move> possibleMoves = new ArrayList<Move>();

		int[][] moveVectors = {{-2,-1}, {-1,-2}, {-2,1}, {2,-1}, {-1,2}, {1,-2}, {1,2}, {2,1}};

		for (int[] mv: moveVectors) {
			int testX = currentX + mv[0];
			int testY = currentY + mv[1];

			if (!b.outOfRange(testX, testY)) {
				if (!b.occupied(testX, testY)) {
					possibleMoves.add(new Move(this, currentX, currentY, testX, testY, false));
				}
				else if (b.getPiece(testX, testY).getColour() != getColour()){
					possibleMoves.add(new Move(this, currentX, currentY, testX, testY, true));
				}
			}
		}

		if (possibleMoves.isEmpty()) {
			return null;
		}
		else {
			return possibleMoves;
		}
	}
}
