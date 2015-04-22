package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

public class Bishop extends MoveUntilBlocked {
	public Bishop(int ix, int iy, int c, Board b)  {
		super(PieceCode.BISHOP, ix, iy, c, b);

	}

	public ArrayList<Move> availableMoves() {
		ArrayList<Move> possibleMoves = getDiagonal();
		if (possibleMoves.isEmpty()) {
			return null;
		}
		else {
			return possibleMoves;
		}
	}
}
