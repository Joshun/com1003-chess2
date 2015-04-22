package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

/**
 * uk.ac.sheffield.aca14jmo.Rook.java
 *
 * Class to represent a rook piece
 */

public class Rook extends MoveUntilBlocked {
	public Rook(int ix, int iy, int c, Board b)  {
		super(PieceCode.ROOK, ix, iy, c, b);
	}

	public ArrayList<Move> availableMoves() {
		ArrayList<Move> possibleMoves = getHorizontalVertical();
		if (possibleMoves.isEmpty()) {
			return null;
		}
		else {
			return possibleMoves;
		}
	}

	public static void main(String[] args) {
		Board b = new Board();
		Piece bishop = new Bishop(2, 0, PieceCode.WHITE, b);
		System.out.println(bishop.availableMoves());
	}
}
