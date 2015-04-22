package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

/**
 * uk.ac.sheffield.aca14jmo.King.java
 *
 * Class to represent the king piece
 */

public class King extends Piece {
	public King(int ix, int iy, int c, Board b)  {
		super(PieceCode.KING, ix, iy, c, b);
	}

	public ArrayList<Move> availableMoves() {
		int currentX = getX();
		int currentY = getY();
		Board b = getBoard();
		ArrayList<Move> possibleMoves = new ArrayList<Move>();

		for (int i=-1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {
				if (!(i==0 && j==0) && !b.outOfRange(currentX+i, currentY+j)) {
					if (b.occupied(currentX+i, currentY+j)) {
						if (getColour() != b.getPiece(currentX+i, currentY+j).getColour()) {
							possibleMoves.add(new Move(this, currentX, currentY, currentX + i, currentY + j, true));
						}
					}
					else {
						possibleMoves.add(new Move(this, currentX, currentY, currentX+i, currentY+j, false));
					}
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